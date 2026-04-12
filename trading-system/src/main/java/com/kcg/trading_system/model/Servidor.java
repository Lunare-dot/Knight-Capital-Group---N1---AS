package com.kcg.trading_system.model;

public class Servidor extends BaseEntity {
    public enum StatusOperacional { LIGADO, DESLIGADO, INDISPONIVEL }

    private String hostname;
    private String versaoInstalada;
    private PoliticaSeguranca politica;
    private StatusOperacional status;

    public Servidor() {
        super();
    }

    public Servidor(String hostname, String versaoInstalada, PoliticaSeguranca politica, StatusOperacional status) {
        super();
        setHostname(hostname);
        setVersaoInstalada(versaoInstalada);
        setPoliticaSeguranca(politica);
        setStatusOperacional(status);
    }

    public Servidor(String id, String hostname, String versaoInstalada, PoliticaSeguranca politica, StatusOperacional status) {
        super(id);
        setHostname(hostname);
        setVersaoInstalada(versaoInstalada);
        setPoliticaSeguranca(politica);
        setStatusOperacional(status);
    }

    public String getHostname() { return hostname; }
    public void setHostname(String hostname) {
        validarHostname(hostname);
        this.hostname = hostname;
    }

    public String getVersaoInstalada() { return versaoInstalada; }
    public void setVersaoInstalada(String versaoInstalada) {
        validarVersaoInstalada(versaoInstalada);
        this.versaoInstalada = versaoInstalada;
    }

    public PoliticaSeguranca getPoliticaSeguranca() { return politica; }
    public void setPoliticaSeguranca(PoliticaSeguranca politica) {
        validarPoliticaSeguranca(politica);
        this.politica = politica;
    }

    public StatusOperacional getStatusOperacional() { return status; }
    public void setStatusOperacional(StatusOperacional status) {
        validarStatusOperacional(status);
        this.status = status;
    }

    private void validarHostname(String hostname) {
        if(hostname == null || hostname.isBlank()) {
            throw new IllegalArgumentException("Hostname vazio ou inválido.");
        }
    }

    private void validarVersaoInstalada(String versaoInstalada) {
        if(versaoInstalada == null || versaoInstalada.isBlank()) {
            throw new IllegalArgumentException("Versão instalada inválida.");
        }
    }

    private void validarPoliticaSeguranca(PoliticaSeguranca politica) {
        if(politica == null) {
            throw new IllegalArgumentException("Política de Segurança violada.");
        }
    }

    private void validarStatusOperacional(StatusOperacional status) {
        if(status == null) {
            throw new IllegalArgumentException("Status inválido.");
        }
    }
}