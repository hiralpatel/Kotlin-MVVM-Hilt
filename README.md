# Kotlin-MVVM-Hilt
A sample Album list app that shows how to use ViewModels and Room together with RxJava &amp; Dagger2, in Kotlin by Clean Architecture.

### Implemented by Clean Architecture
The following diagram shows the structure of this project with 3 layers:
- Presentation
- Domain
- Data

### Scenario
Using of https://jsonplaceholder.typicode.com/ as a public api to generate fake data for testing.

At a glance:

- Create list of Albums with Vertical scrolling.
- Each Album has some Photos which has to show Horizontally.
- Both Horizontal and Vertical scroll will be infinite scrolling with data.
- All the data will be stored locally, so if after 2-3 times opened Application it will work offline as well.

### Included below things

