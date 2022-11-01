#### Stage 1: Build the application
FROM openjdk:17-oracle as build_maven

# Set the current working directory inside the image
WORKDIR /app
# Copy maven executable to the image
COPY mvnw .
COPY mvnw.cmd .

COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# cleaning mvnw file by removing spaces and CR characters from the file
RUN sed -i 's/\r$//' mvnw
# Build all the dependencies in preparation to go offline.
# This is a separate step so the dependencies will be cached unless
# the pom.xml file has changed.
#RUN ./mvnw dependency:go-offline -B
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src src


# Package the application
RUN ./mvnw package
EXPOSE 8080

#WORKDIR /tmp
# cp jar build internally in container and not from external FS
RUN cp /app/target/record-aggregator-service*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8080", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
