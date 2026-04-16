package com.kcg.trading_system.mock;

import com.kcg.trading_system.model.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class MockData {
    public static final PoliticaSeguranca POLITICA_PADRAO =
            new PoliticaSeguranca("pol-01", "v2.1.0", new BigDecimal("5000000"));

    // Simula o cenário KCG: 7 servidores corretos, 1 com versão legada
    public static final List<Servidor> SERVIDORES = List.of(
            new Servidor("srv-01", "v2.1.0", POLITICA_PADRAO, Servidor.StatusOperacional.LIGADO),
            new Servidor("srv-02", "v2.1.0", POLITICA_PADRAO, Servidor.StatusOperacional.LIGADO),
            new Servidor("srv-03", "v2.1.0", POLITICA_PADRAO, Servidor.StatusOperacional.LIGADO),
            new Servidor("srv-04", "v2.1.0", POLITICA_PADRAO, Servidor.StatusOperacional.LIGADO),
            new Servidor("srv-05", "v2.1.0", POLITICA_PADRAO, Servidor.StatusOperacional.LIGADO),
            new Servidor("srv-06", "v2.1.0", POLITICA_PADRAO, Servidor.StatusOperacional.LIGADO),
            new Servidor("srv-07", "v2.1.0", POLITICA_PADRAO, Servidor.StatusOperacional.LIGADO),
            new Servidor("srv-08", "v1.0.0", POLITICA_PADRAO, Servidor.StatusOperacional.LIGADO) // versão legado
    );

    public static final List<OrdemNegociacao> ORDENS = List.of(
        new OrdemNegociacao(
                "ord-01",
                LocalDateTime.now().minusMinutes(30),
                OrdemNegociacao.StatusFinal.APROVADO,
                Double.valueOf(150_000.0),
                "srv-01"
        ),
        new OrdemNegociacao(
                "ord-02",
                LocalDateTime.now().minusMinutes(20),
                OrdemNegociacao.StatusFinal.REJEITADO,
                Double.valueOf(8_500_000.0),
                "srv-08"
        ),
        new OrdemNegociacao(
                "ord-03",
                LocalDateTime.now().minusMinutes(5),
                OrdemNegociacao.StatusFinal.PENDENTE,
                Double.valueOf(320_000.0),
                "srv-02"
        )
    );

    public static final List<ItemOrdem> ITENS = List.of(
            new ItemOrdem("AAPL", 100L, new BigDecimal("150.00"), ORDENS.get(0).getId()),
            new ItemOrdem("JPM",  500L, new BigDecimal("17000.00"), ORDENS.get(1).getId()),
            new ItemOrdem("GOOGL", 80L, new BigDecimal("4000.00"),  ORDENS.get(2).getId())
    );

    public static final List<RegistroAuditoria> AUDITORIAS = List.of(
            new RegistroAuditoria(
                    RegistroAuditoria.TipoEvento.DEPLOY_INCONSISTENTE,
                    "Servidor srv-08 com versão v1.0.0 diverge da versão homologada v2.1.0.",
                    LocalDateTime.now().minusHours(1),
                    null // sem ordem associada - evento de infraestrutura
            ),
            new RegistroAuditoria(
                    RegistroAuditoria.TipoEvento.ORDEM_ENVIADA,
                    "Ordem enviada com sucesso pelo servidor srv-01.",
                    LocalDateTime.now().minusMinutes(30),
                    ORDENS.get(0).getId()
            ),
            new RegistroAuditoria(
                    RegistroAuditoria.TipoEvento.ANOMALIA_DETECTADA,
                    "Valor total de R$ 8.500.000,00 ultrapassa o limite financeiro de R$ 5.000.000,00.",
                    LocalDateTime.now().minusMinutes(20),
                    ORDENS.get(1).getId()
            ),
            new RegistroAuditoria(
                    RegistroAuditoria.TipoEvento.ORDEM_REJEITADA,
                    "Ordem rejeitada automaticamente por anomalia detectada no servidor srv-08.",
                    LocalDateTime.now().minusMinutes(20),
                    ORDENS.get(1).getId()
            ),
            new RegistroAuditoria(
                    RegistroAuditoria.TipoEvento.OPERACAO_INTERROMPIDA,
                    "Operações do servidor srv-08 interrompidas automaticamente.",
                    LocalDateTime.now().minusMinutes(19),
                    null
            )
    );
}