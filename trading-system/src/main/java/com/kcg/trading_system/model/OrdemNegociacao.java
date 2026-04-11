package com.kcg.trading_system.model;

import java.time.LocalDateTime;

public class OrdemNegociacao extends BaseEntity {
    private enum StatusFinal { APROVADO, REJEITADO }

    private LocalDateTime dataHora;
    private StatusFinal status;
    private Double valorTotal;
    private Servidor servidor;

    public OrdemNegociacao() {
        super();
    }

    public OrdemNegociacao(LocalDateTime dataHora, StatusFinal status, Double valorTotal, Servidor servidor) {
        super();
        setDataHora(dataHora);
        setStatusFinal(status);
        setValorTotal(valorTotal);
        setServidor(servidor);
    }

    public OrdemNegociacao(String id, LocalDateTime dataHora, StatusFinal status, Double valorTotal, Servidor servidor) {
        super(id);
        setDataHora(dataHora);
        setStatusFinal(status);
        setValorTotal(valorTotal);
        setServidor(servidor);
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

    public Servidor getServidor() { return servidor; }
    public void setServidor(Servidor servidor) {
        validarServidor(servidor);
        this.servidor = servidor;
    }

    private void validarDataHora(LocalDateTime dataHora) {
        if(dataHora == null) {
            throw new IllegalArgumentException("Data e hora inválidas.");
        }

        if(dataHora.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data e hora da negociação não podem ser no futuro.");
        }
    }

    private void validarStatusFinal(StatusFinal status) {
        if(status == null) {
            throw new IllegalArgumentException("Status inválido.");
        }
    }

    private void validarValorTotal(Double valorTotal) {
        if(valorTotal == null || valorTotal < 0) {
            throw new IllegalArgumentException("Valor total vazio ou inválido.");
        }
    }

    private void validarServidor(Servidor servidor) {
        if(servidor == null) {
            throw new IllegalArgumentException("Servidor não encontrado ou indisponível.");
        }
    }
}