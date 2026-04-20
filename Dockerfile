FROM mcr.microsoft.com/playwright/java:v1.58.0-noble

WORKDIR /app

COPY . /app

RUN mkdir -p /app/target/allure-results

CMD mvn test \
      -Dmaven.test.failure.ignore=true \
      -Dallure.results.directory=target/allure-results