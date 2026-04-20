# Repository-Service Naming & Handling Convention

Este documento define um padrão recomendado para uso em projetos Spring Data, visando consistência, clareza e segurança.

## 📌 Convenção de Nomes

### Repository
- Sempre usar **findBy...**
- Segue o padrão do Spring Data, deixando claro que é uma query.

**Exemplos:**
```java
Optional<Session> findById(Integer id);
List<Session> findByConferenceId(Integer conferenceId);
```

### Service
- Usar `getBy...` ou `getAll...`
- No nível de negócio faz mais sentido falar “pegar” ou “obter”.

**Exemplos:**

```java
public Session getSessionById(Integer id);
public List<Session> getAllSessions();
```

## 📌 Tratamento de Ausência
### Unitário
- Usar `Optional<T>` no repository.
- No service decidir se lança exceção ou retorna valor default.

**Exemplo:**

```java
@Override
public Session getSessionById(Integer id) {
return repository.findById(id).orElseThrow(() -> 
        new NotFoundException("Session " + id + " not found"));
}
```

### Coleção
- Usar `List<T>` direto.
- Se não houver nada, retorna lista vazia (não precisa de `Optional<List<T>>`).

## 📌 Benefícios do Padrão

- Consistência: quem lê sabe que `findBy...` é query do banco e `getBy...` é regra de negócio.
- Clareza: fácil diferenciar infraestrutura (repository) de lógica de aplicação (service).
- Segurança: evita `NullPointerException` e força o tratamento de casos “não encontrado”.