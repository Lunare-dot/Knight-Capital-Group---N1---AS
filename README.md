# KCG Trading System — Simulação Arquitetural

Simulação do sistema de trading automatizado da **Knight Capital Group (KCG)**, desenvolvida como parte do Trabalho de Arquitetura de Software — N1, do curso de Engenharia de Software da Universidade Católica de Brasília.

O objetivo não é replicar o sistema real, mas demonstrar como a aplicação de padrões arquiteturais e requisitos de segurança teria mitigado o colapso de US$ 440 milhões ocorrido em 1º de agosto de 2012.

---

## Tecnologias

- Java 17+
- Spring Boot 3.x
- Maven
- Postman (para testes REST)

---

## Arquitetura

O sistema segue o modelo **Client-Server**, onde a aplicação Spring Boot atua como servidor REST e os testes (Postman/Newman) representam o cliente/sistema consumidor da API, realizando requisições HTTP para os endpoints expostos.

Internamente, o projeto adota uma **Arquitetura em Camadas (N-tier Layered)**, organizada nos seguintes pacotes:

```
com.kcg.trading_system
├── controller      # Endpoints REST (DeployController, TradingController)
├── service         # Lógica de negócio (TradingService, AuditoriaService)
├── model           # Entidades de domínio (Servidor, OrdemNegociacao, etc.)
├── policy          # Regra de segurança centralizada (SecurityPolicyManager)
├── dto             # Objetos de transferência (OrdemRequestDTO, OrdemResponseDTO)
├── mock            # Dados simulados (MockData)
└── exception       # Exceções de domínio personalizadas
```

### Design Patterns utilizados

| Pattern | Onde | Propósito |
|---|---|---|
| **Singleton** | `SecurityPolicyManager` | Garante política de segurança única e consistente em todos os servidores |
| **Strategy** | Métodos `is*` do `SecurityPolicyManager` | Encapsula regras de validação intercambiáveis |
| **Service Layer** | `TradingService` / `AuditoriaService` | Separa orquestração de negócio do registro de eventos |
| **DTO** | `OrdemRequestDTO` / `OrdemResponseDTO` | Desacopla a API pública do modelo interno de domínio |

---

## Como executar

### Pré-requisitos

- JDK 17 ou superior instalado
- Maven instalado (`mvn -v` para verificar)

### Passos

```bash
# Clone o repositório
git clone <url-do-repositorio>
cd trading-system

# Compile e execute
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

---

## Diagramas UML

Os diagramas da solução estão disponíveis em:
``
`/docs/uml/`
``

### Incluem:
```
- DER (Entidade-Relacionamento)
- Diagrama de Classe
- Diagrama de Caso de Uso
- Diagrama de Atividade
- Diagrama Arquitetural N-Tier
```
---

## Testes Automatizados (Postman + Newman)
### Localização dos arquivos
``
`/postman/collections/trading_tests.json`
``

---

## Importante - Postman

### Não atualizar a collection para v3 (YAML).

O Postman pode exibir o aviso:
```
"Upgrade files - Postman no longer supports JSON..."
```

### IGNORE esse aviso.

Atualizar para v3:

- pode corromper o arquivo
- pode transformar o `trading_tests.json` em diretório
- quebra a execução via Newman

---

## Executando os testes

### Instalar o Newman:
</> Bash
```
npm install -g newman
```

### Executar:
</> Bash
```
newman run postman/collections/trading_tests.json
```

---

## Cenário simulado (MockData)

O `MockData` simula fielmente o contexto do desastre KCG:

- **8 servidores** registrados: 7 na versão homologada `v2.1.0` e 1 (`srv-08`) na versão legada `v1.0.0`
- **Limite financeiro:** R$ 5.000.000,00 por ordem (definido na `PoliticaSeguranca`)
- **3 ordens pré-carregadas:**
  - `ord-01` — aprovada, R$ 150.000,00, servidor `srv-01`
  - `ord-02` — rejeitada, R$ 8.500.000,00 (acima do limite), servidor `srv-08` (legado)
  - `ord-03` — pendente, R$ 320.000,00, servidor `srv-02`
- **5 registros de auditoria iniciais** correspondendo ao fluxo do desastre original

---

## Autores

- Leydson Douglas
- Pedro Lucas Lopes de Melo
- Pedro Eduardo da Costa Nascimento
- Luiz Gustavo Carvalho de Brito
- Matheus Ferreira Souza
- Pedro Ferraz de Sousa Pereira

**Orientador:** Jefferson Salomão Rodrigues  
**UCB — Brasília, 2026**