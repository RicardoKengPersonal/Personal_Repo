US232 – Show Request
==============================
---
# Analysis

---

## Business Rules

    The use case handles searching active public figures by: Category and/or keyword.

## Acceptance Criteria

    - The search should ignore accents and shouldn’t be case sensiƟve.

# Design

---

## Domain

Entity: Figure

Interface: FigureRepository

Domain Service: FigureManagementService

## Application

---

Controller: SearchPublicFiguresController

## UI (CLI/Backoffice)

---

    - Command in the menu: "Search Public Figures"
    - Input: code and/or keywords
    - Output: info about the code, description, version, designer, dsl version and visibility


## Testing

---
Unit Tests

    - Checks whether the figure and list were successfully created. Then, verifiy if the search was sucessfullt.


## Sequence Diagram

---

![diagram](/docs/us_232/us_232.svg)