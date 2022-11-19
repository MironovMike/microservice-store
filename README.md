# Общее описание
Проект магазина товаров, предназначенный для изучения различных технологий Java.

# Список сервисов
- *Store* - хранит товары, их свойства и цену в рублях. В зависимости от локали пользователя (определяет по заголовку Accept-Language) возвращает товар в рублях или, используя сервис *Rates*, в другой валюте. При этом Resilience4j реализует защиту от медленной работы сервиса *Rates* (CircuitBreaker), а также следующие средства повышения стабильности работы и защиты: @Bulkhead,  @RateLimiter, @Retry. Для доступа к сервису *Store* необходим JWT, который можно запросить у сервиса *Keycloak*. При этом *Store* делает запросы к *Rates* через *Gateway*. *Store* также передает JWT во все свои запросы, а также заголовок request-id. По request-id можно отследить все дочерние запросы сервисов, тем самым связав их к конкретному запросу клиента приложения.
- *Rates* - сервис курса вылют. Доступ к ендпоинтам реализован по JWT (который также можно получить у *Keycloak*).
- *Config-server* - хранит конфигурации всех сервисов (Spring Cloud Config Server).
- *Eureka-server* - discovery service.
- *Gateway* - проксирует все запросы к сервисам (используя Eureka-server для получения конечного адреса нужного сервиса). Добавляет в заголовок запроса уникальный request-id (если запрос поступил без него).
- *Keycloak* - менеджер прав пользователей. Хранит пользователей их роли. Генерирует и производит валидацию JTW-токенов.
- *Mysql* - база данных товаров.

# Используемые технологии
JPA (Hibernate), Spring Actuator, Hibernate Validation, Spring Web, Spring Cloud Config, Slf4j, Lombok, Spring Cloud
 Load Balancer, Netflix Eureka, Eureka Discovery Client, Load Balanced Spring REST template, Resilience4j, Spring
  Gateway, Keycloak.