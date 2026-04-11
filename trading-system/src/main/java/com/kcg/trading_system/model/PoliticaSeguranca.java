package com.kcg.trading_system.model;

public class PoliticaSeguranca extends BaseEntity {
    private String versaoHomologada;
    private Long limiteFinanceiroTeto;

    public PoliticaSeguranca() {
        super();
    }

    public PoliticaSeguranca(String versaoHomologada, Long limiteFinanceiroTeto) {
        super();
        setVersaoHomologada(versaoHomologada);
        setLimiteFinanceiroTeto(limiteFinanceiroTeto);
    }

    public PoliticaSeguranca(String id, String versaoHomologada, Long limiteFinanceiroTeto) {
        super(id);
        setVersaoHomologada(versaoHomologada);
        setLimiteFinanceiroTeto(limiteFinanceiroTeto);
    }

    public String getVersaoHomologada() { return versaoHomologada; }
    public void setVersaoHomologada(String versaoHomologada) {
        validarVersaoHomologada(versaoHomologada);
        this.versaoHomologada = versaoHomologada;
    }

    public Long getLimiteFinanceiroTeto() { return limiteFinanceiroTeto; }
    public void setLimiteFinanceiroTeto(Long limiteFinanceiroTeto) {
        validarLimiteFinanceiroTeto(limiteFinanceiroTeto);
        this.limiteFinanceiroTeto = limiteFinanceiroTeto;
    }

    private void validarVersaoHomologada(String versaoHomologada) {
        if(versaoHomologada == null || versaoHomologada.isEmpty()) {
            throw new IllegalArgumentException("Versão inválida.");
        }
    }

    private void validarLimiteFinanceiroTeto(Long limiteFinanceiroTeto) {
        if(limiteFinanceiroTeto == null || limiteFinanceiroTeto == 0) {
            throw new IllegalArgumentException("Limite vazio ou inválido.");
        }
    }

    @Override
    public void mostrarDados() {
        System.out.println("=== Políticas de Segurança ===");
        System.out.println("- ID:" + id);
        System.out.println("- Versão:" + versaoHomologada);
        System.out.println("- Limite:" + limiteFinanceiroTeto);
    }
}