package com.kcg.trading_system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kcg.trading_system.mock.MockData;
import com.kcg.trading_system.model.RegistroAuditoria;

/**
 * Service responsável pelo registro de eventos de auditoria do sistema.
 * Não contém regras de negócio, apenas registra eventos ocorridos.
 */
public class AuditoriaService {

    private final List<RegistroAuditoria> registros = new ArrayList<>(MockData.AUDITORIAS);

    /**
     * Registra um novo evento de auditoria.
     * 
     * @param tipo tipo do evento ocorrido
     * @param mensagem mensagem descrição do evento
     * @param ordemId ordemId identificador da ordem associada (pode ser null)
     */
    public void registrarEvento(RegistroAuditoria.TipoEvento tipo, String mensagem, String ordemId) {
        RegistroAuditoria registro = new RegistroAuditoria(tipo, mensagem, LocalDateTime.now(), ordemId);

        salvar(registro);
    }

    /**
     * Persiste o registro de auditoria.
     * Todos os dados são mantidos em memória.
     * 
     * @param registro evento de auditoria a ser salvo
     */
    private void salvar(RegistroAuditoria registro) {
        registros.add(registro);
        System.out.println("Audit log: " + registro);
    }

    /**
     * Retorna uma lista de todos os registros de auditoria armazenados.
     * 
     * @return lista de registros de auditoria
     */
    public List<RegistroAuditoria> listarRegistros() {
        return registros;
    }
}