# Design Document

**Author**: Team80

## 1 Design Considerations

The subsections below describe the issues that are addressed during the design of the Android app.

### 1.1 Assumptions

The design of this Android app includes the following assumptions:

* The app is intended for use on the Andoid platform running the API level 19 (KitKat) or above
* The app is designed for use on an Android phone with a touch interface
* The app must be used with a card scanner to process Credit Card transaction
* The app is written for the English language
* The device used must have a screen density of at least 120 dpi 

### 1.2 Constraints

* The system should only keep track of what's necessary. This means we will not not be keeping track of credit card information, for example.
* The system should be easy to maintain. Discount types may be added, or sales policies might change in the future. Loose coupling is a must, and the interfaces between components must not break when the system is modified.

### 1.3 System Environment

*Describe the hardware and software that the system must operate in and interact with.*

## 2 Architectural Design

### 2.1 Component Diagram

![alt text][component-diagram]

### 2.2 Deployment Diagram

![alt text][deployment-diagram]

## 3 Low-Level Design

### 3.1 Class Diagram

![alt text][design-team]

### 3.2 Other Diagrams

## 4 User Interface Design

![alt text][GUI-diagram]
![alt text][GUI-diagram2]

[design-team]: ClassDiagram.png "Team-Design"
[deployment-diagram]: DeploymentDiagram.png "Deployment Diagram"
[component-diagram]: ComponentDiagram.png "Component Diagram"
[GUI-diagram]: GUIDesign.png "GUI Diagram"
[GUI-diagram2]: GUIDesign2.png "GUI Diagram2"
