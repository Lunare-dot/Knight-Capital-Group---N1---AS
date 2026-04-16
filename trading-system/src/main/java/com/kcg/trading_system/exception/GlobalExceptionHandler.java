package com.kcg.trading_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Tratador global de exceções da API.
 * Converte exceções de domínio em respostas HTTP adequadas ao cliente.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata anomalias detectadas nas operações de trading.
     * Retorna HTTP 422 — a requisição foi entendida mas não pôde ser processada.
     *
     * @param e exceção lançada pelo TradingService
     * @return resposta com mensagem de erro
     */
    @ExceptionHandler(AnomaliaDetectadaException.class)
    public ResponseEntity<String> handleAnomalia(AnomaliaDetectadaException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Trata inconsistências detectadas no deploy do sistema.
     *
     * @param e exceção lançada pelo TradingService ou DeployController
     * @return resposta com mensagem de erro
     */
    @ExceptionHandler(DeployInconsistenteException.class)
    public ResponseEntity<String> handleDeploy(DeployInconsistenteException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /**
     * Trata exceções genéricas não previstas pelo domínio.
     * Retorna HTTP 500 — erro interno do servidor.
     *
     * @param e exceção genérica
     * @return resposta com mensagem de erro genérica
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno do servidor: " + e.getMessage());
    }
}