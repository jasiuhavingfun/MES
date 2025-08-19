# Finite Element Method (FEM)

## 📖 Overview

This project is an implementation of the **Finite Element Method (FEM)** in **Java**, created as part of an educational course.
It allows users to run FEM simulations using **text-based input files** that define simulation parameters, mesh geometry, material properties, and boundary conditions.

---

## ✨ Features

* Implementation of FEM in **Java**
* Reads structured **input files** (see `Test1_4_4.txt` as an example)
* Supports:

  * Material properties (conductivity, density, specific heat, etc.)
  * Simulation parameters (time, step size)
  * Node definitions with coordinates
  * Element connectivity
  * Boundary conditions

---

## 🛠 Technologies

* **Language:** Java

---

## 📂 Project Structure

```
/src                     # Java source code
/src/Test1_4_4.txt       # Example input file
.gitignore
```

---

## 🚀 Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/jasiuhavingfun/MES.git
   cd MES
   ```
2. Open the project in **IntelliJ IDEA** (or another Java IDE).
3. Build and run the main Java class (look for a `main` method inside `src/`).
4. Run the program with the example input file:

   ```bash
   java main src/Test1_4_4.txt
   ```

---

## 📑 Input File Format

Example: [`Test1_4_4.txt`](src/Test1_4_4.txt)

```txt
SimulationTime 500
SimulationStepTime 50
Conductivity 25
Alfa 300
Tot 1200
InitialTemp 100
Density 7800
SpecificHeat 700
Nodes number 16
Elements number 9
*Node
1, 0.1, 0.005
...
*Element, type=DC2D4
1, 1, 2, 6, 5
...
*BC
1, 2, 3, 4, 5, 8, ...
```

### Sections

* **Simulation parameters:** total time, step size, material constants
* **Nodes:** numbered list of coordinates
* **Elements:** connectivity between nodes (here: 4-node 2D elements)
* **BC (Boundary Conditions):** list of nodes with prescribed conditions

Users can create their own `.txt` files in the same format to simulate different meshes and material setups.

---

## 📜 License

This project was created for educational purposes.
Feel free to use or modify for learning or coursework.

---

## 👤 Author

Created by **[@jasiuhavingfun](https://github.com/jasiuhavingfun)**
