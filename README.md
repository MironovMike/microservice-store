# Общее описание
Проект магазина товаров, предназначенный для изучения различных технологий Java.

# Список сервисов
- *Store* - хранит товары, их свойства и цену в рублях. В зависимости от локали пользователя (определяет по заголовку Accept-Language) возвращает товар в рублях или, используя сервис *Rates*, в другой валюте. При этом Resilience4j реализует защиту от медленной работы сервиса *Rates* (CircuitBreaker), а также следующие средства повышения стабильности работы и защиты: @Bulkhead,  @RateLimiter, @Retry.
- *Rates* - сервис курса вылют.
- *Config-server* - хранит конфигурации всех сервисов (Spring Cloud Config Server).
- *Eureka-server* - discovery service.
- *Gateway* - проксирует все запросы к сервисам (используя Eureka-server для получения конечного адреса нужного сервиса). Добавляет в заголовок запроса уникальный id
 (если не задан). Добавляет его в заголовок ответа.

# Используемые технологии
JPA (Hibernate), Spring Actuator, Hibernate Validation, Spring Web, Spring Cloud Config, Slf4j, Lombok, Spring Cloud
 Load Balancer, Netflix Eureka, Eureka Discovery Client, Load Balanced Spring REST template, Resilience4j, Spring
  Gateway.