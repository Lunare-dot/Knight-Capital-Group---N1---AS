package com.kcg.trading_system.model;

public class Servidor extends BaseEntity {
    public enum StatusOperacional { LIGADO, DESLIGADO, INDISPONÍVEL }

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
        setVersaoInstalda(versaoInstalada);
        setPoliticaSegurança(politica);
        setStatusOperacional(status);
    }

    public Servidor(String id, String hostname, String versaoInstalada, PoliticaSeguranca politica, StatusOperacional status) {
        super(id);
        setHostname(hostname);
        setVersaoInstalda(versaoInstalada);
        setPoliticaSegurança(politica);
        setStatusOperacional(status);
    }

    public String getHostname() { return hostname; }
    public void setHostname(String hostname) {
        validarHostname(hostname);
        this.hostname = hostname;
    }

    public String getVersaoInstalada() { return versaoInstalada; }
    public void setVersaoInstalda(String versaoInstalada) {
        validarVersaoInstalada(versaoInstalada);
        this.versaoInstalada = versaoInstalada;
    }

    public PoliticaSeguranca getPoliticaSegurança() { return politica; }
    public void setPoliticaSegurança(PoliticaSeguranca politica) {
        validarPoliticaSeguranca(politica);
        this.politica = politica;
    }

    public StatusOperacional getStatusOperacional() { return status; }
    public void setStatusOperacional(StatusOperacional status) {
        validarStatusOperacional(status);
        this.status = status;
    }

    public void validarHostname(String hostname) {
        if(hostname == null || hostname.isEmpty()) {
            throw new IllegalArgumentException("Hostname vazio ou inválido.");
        }
    }

    public void validarVersaoInstalada(String versaoInstalada) {
        if(versaoInstalada == null || versaoInstalada.isEmpty()) {
            throw new IllegalArgumentException("Versão instalada inválida.");
        }
    }

    public void validarPoliticaSeguranca(PoliticaSeguranca politica) {
        if(politica == null) {
            throw new IllegalArgumentException("Política de Segurança violada.");
        }
    }

    public void validarStatusOperacional(StatusOperacional status) {
        if(status == null) {
            throw new IllegalArgumentException("Status inválido.");
        }
    }
}