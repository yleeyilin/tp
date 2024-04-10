---
layout: page
title: Yi Lin's Project Portfolio Page
---

### Project: PoochPlanner

PoochPlanner is a desktop application to track details of various groups (supplier, maintainer, staff) that dog cafe owners have to regularly interact with. The app is optimised for use using Command Line Interface (CLI) while still encompassing a user-friendly Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature : Edit**: Added the ability to edit previous contacts. (Pull requests [\#62](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/62))
  * What it does: This feature allows users to edit all previous contacts. Multiple fields can be edited at the same time.
  * Justification: This feature improves the product significantly because the details of a contact can change and the app should provide a convenient way to rectify the contacts.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to handle all four types of contacts.

* **New Feature : Pin**: Added the ability to pin contacts. (Pull requests [\#128](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/128))
  * What it does: This feature allows users to pin frequent contacts. Multiple contacts can be pinned.
  * Justification: This feature improves the product significantly because a user may have many contacts and the app should provide a convenient way for the user to retrieve frequent contacts.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required handling the versioned address book and proper storage in the json file.

* **New Feature : Pin**: Added the ability to unpin contacts. (Pull requests [\#128](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/128))
  * What it does: This feature allows users to unpin pinned contacts.
  * Justification: This feature improves the product significantly because a user may want to pin a different contact as their priorities change and the app should provide a convenient way for the user to unpin contacts that are no longer as important..
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required handling the versioned address book and proper storage in the json file.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=CS2103T-W10-2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=yleeyilin&tabRepo=AY2324S2-CS2103T-W10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `edit` [\#124](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/124)
    * Added documentation for the feature `pin` [\#124](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/124)
    * Added documentation for the feature `unpin` [\#124](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/124)
    * Formatted documentation and added tips and constraints for all features [\#137](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/137)
    * Added screenshots and explainations for all features [\#199](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/199)
  * Developer Guide:
    * Added use cases for PoochPlanner [\#30](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/30)
    * Added Sequence Diagram for the `edit` command [\#204](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/204)
    * Added Sequence Diagram for the `pin` command [\#204](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/204)
    * Added Sequence Diagram for the `unpin` command [\#204](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/204)
    * Edited the UML Diagram for `UI` [\#77](https://github.com/AY2324S2-CS2103T-W10-2/tp/pull/77)
