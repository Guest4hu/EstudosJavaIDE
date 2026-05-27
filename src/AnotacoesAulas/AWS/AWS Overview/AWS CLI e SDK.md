## 🖥️ Formas de Interagir com a AWS

> Tag: #cli #sdk | Relacionado: `[[AWS CLI e SDK]]`

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
