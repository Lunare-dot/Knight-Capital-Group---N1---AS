package com.kcg.trading_system.dto;

public class OrdemResponseDTO {

    private String ordemId;
    private String status;
    private String mensagem;
    private String servidorId;

    public OrdemResponseDTO() {}

    public OrdemResponseDTO(String ordemId, String status, String mensagem, String servidorId) {
        this.ordemId = ordemId;
        this.status = status;
        this.mensagem = mensagem;
        this.servidorId = servidorId;
    }

    public String getOrdemId() { return ordemId; }
    public void setOrdemId(String ordemId) { this.ordemId = ordemId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getServidoId() { return servidorId; }
    public void setServidorId(String servidorId) { this.servidorId = servidorId; } 
}