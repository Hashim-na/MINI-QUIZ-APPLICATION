# Java Mini Quiz Application

A desktop-based interactive quiz application developed using **Java Swing** and **AWT**, structured with a clean, modular architecture. This project was built as part of the B.Sc. Multimedia mini project requirements.

## 👨‍🎓 Student Details
* **Name:** Hashim N A
* **Register Number:** 25AH024
* **Course:** B.Sc. Multimedia & Web Technology

---

## 🚀 Key Features
* **Modular Architecture:** Well-organized separation of concerns (`model`, `service`, `ui`).
* **Graphical User Interface (GUI):** Built using Java Swing with custom styling, radio buttons, and error validation prompts.
* **Scrollable HTML Review Summary:** Displays a comprehensive, question-by-question breakdown of user performance with correct/incorrect markers in a scrollable view upon quiz completion.
* **Two-Step Completion Flow:** Features an initial detailed results review window followed by a separate interactive confirmation dialog to restart or exit the application.
* **Dynamic Scoring & Remarks:** Automatically calculates scores and provides custom performance feedback remarks.

---

## 📂 Project Structure

QuizApplication/
│
├── src/                      # Source code root folder
│   ├── model/                # Data and Question storage classes
│   │   ├── Question.java
│   │   └── QuestionBank.java
│   │
│   ├── service/              # Business logic and state management
│   │   └── QuizService.java
│   │
│   └── ui/                   # Graphical User Interface (View)
│       └── QuizGUI.java
│
├── README.md                 # Project documentation for GitHub
└── .gitignore                # Files to ignore during git commit