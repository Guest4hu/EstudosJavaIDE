## 🔒 Segurança na AWS — Modelo de Responsabilidade Compartilhada

> Tag: #seguranca | Relacionado: [[Segurança em Nuvem]]

A AWS adota o **Modelo de Responsabilidade Compartilhada** (_Shared Responsibility Model_): a segurança é dividida entre a AWS e o cliente.

```
┌─────────────────────────────────────────────┐
│              RESPONSABILIDADE DO CLIENTE     │
│                                             │
│  • Dados e criptografia                     │
│  • Gerenciamento de identidade (IAM)        │
│  • Configuração de rede (Security Groups)   │
│  • Aplicações e sistemas operacionais       │
│  • Patches e atualizações na instância      │
├─────────────────────────────────────────────┤
│           RESPONSABILIDADE DA AWS           │
│                                             │
│  • Hardware físico dos data centers         │
│  • Infraestrutura global (rede, energia)    │
│  • Hipervisores e virtualização             │
│  • Segurança física dos data centers        │
│  • Software dos serviços gerenciados        │
└─────────────────────────────────────────────┘
```

**Resumo prático:**

- A AWS protege **"a nuvem"** (infraestrutura física e serviços)
- O cliente protege **"o que está na nuvem"** (dados, acessos, configurações)

> ⚠️ **Erro comum:** Achar que a AWS cuida de tudo. Se você configurar mal um bucket S3 como público, a AWS não vai bloqueá-lo automaticamente — essa é **sua responsabilidade**.
