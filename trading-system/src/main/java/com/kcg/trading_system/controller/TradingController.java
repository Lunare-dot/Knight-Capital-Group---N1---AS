package com.kcg.trading_system.controller;

import com.kcg.trading_system.dto.OrdemRequestDTO;
import com.kcg.trading_system.dto.OrdemResponseDTO;
import com.kcg.trading_system.model.OrdemNegociacao;
import com.kcg.trading_system.model.RegistroAuditoria;
import com.kcg.trading_system.service.AuditoriaService;
import com.kcg.trading_system.service.TradingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller responsável pelas operações de negociação e auditoria do sistema.
 */
@RestController
@RequestMapping("/trading")
public class TradingController {

    private final TradingService tradingService;
    private final AuditoriaService auditoriaService;

    @Autowired
    public TradingController(TradingService tradingService, AuditoriaService auditoriaService) {
        this.tradingService = tradingService;
        this.auditoriaService = auditoriaService;
    }

    /**
     * Inicia todas as operações de negociação do sistema.
     * Valida o deploy antes de processar as ordens disponíveis no MockData.
     *
     * @return mensagem de confirmação
     */
    @PostMapping("/iniciar")
    public ResponseEntity<String> iniciarOperacoes() {
        tradingService.iniciarOperacoes();
        return ResponseEntity.ok("Operações iniciadas com sucesso.");
    }

    /**
     * Processa uma ordem de negociação individual enviada pelo cliente.
     *
     * @param request dados da ordem enviados pelo cliente
     * @return resposta com status e detalhes da ordem processada
     */
    @PostMapping("/ordem")
    public ResponseEntity<OrdemResponseDTO> enviarOrdem(@RequestBody OrdemRequestDTO request) {
        OrdemNegociacao ordem = new OrdemNegociacao(
                LocalDateTime.now(),
                OrdemNegociacao.StatusFinal.PENDENTE,
                request.getQuantidade().doubleValue(),
                request.getServidorId()
        );

        tradingService.executarOrdem(ordem);

        OrdemResponseDTO response = new OrdemResponseDTO(
                ordem.getId(),
                OrdemNegociacao.StatusFinal.APROVADO.toString(),
                "Ordem processada com sucesso.",
                ordem.getServidorId()
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Interrompe manualmente todas as operações do sistema.
     *
     * @return mensagem de confirmação
     */
    @PostMapping("/interromper")
    public ResponseEntity<String> interromperOperacoes() {
        tradingService.interromperOperacoes();
        return ResponseEntity.ok("Operações interrompidas manualmente.");
    }

    /**
     * Retorna todos os registros de auditoria do sistema.
     *
     * @return lista de registros de auditoria
     */
    @GetMapping("/auditoria")
    public ResponseEntity<List<RegistroAuditoria>> listarAuditoria() {
        return ResponseEntity.ok(auditoriaService.listarRegistros());
    }
}