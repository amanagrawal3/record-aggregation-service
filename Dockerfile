#### Stage 1: Build the application
FROM openjdk:17-oracle as build_maven

RUN mkdir -p /app
# Set the current working directory inside the image
WORKDIR /app
# Copy maven executable to the image
COPY mvnw .
COPY mvnw.cmd .

COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .


# Build all the dependencies in preparation to go offline.
# This is a separate step so the dependencies will be cached unless
# the pom.xml file has changed.
#RUN ./mvnw dependency:go-offline -B
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src src


# Package the application
RUN ./mvnw package

RUN ls -lart /app
RUN ls -lart /app/target
#RUN cd /app/target
RUN pwd

RUN pwd
RUN echo build_maven

EXPOSE 8080

COPY /target/record-aggregator-service-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8080", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
