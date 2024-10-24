# Enuygun - QA Engineer Case Study

---

## Overview

This repository contains an automation framework that may provide end-to-end testing of a Enuygun web application.

The framework utilizes:
- **Selenium WebDriver** for browser automation.
- **TestNG** as the testing framework.
- **Page Object Model** to make the code more readable and maintainable.
- **Maven** for managing project dependencies and running tests.
- **Extent Report**  as the reporting tool.

---

## Features

1. **Parameterized Locators**: Locators for elements are dynamically generated based on passenger count and type.
2. **Dynamic Input Handling**: Supports dynamic passenger types and flexible form filling.
3. **Flight Search and Filters**: Automates the search process for flights, including ticket price filtering, time filtering, airlines.
4. **Confirm Payment**: Completes the payment with valid user card information. 

---

## Getting Started

### Prerequisites

- Java 11+
- Selenium 4+
- Maven 3.6+
- TestNG 7+
- Latest stable browser version of Chrome WebDriver.

---

### Installation

#### Step-1: Clone the repository
git clone https://github.com/efisek/enuygun-qa.git

cd enuygun-qa

#### Step-2: Install dependencies
mvn clean install

#### Step-3: Run tests
mvn test

---

### Key Components:

- **BaseTest**: Contains setup, teardown logic for WebDriver, captures screenshots on failed tests.
- **BasePage**: It works as a foundation of the other pages, including common methods to interact with web elements. 
- **HomePage**: It handles flight search, including selecting ports, passengers, cabin class, and trip type.
- **FlightSearchPage**: Includes methods to apply different filters, choose targeted flights.
- **FlightReservationPage**: Dynamically fills the contact info, and passenger details based on the number of passengers and their types.
- **PaymentPage**: Appropriate to pay by card.
- Reports and screenshots can be found under the **test-output** folder.
- The source code is in **main** branch.

---

## License

This project is licensed under the MIT License.

---

## Contributors

Emre Fişek
