# EcoMap: Interactive GIS Data Visualizer

**Course:** CPS209 — Project #2  
**Target Career Path:** GIS Specialist / Analyst

---

## 1. Project Description
**EcoMap** is a desktop Geographic Information Systems (GIS) application built using Java Swing and 2D Graphics. It models the core workflow of a GIS analyst: importing background spatial contexts (raster themes), managing layer visibilities, and manually digitizing geometric vector features (data points) via coordinate tracking. 

The application solves the problem of needing an interactive, lightweight spatial dashboard to log environmental observations on a Cartesian grid system mapped to simulated geographic coordinates.

---

## 2. Technical Requirements Checklist & Fulfillment

As per the project specification guidelines, the application satisfies the required criteria through the following technical components:

### Swing Components Used
* **`JComboBox<String>` (Theme Selection):** Allows the user to toggle between different map backgrounds (`Blueprint`, `Terrain`, and `Dark Mode`). This handles global visual state updates.
* **`JCheckBox` (Grid Visibility):** Acts as a GIS layer controller, turning the coordinate grid overlay on and off instantly.
* **`JLabel` (Coordinate Display):** Dynamically updates to show the simulated GPS coordinates (Latitude and Longitude) based on pixel positions.

### 2D Graphics Rendering (`JPanel`)
* The application features a custom `MapPanel` extending `JPanel`. 
* It overrides `paintComponent(Graphics g)` and utilizes `Graphics2D` to draw structural lines (the coordinate grid) using transparency and geometric markers (`fillOval` and `drawOval`) representing digitized geographic assets.

### Event Listeners
* **`ActionListener`:** Attached to both the `JComboBox` and `JCheckBox` to intercept state changes and immediately invoke `repaint()` to update the canvas.
* **`MouseListener` (MousePressed):** Allows user-driven interaction. Clicking anywhere on the canvas dynamically appends a new vector `Point` to an internal data structure and maps it visually.
* **`MouseMotionListener` (MouseMoved):** Captures pixel-relative movements over the coordinate space and translates them in real-time into mock decimal degrees via an algorithmic conversion factor.

---

## 3. How to Run and Use the Program

### Compilation & Execution
Ensure all project `.java` files are stored within the exact same directory (with no `package` headers included, matching the guidelines for submission). 

Compile and execute the driver file using your terminal or IDE:
```bash
javac Project2Runner.java
java Project2Runner
