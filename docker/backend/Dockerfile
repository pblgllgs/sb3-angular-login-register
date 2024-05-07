FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:17
ARG PROFILE=dev
ARG APP_VERSION=${VERSION}
WORKDIR /app
COPY --from=build /build/target/backend-*.jar /app
EXPOSE 8088

ENV DB_URL=jdbc:postgresql://postgres_db_app:5432/db_app
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

CMD java -jar -Dspring.datasource.url=${DB_URL} -Dspring.profiles.active=${ACTIVE_PROFILE} /app/backend-${JAR_VERSION}.jar