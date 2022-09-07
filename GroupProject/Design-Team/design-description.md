# Design Description

The following document outlines specific design considerations for each of the listed requirements.

## Requirement 1
> When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  

**A simple GUI would be created for the main menu, containing 4 buttons, one for each of the four requirements listed above.**


Each button is tied to a class operator designated in the UML diagram.

1. Button (1) calls the JobController.updateCurrentJob(Job): void API, allowing the user to enter or edit current job details.
2. Button (2) calls the JobController.addJobOffer(Job): void API, allowing the user to enter job offers.
3. Button (3) calls the ComparisonSettingsController.setComparisionSetting(ComparisonSetting), allowing the user to adjust the comparison settings
4. Button (4) calls the JobController.getJobOffer(jobId): JobOffer API for each jobId, listing comparisons of the two jobs. This feature is enabled only when the user either inputs a current job and a job offer or two job offers. In either case, two Job objects are created. All Job objects have an "id" attribute, as can be seen in the Job(Model) in the UML diagram.

## Requirement 2
> When choosing to enter current job details, a user will:
>   1. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job:
> - Title
> - Company
> - Location (entered as city and state)
> - Cost of living in the location (expressed as an index)
> - Yearly salary
> - Yearly bonus
> - Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
> - Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
> - Gym membership allowance ($0 to $500 annually)

**Since the user must be shown a user interface to enter or edit the details of their current job, this will be handled by the UI.**
>
> 2. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

Saving job details is accomplished by calling JobController.updateCurrentJob(Job): void API.

"Cancel and exit without saving" will be **handled by the UI.**

## Requirement 3
> When choosing to enter job offers, a user will:
> 1. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.

**Accomplished by UI.**
> 2. Be able to either save the job offer details or cancel.

Saving job details is accomplished by calling JobController.updateCurrentJob(Job): void API.

"Cancel and exit without saving" will be **handled by the UI.**

> 3. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

(1)"Enter another offer" and (2)"return to the main menu" **will be handled by UI.**

(3) will be implemented by calling the JobController.getJobOffer(jobId): JobOffer API for each id, listing comparisons of the two jobs.**



## Requirement 4
> When adjusting the comparison settings, the user can assign integer weights to:
> - Yearly salary
> - Yearly bonus
> - Allowed weekly telework days
> - Leave time
> - Gym membership allowance
> If no weights are assigned, all factors are considered equal.

The ComparisonSetting class contains the above attributes. The user can edit the default integer value. This **will be handled by the UI**, with the help of the  ComparisonSettingsController.setComparisionSetting(ComparisonSetting):void API.
The CRUD(create, read, update and delete) operations from this class allow the user to rewrite the ComparisonSetting object that is stored in the DataCenter(DAO).

## Requirement 5
> When choosing to compare job offers, a user will:
> 1. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.

**Handled by the UI**

> 2. Select two jobs to compare and trigger the comparison.

**Handled by the UI**
> 3. Be shown a table comparing the two jobs, displaying, for each job:
>    - Title
>    - Company
>    - Location
>    - Yearly salary adjusted for cost of living
>    - Yearly bonus adjusted for cost of living
>    - Allowed weekly telework days
>    - Leave time
>    - Gym Membership Allowance

The JobController.getJobOffer(jobId): JobOffer API is called on each jobId, from the two jobs seleted above. This triggers the list of comparisons to be displayed for these two jobs. This feature is enabled only when the user either inputs a current job and a job offer or two job offers. In either case, two Job objects are created. All Job objects have an "id" attribute, as can be seen in the Job(Model) in the UML diagram.

> 4. Be offered to perform another comparison or go back to the main menu.

**Accomplished by UI.**


## Requirement 6
> When ranking jobs, a job’s score is computed as the weighted sum of:
> AYS + AYB + GYM + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)
> where:  
> - AYS = yearly salary adjusted for cost of living  
> - AYB = yearly bonus adjusted for cost of living  
> - GYM = Gym Membership Allowance ($0 to $500 annually)  
> - LT = leave time  
> - RWT = telework days per week  

This will be implemented in the JobController.rankJobOffers(void): void method, which is called upon when new jobs are added, so that getJobOffers is not required to sort through job offers again.

## Requirement 7
> The user interface must be intuitive and responsive.

**This will be implemented by UI.**

## Requirement 8
> For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

The diagram assumes that data of the classes are accessible within the same system and the application does not rely on additional network services to be able to perform CRUD operations.
