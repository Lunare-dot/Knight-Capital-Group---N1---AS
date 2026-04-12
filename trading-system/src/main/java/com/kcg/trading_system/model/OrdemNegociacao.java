package com.kcg.trading_system.model;

import java.time.LocalDateTime;

public class OrdemNegociacao extends BaseEntity {
    public enum StatusFinal { APROVADO, REJEITADO, PENDENTE }

    private LocalDateTime dataHora;
    private StatusFinal status;
    private Double valorTotal;
    private String servidorId;

    public OrdemNegociacao() {
        super();
    }

    public OrdemNegociacao(LocalDateTime dataHora, StatusFinal status, Double valorTotal, String servidorId) {
        super();
        setDataHora(dataHora);
        setStatusFinal(status);
        setValorTotal(valorTotal);
        setServidorId(servidorId);
    }

    public OrdemNegociacao(String id, LocalDateTime dataHora, StatusFinal status, Double valorTotal, String servidorId) {
        super(id);
        setDataHora(dataHora);
        setStatusFinal(status);
        setValorTotal(valorTotal);
        setServidorId(servidorId);
    }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) {
        validarDataHora(dataHora);
        this.dataHora = dataHora;
    }

    public StatusFinal getStatusFinal() { return status; }
    public void setStatusFinal(StatusFinal status) {
        validarStatusFinal(status);
        this.status = status;
    }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) {
        validarValorTotal(valorTotal);
        this.valorTotal = valorTotal;
    }

    public String getServidorId() { return servidorId; }
    public void setServidorId(String servidorId) {
        validarServidor(servidorId);
        this.servidorId = servidorId;
    }

    private void validarDataHora(LocalDateTime dataHora) {
        if(dataHora == null) {
            throw new IllegalArgumentException("Data e hora inválidas.");
        }

        if(dataHora.isAfter(LocalDateTime.now().plusSeconds(1))) {
            throw new IllegalArgumentException("A data e hora da negociação não podem ser no futuro.");
        }
    }

    private void validarStatusFinal(StatusFinal status) {
        if(status == null) {
            throw new IllegalArgumentException("Status inválido.");
        }
    }

    private void validarValorTotal(Double valorTotal) {
        if(valorTotal == null || valorTotal <= 0) {
            throw new IllegalArgumentException("Valor total vazio ou inválido.");
        }
    }

    private void validarServidor(String servidorId) {
        if(servidorId == null || servidorId.isBlank()) {
            throw new IllegalArgumentException("ID do servidor inválido.");
        }
    }
}