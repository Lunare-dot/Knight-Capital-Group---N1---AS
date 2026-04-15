package com.kcg.trading_system.exception;

/**
 * Exceção lançada quando uma inconsistência é detecada durante operações
 * relacionadas a deploy no sistema.
 */
public class DeployInconsistenteException extends RuntimeException {

    /**
     * Cria uma nova exceção de deploy inconsistente com a mensagem informada.
     * 
     * @param message descrição da inconsistência encontrada
     */
    public DeployInconsistenteException(String message) {
        super(message);
    }
}