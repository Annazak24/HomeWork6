# Homework #6 — UI Automation with Playwright

## Description
UI test automation project implemented using **Playwright (Java)** as part of Homework #6.

The project follows **Page Object Model**, uses **Dependency Injection (Guice)** and fully complies with the homework requirements.

---

## Implemented Scenarios
- Teachers block interaction
- Courses catalog filtering
- Company services navigation
- Subscription and payment flow

---

## Technologies
- Java
- Playwright
- JUnit 5
- Guice (Dependency Injection)
- Maven

---

## Dependency Injection
The project uses **Guice** for Dependency Injection.

All Playwright resources (Browser, Context, Page) and Page Objects are created in fixtures and injected into tests via DI.  
The `@UsePlaywright` annotation is **not used**.

---

## Project Structure
- `pages` — Page Object classes
- `extensions` — Playwright lifecycle management
- `modules` — Guice configuration
- `tests` — UI tests

---

## Playwright Tracing
Playwright tracing is enabled for all tests.  
Trace archives are stored in the project root.

---

## Run Tests
Tests can be executed from the console:

```bash
mvn clean test
