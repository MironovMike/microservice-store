# Общее описание
Проект магазина товаров, предназначенный для изучения различных технологий Java.

# Список сервисов
- *Store* - хранит товары, их свойства и цену в рублях. В зависимости от локали пользователя (определяет по заголовку Accept-Language) возвращает товар в рублях или, используя сервис *Rates*, в другой валюте.
- *Rates* - сервис курса вылют.
- *Config-server* - хранит конфигурации всех сервисов (Spring Cloud Config Server)
- *eureka-server* - discovery service.

# Используемые технологии
JPA (Hibernate), Spring Actuator, Hibernate Validation, Spring Web, Spring Cloud Config, Slf4j, Lombok, локализация запросов на основе заголовка Accept-Language, Spring Cloud Load Balancer, Netflix Eureka, Eureka Discovery Client