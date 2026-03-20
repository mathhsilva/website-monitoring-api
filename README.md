# 🌐 Website Monitoring API

API REST para monitoramento de disponibilidade de websites, com execução automática via scheduler, histórico de verificações e documentação interativa com Swagger.

---

## 🚀 Badges

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3-green)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Deploy](https://img.shields.io/badge/deploy-online-success)

---

## 📌 Sobre o projeto

Este projeto foi desenvolvido para monitorar a disponibilidade de websites através de uma API REST.

Possui:
- verificações manuais
- execução automática via scheduler
- armazenamento de histórico no banco de dados

Projeto voltado para portfólio backend, demonstrando:
- boas práticas com Spring Boot
- arquitetura em camadas
- integração com banco de dados
- testes automatizados
- deploy em produção

---

## 🌍 Aplicação em produção

🔗 API:
https://websitemonitoring.hinnovation.com.br

📄 Swagger:
https://websitemonitoring.hinnovation.com.br/swagger-ui/index.html

❤️ Healthcheck:
https://websitemonitoring.hinnovation.com.br/actuator/health

📦 Repositório:
https://github.com/mathhsilva/website-monitoring-api

---

## 🛠️ Tecnologias utilizadas

- Java 21
- Spring Boot 3
- Spring Data JPA
- H2 (desenvolvimento)
- PostgreSQL (produção)
- Maven
- Lombok
- Swagger / OpenAPI
- JUnit + Spring Boot Test
- Docker
- Coolify

---

## ⚙️ Funcionalidades

- Cadastro de websites
- Ativação e desativação de monitoramento
- Verificação manual de status
- Monitoramento automático (scheduler)
- Histórico de monitoramento
- Consulta de status atual
- Documentação Swagger
- Testes automatizados
- Deploy em produção

---

## 🏗️ Arquitetura

controller -> service -> repository -> database

---

## 📡 Endpoints principais

- POST `/api/websites`
- GET `/api/websites`
- GET `/api/websites/{id}`                                                          
- PATCH `/api/websites/{id}/active`
- POST `/api/websites/{websiteId}/check`
- GET `/api/websites/{websiteId}/records`
- GET `/api/websites/{websiteId}/status`

---

## 🚀 Como rodar localmente

```bash
git clone https://github.com/mathhsilva/website-monitoring-api.git
cd website-monitoring-api
mvnw.cmd spring-boot:run
```

---

## 🧪 Executar testes

```bash
mvnw.cmd test
```

---

## 🗄️ Banco de dados

### Desenvolvimento
- H2 em memória

### Produção
- PostgreSQL

Variáveis de ambiente:

SPRING_PROFILES_ACTIVE=prod  
SPRING_DATASOURCE_URL=  
SPRING_DATASOURCE_USERNAME=  
SPRING_DATASOURCE_PASSWORD=  

---

## ⏱️ Scheduler

Intervalo de monitoramento configurável:

monitoring.scheduler.fixed-rate=15s

---

## 📈 Melhorias futuras

- Autenticação JWT
- Notificações (email ou webhook)
- Dashboard com gráficos
- CI/CD
- Monitoramento com Prometheus/Grafana

---

## 👨‍💻 Autor

Matheus Cordeiro

---

## 📢 Observação

Projeto desenvolvido como portfólio backend para demonstrar construção de APIs REST, uso de scheduler, testes automatizados, documentação com Swagger e deploy em produção.
