ildar

spring boot, docker, validation, mockmvc, swagger
-
Валидатор проверяет по xSource DTO, 
либо xSource из ThreadLocal   
обеспечено масштабирование валидации полей за счет кастомной реализации
-
запуск ./install.sh или команды по очереди
1 сборка mvn clean package
2 докеризация docker build -t sprinbootcore:dev .
3 запуск бд в докере и приложения docker-compose up -d --build
