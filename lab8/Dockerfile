FROM eclipse-temurin:17-jdk-jammy

# Создание и установка рабочей директории
WORKDIR /app

# Копирование jar-файла в контейнер
COPY target/student-app-1.0.jar /app/student-app.jar

# Открытие порта для приложения
EXPOSE 8443

# JVM параметры
ENTRYPOINT ["java", "-Duser.language=ru", "-jar", "/app/student-app.jar"]
