# **Project Plan -- Team 80**

##1. Introduction

This software project includes the design and development of a command line tool to determine the average number of words per sentence in an ASCII text file provided as input. This tool can be used by anyone with little or no technological background on any computing platform. While the flag -d will allow the user to specify the various kinds of sentence delimiters, the flag -l will allow the user to specify the minimum threshold i.e. minimum characters which will constitute a word.

##2. Process Description

A waterfall-like software development process will be followed in the execution of this project and the activities involved are as follows:

`Requirements Development -> System Design -> System Implementation -> Verification and Testing -> Documentation`

###Requirements Development

In this activity, the team will formalize a set of system requirements based on all the information gathered by the team members.  This will be the first step in the development of the system and will be completed post discussions with the customer (Lauren’s interview). The information gathered will be organized into an exhaustive set of functional and non-functional requirements and presented in the form of a “Requirements” document. Along with the behavior of the application, the hardware and usage specifications will also be outlined in this document. This document will be finalized and approved by all team members before starting the next activities of the project and will be used a reference for all succeeding work.

###System Design

Once the requirements are fully defined, the system architecture will be developed.  This activity may include the creating of class diagrams, case diagrams, sequence diagrams and lists of methods necessary to satisfy the list of functional requirements. The system design activity will be headed by the project manager and all the team members will provide inputs during this process. This activity will be completed when all the requirements are well represented and the software architecture is completely defined.

###System Implementation

Once the system design is completed, actual implementation work will commence which will include the majority of the functional coding.  All of the classes, objects and methods will be created in the form defined by the system design. The code will be broken down and developed by all the team members and a central Github repository will be used to track all changes. The development activities will be carried out in branches and once the final application is reviewed and approved by the development lead, it will be merged to the master branch. Code development process will strictly adhere to the requirements documents and any changes along the way will be reviewed by the team members and adjusted accordingly. This process will be iteratively carried out until all the requirements are satisfactorily met for the project and the source code will be made available upon completion.  This activity will be deemed complete when the software system is in a functional state and ready for testing.

###Verification and Testing

Once the implementation of the system is completed, the testing phase will begin. This activity will involve the setting up of a QA procedure by the QA manager. As part of this procedure several unit tests (JUnit) will be developed in conjunction with the code. Important validation tests will be carried out by the QA manager to test the functionality of the code and a set of example cases will be created for the user. The QA team, constituting of all the team members, along with the QA manager will develop these validations and unit tests. The application will be released only after all these tests have successfully passed. These tests will be provided as part of the project and will be well documented.

###Documentation

A detailed user manual will be created upon completion of the verification and testing stage. The documentation team will draft the user manual and it will contain everything required to run the application by the user. This document will also contain examples and troubleshooting information for the user. A detailed summary of all the error messages and error codes will be compiled and suitable solutions will be outlined. Once approved by the documentation lead the user manual will be made available along with the application to the user.

##3. Team

The following table summarizes the roles of the various team members:

| Member  |Project Manager  | Development Lead  | Documentation Lead  | QA Manager  |Developer | Tester  | 
|--------:|----------------:|------------------:|--------------------:|------------:|---------:|--------:|
|Joel Henry |X  | | | |X  |X |
|Haresh Bhagchandani  | | |X  | |X |X  |
|Jaspreet Bajwa | | | |X |X  |X  |
|Gandharv Kashinath | |X  |  | |X |X  |

Note that all the team members will be responsible for the development work and testing. They will also contribute to the project with their ideas and assigned tasks by completing them in a timely manner.

###Joel Henry (Project Manager)

Will serve as the project manager and will track the progress of the various groups while making sure the project goals are met in a timely fashion. The project manager will also be responsible for the process planning and organizing teams to carry out required tasks.

###Haresh Bhagchandani (Documentation Lead)

Will serve as the documentation lead and will oversee the writing of the user manual. The user manual will be written predominantly by the documentation lead while getting inputs from various team members accordingly.

###Jaspreet Bajwa (QA Manager)

Will serve as the QA manager and will be in charge of developing relevant unit tests and validation cases for the application. These tests will be used during the development process to ensure the quality of the application and the QA manager will enforce the developers to perform these tests while doing the development work. Finally, the QA manager will verify if all the tests have passed while ensuring the requirements for the application have been met. 

###Gandharv Kashinath (Development Lead)

Will serve as the development lead and will be in charge of all the development activities carried out during the project. The development lead will manage the version control system by creating branches for the individual developers to perform their tasks and merging them accordingly. Coding standards will be established and the code will be regularly reviewed to check if those standards are being met by the development lead.

##4. Estimates

This project will require a combined 25 man hours and 250 lines of code including JUnit tests.
