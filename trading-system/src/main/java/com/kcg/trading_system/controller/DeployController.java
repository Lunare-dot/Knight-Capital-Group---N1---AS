package com.kcg.trading_system.controller;

import com.kcg.trading_system.mock.MockData;
import com.kcg.trading_system.model.Servidor;
import com.kcg.trading_system.policy.SecurityPolicyManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelo gerenciamento de deploy e monitoramento de servidores.
 */
@RestController
@RequestMapping("/deploy")
public class DeployController {

    private final SecurityPolicyManager policyManager = SecurityPolicyManager.getInstance();

    /**
     * Verifica se todos os servidores estão na versão homologada pela política vigente.
     *
     * @return mensagem indicando se o deploy está consistente ou não
     */
    @GetMapping("/verificar")
    public ResponseEntity<String> verificarConsistencia() {
        if (policyManager.isDeployConsistente()) {
            return ResponseEntity.ok("Deploy consistente. Todos os servidores estão na versão homologada.");
        }
        return ResponseEntity.status(409).body("Deploy inconsistente. Há servidores com versão divergente.");
    }

    /**
     * Lista todos os servidores e seus respectivos status operacionais.
     *
     * @return lista de servidores registrados no sistema
     */
    @GetMapping("/servidores")
    public ResponseEntity<List<Servidor>> listarServidores() {
        return ResponseEntity.ok(MockData.SERVIDORES);
    }

    /**
     * Retorna o status individual de um servidor pelo seu ID.
     *
     * @param id identificador do servidor
     * @return servidor correspondente ou 404 caso não encontrado
     */
    @GetMapping("/servidores/{id}")
    public ResponseEntity<Servidor> buscarServidor(@PathVariable String id) {
        return MockData.SERVIDORES.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}