FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# Install dependencies and Google Chrome
RUN apt-get update && \
    apt-get install -y wget unzip curl gnupg fonts-liberation libasound2 libgbm1 libnspr4 libnss3 xdg-utils && \
    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    apt-get install -y ./google-chrome-stable_current_amd64.deb && \
    rm google-chrome-stable_current_amd64.deb && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Copy Maven files and prefetch dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B || true

# Copy source code and testng suite
COPY src ./src
COPY testng.xml .

# Pre-compile test code
RUN mvn clean test-compile -DskipTests

# Run tests in headless mode by default
CMD ["mvn", "test", "-Dheadless=true"]