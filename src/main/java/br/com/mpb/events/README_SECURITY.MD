# 🔐 README - Segurança e Controle de Acesso

Este documento descreve a arquitetura de segurança implementada na aplicação, incluindo autenticação, autorização e controle de acesso via JWT.

---

## 🚀 Componentes Principais

### 1. 📌 AuthController
- Expõe os endpoints de **login**.
- Recebe as credenciais do usuário.
- Valida os dados e gera um **token JWT**.

```java
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User validUser = service.getUserByEmail(user.getEmail());

        if (validUser != null && passwordEncoder.matches(user.getPassword(), validUser.getPassword())) {
            return ResponseEntity.ok(JwtUtil.generateToken(validUser.getEmail()));
        } else {
            return ResponseEntity.status(401).build();
        }
    }
```

### 2. 🛡️ JwtFilter
- Intercepta todas as requisições.
- Extrai o token JWT do header `Authorization`.
- Valida o token e, se válido, popula o `SecurityContext` com os dados do usuário.

```java
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtUtil.validateToken(token)) {
                UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(jwtUtil.getUsername(token), null, List.of());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
```

### 3. 🔑 JwtToken
- Classe utilitária para **gerar** e **validar** tokens JWT.
- Define informações como:
    - `sub` → usuário autenticado
    - `exp` → tempo de expiração
    - Assinatura → garante integridade

```java
    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static String validateToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);
        return jwt.getSubject(); // retorna o usuário
    }
```

### 4. ⚙️ SecurityConfig
- Define as regras de segurança da aplicação.
- Configura:
    - Rotas públicas (`/auth/**`)
    - Rotas protegidas (demais endpoints)
    - Desabilita CSRF para uso em APIs REST.
    - Adiciona o filtro JWT antes do `UsernamePasswordAuthenticationFilter`.

```java
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // login liberado
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtFilter(), org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
```

### 5. 🌍 CorsConfig
- Controla quais origens podem acessar a API.
- Em desenvolvimento:
    - Libera `http://127.0.0.1:5500` (Live Server VSCode)
    - Libera `http://localhost:3000` (React ou outros frontends)
- Em produção:
    - Deve liberar apenas o domínio oficial do frontend.

```java
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://127.0.0.1:5500",   // Live Server do VSCode
                        "http://localhost:5500",   // alternativa
                        "http://localhost:3000"    // caso use React ou outro frontend
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
```

### 6. 🔐 BCrypt / PasswordEncoder
- Responsável por armazenar senhas de forma segura.
- Utiliza o algoritmo BCrypt, que aplica hash + salt automaticamente.
- Evita que senhas sejam armazenadas em texto puro no banco de dados.

#### Configuração no SecurityConfig

```java
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {
    
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    
        // resto da configuração...
    }
```

#### Uso no Service

```java
    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
```
#### AuthController
-  Durante o login, a senha informada é comparada com o hash armazenado usando:

```java
    if (validUser != null && passwordEncoder.matches(user.getPassword(), validUser.getPassword())) {
        return ResponseEntity.ok(JwtToken.generateToken(validUser.getEmail()));
        } else {
        return ResponseEntity.status(401).build();
        }
```
---

## 🔄 Fluxo de Autenticação

1. 👤 **Login**
    - Usuário envia email e senha para `/auth/login`.
    - O sistema valida e retorna um JWT.

2. 💾 **Armazenamento do Token**
    - O frontend guarda o token JWT (ex.: localStorage).
    - Postman é só copiar o token e colar nas requisições (para testes).

3. 📡 **Requisição Protegida**
    - O frontend envia o token no header:
      ```
      Authorization: Bearer <token>
      ```
4. ✅ **Validação**
    - O `JwtFilter` valida o token.
    - Se válido, o usuário acessa a rota protegida.

5. 🔒 **Controle de Acesso**
    - O `SecurityConfig` garante que apenas usuários autenticados acessem endpoints restritos.
    - O `CorsConfig` garante que apenas origens autorizadas consigam consumir a API.
    - O `BCrypt` garante que senhas nunca sejam expostas em texto puro.
---

## 📝 Resumo
Com os componentes atuais (AuthController, JwtFilter, JwtToken, SecurityConfig, CorsConfig e BCrypt), a aplicação já possui um núcleo sólido de autenticação, autorização e armazenamento seguro de credenciais. O próximo passo é fortalecer ainda mais a segurança com controle de perfis e refresh tokens.