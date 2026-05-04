#  Classes e Objetos
- **Classe** → o "molde" — representação de algo ou conceito
- **Objeto** → o "produto do molde" — instância específica de uma classe

**Exemplo:** `Figo` e `Rex` são cachorros distintos, mas ambos são instâncias da mesma classe `Cachorro`.

```java
// Classe = molde
public class Cachorro {
    String nome;
    double peso;
}

// Objetos = instâncias
Cachorro figo = new Cachorro();
Cachorro rex  = new Cachorro();
```
## ✅ Benefícios de Utilizar POO

- **Reutilização** — atributos e métodos compartilhados entre hierarquias
- **Código genérico** — classes pai fornecem base aproveitada pelas filhas
- **Manutenção fácil** — alterar a classe pai reflete em todas as filhas
- **Organização** — código mais próximo do mundo real

# 🔁 Herança e Polimorfismo

> Relacionado: [[POO — Conceitos Gerais]] | Tags: #herança #polimorfismo

A classe pai `Animal` possui:

- **Atributos:** `nome`, `peso`
- **Métodos:** `comer()`, `respirar()`

As classes `Cachorro` e `Peixe` **herdam** esses membros, mas **sobrescrevem** os métodos conforme necessário.

```java
public class Animal {
    String nome;
    double peso;
    void comer()    { System.out.println("Comendo..."); }
    void respirar() { System.out.println("Respirando..."); }
}

public class Cachorro extends Animal {
    @Override
    void respirar() { System.out.println("Respirando pelo pulmão!"); }
    void correrAtrasDeCarros() { System.out.println("Au au!"); }
}

public class Peixe extends Animal {
    @Override
    void respirar() { System.out.println("Respirando pelas guelras!"); }
    void nadar() { System.out.println("Nadando..."); }
}
```

### Duas formas de Polimorfismo em Java

|Tipo|O que é|
|---|---|
|**Sobrescrita (Override)**|Classe filha reescreve o comportamento do método herdado|
|**Sobrecarga (Overload)**|Mesmo método com parâmetros diferentes na mesma classe|

> ⚠️ **Polimorfismo** vem do grego: _"muitas formas"_. A JVM decide em tempo de execução qual método chamar com base na **classe real do objeto**.