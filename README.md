# 🌐 Website Monitoring API

API REST para monitoramento de disponibilidade de websites, com execução automática via scheduler, histórico de verificações e documentação interativa com Swagger.

---

## 🚀 Badges

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3-green)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

---

## 📌 Sobre o projeto

Este projeto foi desenvolvido com o objetivo de monitorar a disponibilidade de websites de forma automatizada, registrando histórico de verificações e permitindo consultas via API REST.

Ideal para estudos de backend com foco em:
- arquitetura limpa
- boas práticas com Spring Boot
- testes automatizados
- integração com banco de dados

---

## 🛠️ Tecnologias utilizadas

- Java 21
- Spring Boot 3
- Spring Data JPA
- H2 (desenvolvimento)
- PostgreSQL (produção)
- Maven
- Lombok
- Swagger (OpenAPI)
- JUnit + Spring Boot Test

---

## ⚙️ Funcionalidades

- Cadastro de websites
- Ativação/desativação de monitoramento
- Verificação manual de status
- Monitoramento automático via scheduler
- Histórico de verificações
- Consulta de status atual
- Documentação interativa com Swagger
- Testes automatizados

---

## 🏗️ Arquitetura

```
controller → service → repository → database
```

---

## 📡 Endpoints principais

- POST `/websites`
- GET `/websites`
- POST `/monitoring/{websiteId}/check`
- GET `/monitoring/{websiteId}/records`
- GET `/monitoring/{websiteId}/status`

---

## 📖 Swagger

Acesse:

http://localhost:8080/swagger-ui/index.html

---

## 🚀 Como rodar o projeto

```bash
git clone https://github.com/mathhsilva/website-monitoring-api.git
cd website-monitoring-api
mvnw.cmd spring-boot:run
```

---

## 🧪 Rodar os testes

```bash
mvnw.cmd test
```

---

## 🗄️ Banco de dados

### Desenvolvimento
- H2 (em memória)

### Produção
- PostgreSQL

Configuração via variáveis de ambiente:

```
SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
```

---

## ⏱️ Scheduler

Configuração do monitoramento automático:

```
monitoring.scheduler.fixed-rate=15s
```

---

## 🔧 Configuração importante

Para compatibilidade com Swagger:

```
spring.mvc.pathmatch.matching-strategy=ant-path-matcher
```

---

## 📈 Melhorias futuras

- Autenticação com JWT
- Notificações (email/webhook)
- Dashboard com gráficos
- Dockerização
- CI/CD
- Monitoramento com Prometheus/Grafana

---

## 👨‍💻 Autor

Matheus Cordeiro

---

## 📢 Observação

Projeto desenvolvido para portfólio com foco em backend Java, boas práticas e arquitetura limpa.
