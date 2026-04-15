package com.kcg.trading_system.policy;

import java.math.BigDecimal;

import com.kcg.trading_system.mock.MockData;
import com.kcg.trading_system.model.PoliticaSeguranca;
import com.kcg.trading_system.model.Servidor;

public class SecurityPolicyManager {

    private static volatile SecurityPolicyManager instance;
    private final PoliticaSeguranca politicaVigente;

    /**
     * Incializa o manager carregando a política de segurança vigente do MockData.
     * O construtor privador impede instanciação externa, garantindo o padrão Singleton.
     */
    private SecurityPolicyManager() {
        this.politicaVigente = MockData.POLITICA_PADRAO;
    }

    /**
     * Retorna a instância única do SecurityPolicyManager.
     * Utiliza Double-Checked Locking para garantir thread-safety sem custo
     * desnecessário de sincronização após a primeira inicialização.
     * 
     * @return instância única de SecurityPolicyManager
     */
    public static SecurityPolicyManager getInstance() {
        if(instance == null) {                          // primeira verificação: sem lock
            synchronized(SecurityPolicyManager.class) { // adquire lock apenas se necessário
                if(instance == null) {                  // segunda verificação: dentro do lock
                    instance = new SecurityPolicyManager();
                }
            }
        }
        return instance;
    }

    /**
     * Método de consulta para a política vigente.
     * @return a política de segurança vigente.
     */
    public PoliticaSeguranca getPoliticaVigente() {
        return politicaVigente;
    }

    /**
     * Verifica se o valor da ordem ultrapassa o limite financeiro.
     * @param valorTotal a ser verificado.
     * @return true caso o valor esteja dentro do limite imposto pela política vigente.
     */
    public boolean isValorDentroDoLimite(Double valorTotal) {
        BigDecimal valor = BigDecimal.valueOf(valorTotal);
        return valor.compareTo(politicaVigente.getLimiteFinanceiroTeto()) <= 0;
    }

    /**
     * Verifica se a versão instalada no servidor é a versão homologada.
     * @param servidor com a versão a ser verificada.
     * @return true caso a versão instalada for a versão homologada.
     */
    public boolean isVersaoHomologada(Servidor servidor) {
        return servidor.getVersaoInstalada().equals(politicaVigente.getVersaoHomologada());
    }

    /**
     * Verifica se todos os servidores estão na versão homologada,
     * Usado pelo DeployService antes de liberar operações.
     * @return true caso as versões estejam ba versão homologada pela política vigente.
     */
    public boolean isDeployConsistente() {
        return MockData.SERVIDORES.stream().allMatch(this::isVersaoHomologada);
    }
}