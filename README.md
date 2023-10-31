# Documentação da Aplicação Softplan

## Índice

- [Introdução](#introdução)
- [Tecnologias e Dependências](#tecnologias-e-dependências)
- [Arquitetura](#arquitetura)
- [Modelo de Dados](#modelo-de-dados)
- [Segurança](#segurança)
- [Elasticidade e Escalabilidade](#elasticidade-e-escalabilidade)
- [Monitoramento](#monitoramento)
- [Considerações Finais](#considerações-finais)

## Introdução

A aplicação **Softplan** foi concebida para estabelecimentos com múltiplas filiais. Esta solução facilita o gerenciamento de mesas, pedidos e transações, proporcionando uma interface amigável e intuitiva para os garçons e outros funcionários.

## Tecnologias e Dependências

A aplicação Softplan é fundamentada em tecnologias modernas para fornecer uma experiência de alta qualidade. Abaixo estão as tecnologias-chave e uma explicação sobre por que foram escolhidas:

- **Spring Boot 3.1.5**: A base da aplicação, o Spring Boot é uma plataforma baseada em Java que acelera o desenvolvimento de microserviços, automatizando configurações e facilitando a criação de aplicações stand-alone. Escolhido por sua maturidade e ampla comunidade.

- **Java 17**: A versão mais recente do Java, oferecendo melhorias de desempenho, novos recursos e atualizações de segurança. Foi escolhido por sua robustez, desempenho e segurança.

- **Spring Web**: Esta dependência permite o desenvolvimento de APIs RESTful de alta performance. Escolhida pela sua facilidade de integração e eficiência na criação de endpoints.

- **Spring Data JPA**: Permite a fácil integração com bancos de dados relacionais usando Java Persistence API (JPA). Foi escolhido por simplificar o acesso ao banco de dados e operações CRUD.

- **Liquibase**: Ferramenta de gerenciamento de banco de dados que ajuda a manter o controle de versão do schema. Escolhido por sua capacidade de rastrear e aplicar mudanças no banco de dados de forma organizada.

- **PostgreSQL**: Um dos bancos de dados relacionais open-source mais avançados. Optou-se por ele devido à sua confiabilidade, escalabilidade e recursos avançados.

- **SpringDoc OpenAPI**: Oferece uma maneira fácil de documentar e testar APIs RESTful. Escolhido por sua integração direta com Spring Boot e capacidade de autogerar documentação.

- **Spring Security**: Um dos frameworks mais robustos para segurança em aplicações Java, oferecendo autenticação, autorização e proteção contra ataques comuns. Foi a escolha natural devido à sua compatibilidade com Spring Boot e sua reputação no mercado.

- **Spring Websocket**: Facilita a comunicação em tempo real entre o servidor e os clientes. Escolhido para fornecer notificações em tempo real na aplicação.

- **Spring Data Redis**: Fornece fácil integração com Redis, um armazenamento de dados em memória. Escolhido por sua rapidez e capacidade de reduzir a latência através do cache.

- **AWS SDK & Spring Cloud AWS**: Estas bibliotecas permitem a integração direta com a plataforma de nuvem AWS, oferecendo capacidades de escalabilidade, armazenamento e outros serviços. Escolhido pela popularidade e robustez da AWS.

- **MercadoPago SDK**: SDK que facilita a integração com o sistema de pagamentos do MercadoPago. Escolhido por ser uma das plataformas de pagamento mais populares e confiáveis na América Latina.


## Arquitetura da Aplicação Softplan

A aplicação Softplan é um sistema projetado para gerenciamento de restaurantes, mesas, pedidos e pagamentos. Ela foi estruturada seguindo os princípios de microserviços, garantindo modularidade, escalabilidade e fácil manutenção.

### Base e Estrutura

- **Spring Boot**: O núcleo da aplicação. O Spring Boot é notório por fornecer uma estrutura sólida para criação de microserviços, facilitando a configuração e o bootstrapping de aplicações modernas. Toda a configuração é externalizada, permitindo alterações rápidas sem a necessidade de reconstrução do projeto.

### Armazenamento e Persistência

- **PostgreSQL**: Usado como sistema de gerenciamento de banco de dados relacional, é um dos mais robustos e confiáveis no mercado. Além disso, a integração com **Spring Data JPA** permite um mapeamento objeto-relacional eficiente, simplificando a criação e gerenciamento de entidades.

- **Liquibase**: Garante a evolução consistente da base de dados. Através do controle de versão do schema, é possível garantir que alterações sejam aplicadas corretamente em diferentes ambientes, desde o desenvolvimento até a produção.

- **Spring Data Redis**: Utilizando o Redis como cache, a aplicação pode aumentar significativamente sua velocidade de resposta para solicitações frequentes e diminuir a carga sobre o banco de dados principal.

### Comunicação e Interface

- **Spring Web**: A aplicação expõe seus serviços como APIs RESTful. Esta abordagem garante interoperabilidade, facilitando integrações com outros sistemas ou interfaces front-end.

- **Spring Websocket**: Para garantir comunicações em tempo real, como notificações de novos pedidos para os garçons, a aplicação utiliza WebSockets. Isso proporciona uma experiência mais dinâmica e responsiva para os usuários.

### Segurança

- **Spring Security**: Garante que todas as interações com a aplicação sejam seguras. Desde a autenticação de usuários até a proteção contra ataques comuns, como CSRF e SQL Injection, a aplicação está blindada contra ameaças.

### Pagamento e Integração

- **MercadoPago SDK**: A integração com o MercadoPago permite processar pagamentos de forma eficiente e segura, garantindo uma ampla variedade de métodos de pagamento para os clientes.

### Monitoramento e Manutenção

- **Spring Boot Actuator**: Proporciona insights valiosos sobre o estado e saúde da aplicação. Ele expõe várias métricas e informações, essenciais para monitoramento em tempo real e diagnóstico de possíveis problemas.

### Integração com Cloud

- **AWS SDK & Spring Cloud AWS**: Preparando-se para o futuro, a aplicação tem todas as ferramentas necessárias para integração com a Amazon Web Services (AWS). Seja para armazenamento, processamento ou escalabilidade, a AWS oferece uma gama de serviços que a aplicação pode explorar.

A escolha desta combinação de tecnologias e frameworks visa não apenas atender aos requisitos atuais da aplicação, mas também prepará-la para desafios futuros, assegurando que ela permaneça atualizada, escalável e manutenível.

## Considerações Finais

A aplicação Softplan é robusta, escalável e concebida para atender às necessidades contemporâneas de estabelecimentos com filiais múltiplas, tornando a gestão mais eficiente e intuitiva.
