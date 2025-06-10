# Use Maven with Java 17
FROM maven:3.9.2-eclipse-temurin-17

# Install system dependencies required by Playwright browsers
RUN apt-get update && apt-get install -y wget gnupg ca-certificates \
    && apt-get install -y \
    libnss3 libatk1.0-0 libatk-bridge2.0-0 libcups2 libdrm2 libxkbcommon0 \
    libxcomposite1 libxdamage1 libxrandr2 libgbm1 libpango-1.0-0 libpangocairo-1.0-0 \
    libasound2 libxss1 libgtk-3-0 libx11-xcb1 fonts-liberation lsb-release xdg-utils \
    && apt-get clean

# Set work directory
WORKDIR /app

# Copy all project files into container
COPY . .

# Install Playwright browsers (important!)
RUN mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"

# Run the tests
CMD ["mvn", "clean", "test"]
