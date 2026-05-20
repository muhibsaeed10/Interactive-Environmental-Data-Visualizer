# Wildfire Risk & Evacuation Buffer Analysis Tool

## 🌍 Project Overview
This project was developed for the **CPS109 (Introduction to Computer Science)** course. It functions as a prototype command-line Geospatial Analysis tool designed to simulate a real-world GIS (Geographic Information Systems) workflow: **Data Input -> Spatial Proximity Analysis -> Reporting**.

The script reads a dataset of critical infrastructure facilities (hospitals, schools, grids) along with their coordinate values, allows a user to input a dynamic hazard event (a wildfire location and a burn radius), and calculates vector-based proximity metrics to generate a risk evacuation report.

---

## 🛠️ Python Constructs Implemented
To satisfy the technical requirements of the CPS109 project criteria, this application demonstrates the usage of the following fundamental programming structures:

* **Variables & Data Assignment:** Tracks program states, user thresholds, and running analytics.
* **Mathematical Operations:** Uses the algebraic Distance Formula derived via the `math` library to determine geometric Euclidean distance:
    $$d = \sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}$$
* **Control Flow (if/elif/else):** Classifies facilities into distinct hazard operational tiers (`EVACUATE IMMEDIATELY`, `HIGH ALERT`, or `SAFE`) based on proximity boundaries.
* **Data Structures:** Organizes parsed coordinate records using multi-dimensional **lists** and geographic coordinate **tuples**.
* **Definite Loops (for):** Iterates across sequence datasets to sequentially parse inputs and compute geospatial distances.
* **Indefinite Loops (while):** Implements a primary execution terminal engine allowing analysts to run multiple simulation coordinates without resetting the script.
* **Modular Programming:** Implements a standalone custom function `calculate_distance()` to handle geometric calculation parameters.
* **File I/O Stream Handling:** Dynamically reads structural assets from an input flat file (`locations.txt`) and exports evaluated metrics into a new localized text asset (`evacuation_report.txt`).

---

## 📂 File Architecture
* `cps109_a1.py` - The core application python source script.
* `locations.txt` - Input dataset mapping critical infrastructure names to relative $(X, Y)$ spatial coordinates.
* `evacuation_report.txt` - Generated output reporting high-risk infrastructure flag locations needing dispatch support.

---

## 🚀 How To Run

1. Ensure you have **Python 3.x** installed.
2. Create a file named `locations.txt` in the root directory formatted with your target points-of-interest:
   ```text
   General_Hospital,12,15
   North_High_School,45,60
   City_Power_Grid,22,30
