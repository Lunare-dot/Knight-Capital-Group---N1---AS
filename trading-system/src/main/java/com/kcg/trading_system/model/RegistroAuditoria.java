package com.kcg.trading_system.model;

import java.time.LocalDateTime;

public class RegistroAuditoria extends BaseEntity {
    private String tipoEvento;
    private String mensagemDetalhada;
    private LocalDateTime timestamp;
    private String ordemId;

    public RegistroAuditoria() {
        super();
    }

    public RegistroAuditoria(String tipoEvento, String mensagemDetalhada, LocalDateTime timestamp, String ordemId) {
        super();
        setTipoEvento(tipoEvento);
        setMensagemDetalhada(mensagemDetalhada);
        setTimestamp(timestamp);
        setOrdemId(ordemId);
    }

    public RegistroAuditoria(String id, String tipoEvento, String mensagemDetalhada, LocalDateTime timestamp, String ordemId) {
        super(id);
        setTipoEvento(tipoEvento);
        setMensagemDetalhada(mensagemDetalhada);
        setTimestamp(timestamp);
        setOrdemId(ordemId);
    }

    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) {
        verificarTipoEvento(tipoEvento);
        this.tipoEvento = tipoEvento;
    }

    public String getMensagemDetalhada() { return mensagemDetalhada; }
    public void setMensagemDetalhada(String mensagemDetalhada) {
        verificarMensagemDetalhada(mensagemDetalhada);
        this.mensagemDetalhada = mensagemDetalhada;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) {
        verificarTimeStamp(timestamp);
        this.timestamp = timestamp;
    }

    public String getOrdemId() { return ordemId; }
    public void setOrdemId(String ordemId) {
        this.ordemId = ordemId;
    }

    private void verificarTipoEvento(String tipoEvento) {
        if(tipoEvento == null || tipoEvento.isBlank()) {
            throw new IllegalArgumentException("Tipo de evento vazio ou inválido.");
        }
    }

    private void verificarMensagemDetalhada(String mensagemDetalhada) {
        if(mensagemDetalhada == null || mensagemDetalhada.isBlank()) {
            throw new IllegalArgumentException("Mensagem inválida.");
        }
    }

    private void verificarTimeStamp(LocalDateTime timestamp) {
        if(timestamp == null) {
            throw new IllegalArgumentException("Timestamp do registro inválido.");
        }
    }
}