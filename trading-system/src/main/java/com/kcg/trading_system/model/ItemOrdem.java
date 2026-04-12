package com.kcg.trading_system.model;

import java.math.BigDecimal;

public class ItemOrdem extends BaseEntity {
    private String tickerAcao;
    private Long quantidade;
    private BigDecimal precoUnitario;
    private String ordemId;

    public ItemOrdem() {
        super();
    }

    public ItemOrdem(String tickerAcao, Long quantidade, BigDecimal precoUnitario, String ordemId) {
        super();
        setTickerAcao(tickerAcao);
        setQuantidade(quantidade);
        setPrecoUnitario(precoUnitario);
        setOrdemId(ordemId);
    }

    public ItemOrdem(String id, String tickerAcao, Long quantidade, BigDecimal precoUnitario, String ordemId) {
        super(id);
        setTickerAcao(tickerAcao);
        setQuantidade(quantidade);
        setPrecoUnitario(precoUnitario);
        setOrdemId(ordemId);
    }

    public String getTickerAcao() { return tickerAcao; }
    public void setTickerAcao(String tickerAcao) {
        validarTickerAcao(tickerAcao);
        this.tickerAcao = tickerAcao;
    }

    public Long getQuantidade() { return quantidade; }
    public void setQuantidade(Long quantidade) {
        validarQuantidade(quantidade);
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) {
        validarPrecoUnitario(precoUnitario);
        this.precoUnitario = precoUnitario;
    }

    public String getOrdemId() { return ordemId; }
    public void setOrdemId(String ordemId) {
        validarOrdemId(ordemId);
        this.ordemId = ordemId;
    }

    private void validarTickerAcao(String tickerAcao) {
        if(tickerAcao == null || tickerAcao.isBlank()) {
            throw new IllegalArgumentException("Ticker de ação inválido.");
        }

        if(!tickerAcao.matches("^[A-Z]{1,5}$")) {
            throw new IllegalArgumentException("Ticker deve conter entre 1 e 5 letras maiúsculas (ex: AAPL, GOOGL).");
        }
    }

    private void validarQuantidade(Long quantidade) {
        if(quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
    }

    private void validarPrecoUnitario(BigDecimal precoUnitario) {
        if(precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço unitário deve ser maior que zero.");
        }
    }

    private void validarOrdemId(String ordemId) {
        if(ordemId == null || ordemId.isBlank()) {
            throw new IllegalArgumentException("ID da ordem de negociação inválido.");
        }
    }
}