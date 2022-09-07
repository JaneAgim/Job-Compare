# Design Document


**Author**: Team 014

## 1 Design Considerations



### 1.1 Assumptions

In the creation of this app, we assume:

- The app is implemented on the Android platform
- The app only serves a single user (George P. Burdell, our fellow classmate)
- The app supports an API level of 29 or higher
- Network connection is not required for application functionality
- The app does not need multiple device log in
- Processing time/Multi threading do not need to be included into the design consideration


### 1.2 Constraints

- Application state shall persist between runs. The application must provide a persistency layer in order to persist data locally.
- App must be able to handle at least 100 job offers

### 1.3 System Environment

- Platform: Android SDK
- OS/API Level: Android 10.0(Q) / API 29


## 2 Architectural Design


### 2.1 Component Diagram
![component diagram](./images/component-diagram.png)

All UI actions will be done by calling apis inside the API center, which further interacts with Job Controller and ComparasonSetting Controller. Job Controller and ComparasonSetting Controller are responsible for CRUD actions with
Android's internal SQLite Database.

### 2.2 Deployment Diagram
![deployment diagram](./images/deployment-diagram.png)


In this project, all components will be stored inside Android SQLite database, no external database would be used.

## 3 Low-Level Design

All UI actions will be done by calling apis inside the API center, which interacts with Job Controller and ComparasonSetting Controller. Job Controller contains all job offfer related actions while ComparasonSetting Controller contains any actions realted to comparison settings. Job Controller and ComparasonSetting Controller will further interact with with Android's internal SQLite Database for any CRUD actions as needed.

### 3.1 Class Diagram



![team design](./images/team-design.png)



## 4 User Interface Design


### Main Menu

<img src="./images/main_page.png" alt="drawing" width="300"/>


### Current Job

<img src="./images/currentjob-ui.png" alt="drawing" width="300"/>


### Job Offers

<img src="./images/job-offers-ui-4-buttons.png" alt="drawing" width="300"/>


### Compare Offers

<img src="./images/compare_button_tablee1.png" alt="drawing" width="300"/>


### Adjust Weights

<img src="./images/adjustweights-ui.png" alt="drawing" width="300"/>


### Comparison Chart

<img src="./images/compare-ui.png" alt="drawing" width="300"/>
