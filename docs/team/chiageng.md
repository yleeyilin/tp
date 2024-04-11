---
layout: page
title: Chia Geng's Project Portfolio Page
---

### Project: PoochPlanner

PoochPlanner is a desktop application to track details of various groups (supplier, maintainer, staff) that dog cafe owners have to regularly interact with. The app is optimised for use using Command Line Interface (CLI) while still encompassing a user-friendly Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature : Add**: Added the ability to add 4 different type of contacts. (Pull requests [\#57](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/57))
    * What it does: allows the user to add a new contact.
    * Justification: This feature improves the product significantly because a user can store all contacts in this address book.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **Storing Json** : Handled the serialization of different type of contacts to store as JSON objects. (Pull requests [\#57](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/57))
    * What it does: store different type of contact into JSON object.
    * Justification: This feature improves the product significantly because a user can store all contacts into local storage.
    * Highlights: This enhancement affects existing project structure. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing project structure.

* **Reading Json** : Handled the serialization of reading different type of contacts from JSON file. (Pull requests [\#57](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/57))
    * What it does: read different type of contact from JSON file.
    * Justification: This feature improves the product significantly because a user can retrieve all contacts from local storage.
    * Highlights: This enhancement affects existing project structure. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing project structure.

* **New Feature : Undo** : Added ability to undo unlimited times for any wrong executed commands that modified address book. (Pull requests [\#114](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/114))
    * What it does: Undo a wrong executed commnad that modified addressbook.
    * Justification: This feature improves the product significantly because a user can retrieve history if wrong command is executed accidentally.
    * Highlights: This enhancement affects existing project structure. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing project structure.

* **New Feature : Redo** : Added ability to redo unlimited times for any wrong executed undo commands. (Pull requests [\#114](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/114))
    * What it does: Redo a wrong executed undo commnad.
    * Justification: This feature improves the product significantly because a user can retrieve history if wrong undo command is executed accidentally.
    * Highlights: This enhancement affects existing project structure. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing project structure.

* **Exception Handling Hierarchy** : Standardise error handling hierarchy for every command. (Pull requests [\#173](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/173))
    * What it does: Standardise the flow of error handling to ensure that bug is easier to be discovered in future.
    * Justification: This standardisation will allow developers to work in a more systematic manner to avoid messy code.
    * Highlights: This enhancement requires a deep understanding on current code in order to create a dynamic exception handling functions which is usable for every command. This was challenging to ensure the abstraction is done elegantly.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=chiageng&tabRepo=AY2324S2-CS2103T-W10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
    * Updated the GUI for display different type of contacts (Pull requests [\#57](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/57)
    * Updated and standardise Exception Handling for every command (Pull requests [\#202](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/202))
    * Updated more specific dynamic error messages and error handling for every command (Pull requests [\#215](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/215))

* **Project management**:
    * Managed releases [v1.2](https://github.com/AY2324S2-CS2103T-W10-2/tp/releases/tag/v1.2), [v1.3(trial)](https://github.com/AY2324S2-CS2103T-W10-2/tp/releases/tag/v1.3(trial)) (2 releases) on GitHub

* **Documentation**:
    * User Guide:
        * Added documentation for the features `add`
        * Added documentation for the features `undo` and `redo`
        * Responsible for Command Summary Table checking
    * Developer Guide:
        * Added non functional requirements.
        * Updated use cases for PoochPlanner.
        * Updated UML diagram for Storage.
        * Added undo and redo details explanation for reference.
        * Added undo and redo Architecture Diagram for reference.
        * Updated sequence diagram to fit with latest PoochPlanner project structure.
        * Responsible for Developer Guide checking.
