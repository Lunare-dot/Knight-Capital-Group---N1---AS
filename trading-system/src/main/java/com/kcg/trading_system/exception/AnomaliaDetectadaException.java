package com.kcg.trading_system.exception;

/**
 * Exceção lançada quando uma anomalia é identificada nas regras de negócio
 * do sistema de trading.
 */
public class AnomaliaDetectadaException extends RuntimeException {
    
    /**
     * Cria uma nova exceção de anomalia detectada com a mensagem informada.
     * 
     * @param message descrição da anomalia identificada
     */
    public AnomaliaDetectadaException(String message) {
        super(message);
    }
}