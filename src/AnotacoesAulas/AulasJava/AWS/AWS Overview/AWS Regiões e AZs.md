## 🌍 Regiões e Availability Zones (AZ)

> Tag: #availability-zone | Relacionado: [[AWS Regiões e AZs]]

### Regiões (_Regions_)

Uma **Região** é um agrupamento geográfico de data centers da AWS, espalhados pelo mundo (ex: `us-east-1` → Norte da Virgínia, `sa-east-1` → São Paulo).

Cada região é **completamente isolada** das demais — dados em `sa-east-1` não saem dessa região sem configuração explícita.

**Critérios para escolher uma Região:**

- **Latência** — escolha a região mais próxima dos seus usuários
- **Compliance** — alguns dados precisam permanecer em território nacional (LGPD, GDPR)
- **Disponibilidade de serviços** — nem todos os serviços AWS estão em todas as regiões
- **Custo** — o preço varia entre regiões

### Availability Zones (AZs)

Uma **AZ (Zona de Disponibilidade)** é um ou mais data centers físicos dentro de uma mesma Região, com:

- Energia elétrica independente
- Refrigeração independente
- Conectividade de rede redundante
- Localização física separada (para resistir a desastres naturais)

```
Região: sa-east-1 (São Paulo)
│
├── AZ: sa-east-1a  →  Data Center A
├── AZ: sa-east-1b  →  Data Center B
└── AZ: sa-east-1c  →  Data Center C
```

> ⚠️ **Por que isso importa em código?**  
> Ao criar recursos como instâncias EC2 ou bancos RDS, você especifica em qual AZ eles ficam. Para **alta disponibilidade**, distribua réplicas em AZs diferentes. Se uma AZ cair, as outras assumem automaticamente.

**Exemplo prático (Java + AWS SDK):**

```java
// Criando uma instância EC2 em uma AZ específica
RunInstancesRequest request = RunInstancesRequest.builder()
    .imageId("ami-0abcdef1234567890")
    .instanceType(InstanceType.T2_MICRO)
    .placement(Placement.builder()
        .availabilityZone("sa-east-1a")  // AZ específica
        .build())
    .minCount(1)
    .maxCount(1)
    .build();
```
