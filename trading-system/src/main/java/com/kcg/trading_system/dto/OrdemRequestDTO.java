package com.kcg.trading_system.dto;

/**
 * DTO de entrada para criação de uma ordem de negociação.
 * Representa os dados enviados pelo cliente via requisição HTTP.
 */
public class OrdemRequestDTO {

    private String tickerAcao;
    private Long quantidade;
    private String servidorId;

    public OrdemRequestDTO() {}

    public String getTickerAcao() { return tickerAcao; }
    public void returnTickerAcao(String tickerAcao) { this.tickerAcao = tickerAcao; }

    public Long getQuantidade() { return quantidade; }
    public void setQuantidade(Long quantidade) { this.quantidade = quantidade; }

    public String getServidorId() { return servidorId; }
    public void setServidorId(String servidorId) { this.servidorId = servidorId; }
}