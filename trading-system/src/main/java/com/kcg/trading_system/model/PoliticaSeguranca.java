package com.kcg.trading_system.model;

import java.math.BigDecimal;

public class PoliticaSeguranca extends BaseEntity {
    private String versaoHomologada;
    private BigDecimal limiteFinanceiroTeto;

    public PoliticaSeguranca() {
        super();
    }

    public PoliticaSeguranca(String versaoHomologada, BigDecimal limiteFinanceiroTeto) {
        super();
        setVersaoHomologada(versaoHomologada);
        setLimiteFinanceiroTeto(limiteFinanceiroTeto);
    }

    public PoliticaSeguranca(String id, String versaoHomologada, BigDecimal limiteFinanceiroTeto) {
        super(id);
        setVersaoHomologada(versaoHomologada);
        setLimiteFinanceiroTeto(limiteFinanceiroTeto);
    }

    public String getVersaoHomologada() { return versaoHomologada; }
    public void setVersaoHomologada(String versaoHomologada) {
        validarVersaoHomologada(versaoHomologada);
        this.versaoHomologada = versaoHomologada;
    }

    public BigDecimal getLimiteFinanceiroTeto() { return limiteFinanceiroTeto; }
    public void setLimiteFinanceiroTeto(BigDecimal limiteFinanceiroTeto) {
        validarLimiteFinanceiroTeto(limiteFinanceiroTeto);
        this.limiteFinanceiroTeto = limiteFinanceiroTeto;
    }

    private void validarVersaoHomologada(String versaoHomologada) {
        if(versaoHomologada == null || versaoHomologada.isEmpty()) {
            throw new IllegalArgumentException("Versão inválida.");
        }
    }

    private void validarLimiteFinanceiroTeto(BigDecimal limiteFinanceiroTeto) {
        if(limiteFinanceiroTeto == null || limiteFinanceiroTeto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Limite vazio ou inválido.");
        }
    }
}