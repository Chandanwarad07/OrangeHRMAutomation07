FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    apt-get install -y ./google-chrome-stable_current_amd64.deb && \
    rm google-chrome-stable_current_amd64.deb

COPY . .

RUN mvn clean install -DskipTests

CMD ["mvn","test"]