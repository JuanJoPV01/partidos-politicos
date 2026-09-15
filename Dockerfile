# Etapa 1: Construcción (Build) con Maven
FROM maven:3.8.5-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Etapa 2: Ejecución (Run) con Apache Tomcat 9
FROM tomcat:9.0-jdk11-corretto
# Limpiar las apps por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*
# Copiar el archivo WAR generado y renombrarlo a ROOT.war para que corra en la raíz (/)
COPY --from=build /app/target/partidopolitico-app.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
