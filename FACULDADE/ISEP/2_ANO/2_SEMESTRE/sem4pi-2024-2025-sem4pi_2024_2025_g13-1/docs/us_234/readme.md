US234 – Show Request
==============================
---
# Analysis

---

## Business Rules

    This user case consists of change the status of a figure from the catalog so that it is not used anymore.

## Acceptance Criteria


# Design

---

## Domain

Entity: Figure

Interface: FigureRepository

Domain Service: FigureService

## Application

---

Controller: DecommissionFiguresController

## UI (CLI/Backoffice)

---

    - Command in the menu: "Decommission Figure"
    - Input: code and version
    - Output: error or success message


## Testing

---
Unit Tests

    - Checks if the state change was successful


## Sequence Diagram

---

![diagram](/docs/us_234/us_234.svg)