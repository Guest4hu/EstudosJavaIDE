## 👤 AWS IAM — Identity and Access Management

> Tag: #iam | Relacionado: `[[AWS IAM — Identity and Access Management]]`

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