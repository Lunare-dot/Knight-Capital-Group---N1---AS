package com.kcg.trading_system.service;

import com.kcg.trading_system.policy.SecurityPolicyManager;
import com.kcg.trading_system.mock.MockData;
import com.kcg.trading_system.model.Servidor;
import com.kcg.trading_system.model.OrdemNegociacao;
import com.kcg.trading_system.model.RegistroAuditoria;
import com.kcg.trading_system.exception.AnomaliaDetectadaException;
import com.kcg.trading_system.exception.DeployInconsistenteException;

import java.util.Optional;

/**
 * Service responsável por orquestrar as operações de negociação do sistema.
 * Realiza as validações de deploy, análise de anomalias e processamento de ordens,
 * além de registrar eventos de auditoria associados ao fluxo.
 */
public class TradingService {

    private final AuditoriaService auditoriaService;
    private final SecurityPolicyManager policyManager = SecurityPolicyManager.getInstance();

    /**
     * Construtor com injeção de dependência do serviço de auditoria.
     * 
     * @param auditoriaService serviço responsável pelo registro de eventos.
     */
    public TradingService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    /**
     * Inicia o fluxo de operações do sistema, validando previamente
     * a consistência do deploy e executando as ordens disponíveis.
     */
    public void iniciarOperacoes() {
        validarDeploy();
        MockData.ORDENS.forEach(this::executarOrdem);
    }

    /**
     * Executa uma ordem de negociação, realizando validações da versão
     * do servidor e limite financeiro antes do processamento.
     * 
     * @param ordem ordem de negociação a ser processada
     */
    public void executarOrdem(OrdemNegociacao ordem) {
        Servidor servidor = buscarServidor(ordem.getServidorId());

        if(!policyManager.isVersaoHomologada(servidor)) {
            auditoriaService.registrarEvento(
                RegistroAuditoria.TipoEvento.DEPLOY_INCONSISTENTE, 
                "Servidor: " + servidor.getId() + " com versão incompatível", 
                null);

            interromperOperacoes();

            throw new DeployInconsistenteException("Servidor " + servidor.getId() + " está com versão não homologada!");
        }

        auditoriaService.registrarEvento(
            RegistroAuditoria.TipoEvento.ORDEM_ENVIADA, 
            "Ordem enviada pelo servidor " + servidor.getId(), 
            ordem.getId()
        );

        if(!policyManager.isValorDentroDoLimite(ordem.getValorTotal())) {
            auditoriaService.registrarEvento(
                RegistroAuditoria.TipoEvento.ANOMALIA_DETECTADA,
                "Valor da ordem ultrapassa limite permitido", 
                ordem.getId()
            );

            auditoriaService.registrarEvento(
                RegistroAuditoria.TipoEvento.ORDEM_REJEITADA, 
                "Ordem rejeitada automaticamente", 
                ordem.getId()
            );

            throw new AnomaliaDetectadaException("Valor da ordem ultrapassa o limite permitido.");
        }
        processarOrdem(ordem);
    }

    /**
     * Realiza o processamento da ordem de negociação.
     * 
     * @param ordem ordem a ser processada
     */
    private void processarOrdem(OrdemNegociacao ordem) {
        System.out.println("Ordem " + ordem.getId() + " processada com sucesso.");
    }

    /**
     * Interrompe todas as operações do sistema em caso de falha crítica,
     * registrando o evento de auditoria correspondente.
     */
    public void interromperOperacoes() {
        auditoriaService.registrarEvento(
            RegistroAuditoria.TipoEvento.OPERACAO_INTERROMPIDA, 
            "Operações interrompidas automaticamente.", 
            null
        );
    }

    /**
     * Valida se o deploy atual está consistente com a política de segurança vigente.
     * 
     * @throws DeployInconsistenteException caso seja detectada inconsistência no deploy
     */
    private void validarDeploy() {
        if(!policyManager.isDeployConsistente()) {
            auditoriaService.registrarEvento(
                RegistroAuditoria.TipoEvento.DEPLOY_INCONSISTENTE, 
                "Deploy inconsistente detectado na incialização", 
                null
            );
            interromperOperacoes();
            throw new DeployInconsistenteException("Deploy inconsistente detectado!");
        }
    }

    /**
     * Busca o servidor associado a uma ordem de negociação.
     * 
     * @param servidorId identificador do servidor
     * @return servidor correspondente ao ID informado
     * @throws RuntimeException caso o servidor não seja encontrado
     */
    private Servidor buscarServidor(String servidorId) {
        Optional<Servidor> servidor = MockData.SERVIDORES.stream().filter(s -> s.getId()
                .equals(servidorId))
                .findFirst();

        return servidor.orElseThrow(() ->
            new RuntimeException("Servidor não encontrado: " + servidorId)
        );
    }
}