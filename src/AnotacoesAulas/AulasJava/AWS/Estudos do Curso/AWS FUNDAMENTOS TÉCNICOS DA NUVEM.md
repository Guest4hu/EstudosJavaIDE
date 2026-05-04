
# ☁️ AWS — Fundamentos Técnicos da Nuvem

> **Status:** `complementado` | **Curso:** AWS Fundamentos Técnicos da Nuvem | **Módulo:** 1  
> **Tipo:** teórica — infraestrutura, acesso, segurança e identidade

---

## 🧭 Visão Geral

A AWS (Amazon Web Services) é uma plataforma de computação em nuvem que oferece infraestrutura sob demanda — servidores, armazenamento, rede e serviços gerenciados — pagando apenas pelo que for usado (_pay-as-you-go_).

**Três modelos de entrega de nuvem:**

|Modelo|O que é|Exemplos AWS|
|---|---|---|
|**IaaS** _(Infrastructure as a Service)_|Infraestrutura bruta — servidores, rede, armazenamento|EC2, S3, VPC|
|**PaaS** _(Platform as a Service)_|Plataforma para rodar aplicações sem gerenciar infra|Elastic Beanstalk, RDS|
|**SaaS** _(Software as a Service)_|Software pronto para uso final|Amazon WorkMail, Chime|

---

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

---

## 🖥️ Formas de Interagir com a AWS

> Tag: #cli #sdk | Relacionado: [[AWS CLI e SDK]]

Existem três formas principais de interagir com os serviços AWS, todas se comunicam com a mesma **AWS API REST** por baixo dos panos.

### 1. Console Web (AWS Management Console)

Interface gráfica acessível pelo navegador em [console.aws.amazon.com](https://console.aws.amazon.com/).

- ✅ Ideal para **exploração**, aprendizado e operações pontuais
- ✅ Não exige conhecimento de sintaxe de comandos
- ❌ Não é reproduzível — dificulta automação e rastreabilidade

### 2. AWS CLI (Command Line Interface)

Ferramenta de linha de comando instalada localmente que permite executar qualquer ação da AWS via terminal.

```bash
# Listar buckets S3
aws s3 ls

# Criar um bucket S3 na região de São Paulo
aws s3api create-bucket \
    --bucket meu-bucket-exemplo \
    --region sa-east-1 \
    --create-bucket-configuration LocationConstraint=sa-east-1

# Descrever instâncias EC2
aws ec2 describe-instances --region sa-east-1
```

- ✅ Permite **scripts e automação** (shell scripts, pipelines CI/CD)
- ✅ Reproduzível e versionável
- ❌ Requer configuração prévia de credenciais (`aws configure`)

### 3. AWS SDK (Software Development Kit)

Permite integrar serviços AWS **diretamente no código** da aplicação. Disponível para Java, Python, Node.js, Go, .NET, entre outros.

**Exemplo em Java — listando buckets S3:**

```java
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

S3Client s3 = S3Client.builder()
    .region(Region.SA_EAST_1)
    .build();

ListBucketsResponse response = s3.listBuckets();
response.buckets().forEach(bucket ->
    System.out.println("Bucket: " + bucket.name())
);
```

> ⚠️ **Dependência Maven para o AWS SDK v2 em Java:**
> 
> ```xml
> <dependency>
>     <groupId>software.amazon.awssdk</groupId>
>     <artifactId>s3</artifactId>
>     <version>2.25.0</version>
> </dependency>
> ```

|Forma de Acesso|Melhor para|Autenticação|
|---|---|---|
|**Console Web**|Exploração, aprendizado, operações únicas|Usuário/senha + MFA|
|**CLI**|Scripts, automação, pipelines|Access Key + Secret Key|
|**SDK**|Integração na aplicação|Access Key, IAM Role, ou credencial temporária|

---

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

---

## 👤 AWS IAM — Identity and Access Management

> Tag: #iam | Relacionado: [[AWS IAM — Identity and Access Management]]

O **IAM** é o serviço central de controle de **identidade e acesso** da AWS. Ele responde a duas perguntas fundamentais:

- **Autenticação:** _Quem é você?_
- **Autorização:** _O que você pode fazer?_

### Componentes do IAM

|Componente|O que é|
|---|---|
|**Root Account**|Conta raiz criada ao abrir a AWS — acesso total. Nunca usar no dia a dia|
|**IAM User**|Usuário individual com credenciais próprias (pessoa ou sistema)|
|**IAM Group**|Grupo de usuários que compartilham as mesmas permissões|
|**IAM Role**|Conjunto de permissões temporárias assumidas por serviços ou usuários|
|**IAM Policy**|Documento JSON que define **o que** pode ou não ser feito|

### IAM Policy — Estrutura

Toda permissão na AWS é definida por uma **Policy JSON**:

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "s3:GetObject",
        "s3:PutObject"
      ],
      "Resource": "arn:aws:s3:::meu-bucket/*"
    },
    {
      "Effect": "Deny",
      "Action": "s3:DeleteObject",
      "Resource": "*"
    }
  ]
}
```

**Campos principais:**

- `Effect` → `Allow` ou `Deny`
- `Action` → qual ação é permitida/negada (ex: `s3:GetObject`, `ec2:StartInstances`)
- `Resource` → em qual recurso se aplica (ARN específico ou `*` para todos)

> ⚠️ **Regra de ouro do IAM: Princípio do Menor Privilégio (_Least Privilege_)**  
> Conceda **apenas** as permissões necessárias para a tarefa. Nunca use permissões de `*` em produção sem necessidade.

### IAM Role — Para Aplicações Java na AWS

Em vez de colocar `Access Key` e `Secret Key` hardcoded no código (❌ péssima prática), use **IAM Roles** para aplicações rodando em EC2, Lambda, ECS etc.:

```java
// ✅ CORRETO — SDK detecta a Role automaticamente via credencial do ambiente
S3Client s3 = S3Client.builder()
    .region(Region.SA_EAST_1)
    .credentialsProvider(InstanceProfileCredentialsProvider.create()) // usa a Role da EC2
    .build();

// ❌ ERRADO — nunca faça isso em código de produção
S3Client s3 = S3Client.builder()
    .credentialsProvider(StaticCredentialsProvider.create(
        AwsBasicCredentials.create("AKIAIOSFODNN7EXAMPLE", "minha-secret-key-exposta")
    ))
    .build();
```

### Toda Ação na AWS é Assinada

Toda requisição à AWS API é autenticada com **AWS Signature Version 4 (SigV4)** — um processo de assinatura criptográfica que garante:

1. **Autenticidade** — a requisição veio de quem diz ser
2. **Integridade** — o conteúdo não foi alterado em trânsito
3. **Prevenção de replay attacks** — inclui timestamp, requisições antigas são rejeitadas

> **Na prática:** O SDK e a CLI fazem isso automaticamente. Você nunca precisa assinar manualmente — mas é importante entender que acontece por baixo dos panos em toda chamada de API.

---

## 🔗 Mapa de Conexões

```
AWS Fundamentos Técnicos
│
├── Infraestrutura
│   ├── [[AWS Regiões e AZs]]
│   └── [[Cloud Computing — Conceitos Gerais]]
│
├── Acesso e Ferramentas
│   ├── [[AWS CLI e SDK]]
│   └── Java → [[AWS SDK para Java]]
│
├── Segurança
│   ├── [[Segurança em Nuvem]]
│   ├── Shared Responsibility Model
│   └── [[AWS IAM — Identity and Access Management]]
│
└── Identidade
    ├── IAM Users, Groups, Roles, Policies
    └── AWS Signature V4
```

---

