# UI Automated Testing Project for OTUS

This repository contains UI automation tests for the **OTUS learning platform** (https://otus.ru)  
using **Selenium WebDriver** with **Java**, **Cucumber BDD**, and **Page Object Model** with **Google Guice DI**. :contentReference[oaicite:1]{index=1}

---

## 📌 Overview

This project automates end-to-end UI scenarios, including:

* Searching for courses by exact name  
* Finding earliest and latest courses  
* Filtering and comparing preparatory courses (cheapest & most expensive) 

The automation framework is built using BDD principles with Cucumber feature files written in Gherkin. :contentReference[oaicite:2]{index=2}

---

## 🚀 Technology Stack

The project uses the following technologies:

| Category | Technology |
|----------|------------|
| Language | Java 24 |
| Test Framework | JUnit Jupiter 5 |
| BDD | Cucumber |
| Browser Automation | Selenium WebDriver 4 |
| Dependency Injection | Google Guice |
| HTML Parsing | Jsoup |
| Build Tool | Maven |
| Additional | WebDriverManager, AssertJ | :contentReference[oaicite:3]{index=3}

---

## 📁 Project Structure
project/
├── src/
│ ├── main/java/
│ │ ├── pages/ # Page Object classes
│ │ ├── extensions/ # JUnit extensions
│ │ ├── annotations/ # Custom annotations
│ │ ├── dto/ # Data Transfer Objects
│ │ └── waiters/ # Custom wait utilities
│ └── test/java/
│ └── main / # Cucumber step definitions & runners
├── pom.xml
└── README.md
