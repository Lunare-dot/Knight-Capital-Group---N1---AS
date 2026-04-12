package com.kcg.trading_system.model;

import java.time.LocalDateTime;

public class RegistroAuditoria extends BaseEntity {
    public enum TipoEvento {
        ORDEM_ENVIADA,
        ORDEM_REJEITADA,
        ANOMALIA_DETECTADA,
        OPERACAO_INTERROMPIDA,
        DEPLOY_REALIZADO,
        DEPLOY_INCONSISTENTE
    }

    private TipoEvento tipoEvento;
    private String mensagemDetalhada;
    private LocalDateTime timestamp;
    private String ordemId;

    public RegistroAuditoria() {
        super();
    }

    public RegistroAuditoria(TipoEvento tipoEvento, String mensagemDetalhada, LocalDateTime timestamp, String ordemId) {
        super();
        setTipoEvento(tipoEvento);
        setMensagemDetalhada(mensagemDetalhada);
        setTimestamp(timestamp);
        setOrdemId(ordemId);
    }

    public RegistroAuditoria(String id, TipoEvento tipoEvento, String mensagemDetalhada, LocalDateTime timestamp, String ordemId) {
        super(id);
        setTipoEvento(tipoEvento);
        setMensagemDetalhada(mensagemDetalhada);
        setTimestamp(timestamp);
        setOrdemId(ordemId);
    }

    public TipoEvento getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(TipoEvento tipoEvento) {
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

    private void verificarTipoEvento(TipoEvento tipoEvento) {
        if(tipoEvento == null) {
            throw new IllegalArgumentException("Tipo de evento inválido.");
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