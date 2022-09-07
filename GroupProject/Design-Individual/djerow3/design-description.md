# Design Description 

The following document outlines specific design considerations for each of the listed requirements. 

## Requirement 1 
> When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  

This requirement is met through Main Menu Class. This class is serving as the orchestration layer with the UI and provides the access point to the different menu options for the user. Specifically the class provides four button attributes that the UI will be able to map to the corresponding menu types. 

Other than the Comparison Settings Class, every other class will have access to the Job Class as having the current job will be critical for the user interface and other workflows. 

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
>
> 2. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

This requirement is met through the Job class. Each  Although not explicitly specified in the UML diagram, the class will have get and set methods for each attribute. This is represented in the Getter() and Setter() methods, respectively. 

The one aspect not represented in the Job class is the ability to exit without saving as this will be addressed through the user interface. In these cases, the user will exit the edit/enter state and the corresponding data will not be passed to the database. 

## Requirement 3 
> When choosing to enter job offers, a user will:
> 1. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
> 2. Be able to either save the job offer details or cancel.
> 3. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

This requirements is addressed through the Main Menu class and Job Class. Through the user interface the user will be able to add a new job offer. This workflow will then leverage the Job Class and the user will be able to enter detail for each attribute of the class. Like in the current job workflow, this can be accomplished through the set methods in the class.

Additionally, the ability to exit without saving as this will be addressed through the user interface similar to the current job workflow. 

## Requirement 4 
> When adjusting the comparison settings, the user can assign integer weights to:
> - Yearly salary
> - Yearly bonus
> - Allowed weekly telework days
> - Leave time
> - Gym membership allowance
> If no weights are assigned, all factors are considered equal.

The user will be able to access the comparison settings through a menu button from the main menu which leverages the Main Menu class. This will navigate the user to a new interface which will allow them to edit settings through Change Comparison Settings and Comparison Settings classes. 

The Change Comparison Settings Class is intended to serve as a orchestration between the UI workflow to allow a user to enter the edit state of the settings. Once this is done, The Change Comparison Settings Class will leverage the set methods of the Comparison Settings Class to update the respective weights. 

## Requirement 5 
> When choosing to compare job offers, a user will:
> 1. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
> 2. Select two jobs to compare and trigger the comparison.
> 3. Be shown a table comparing the two jobs, displaying, for each job:
>    - Title
>    - Company
>    - Location 
>    - Yearly salary adjusted for cost of living
>    - Yearly bonus adjusted for cost of living
>    - Allowed weekly telework days
>    - Leave time
>    - Gym Membership Allowance
> 4. Be offered to perform another comparison or go back to the main menu.

The user will be able to execute the comparison through the Main Menu Class comparisonButton. This button ID will serve as an input for the user interface that can be mapped to navigate the user to the Compare Jobs page. Once the user navigates to the Compare Jobs page, the user interface will call the GetRankingList method from the RankingList class. This will provide the most recent ranking list for the user to compare. 

When the GetRankingList method is called, part of the method logic will be to check for any jobs that have not been added to the list. If any jobs are not included in the list, the method will call the AddJobRanking method which subsequently calls the CalculateJobScore method in hte JobScore class for the respective job. The final step in  this process is that the ReorderList method will be called to ensure that the jobScores provided to the CompareJobs class are in the proper order. 

## Requirement 6
> When ranking jobs, a job’s score is computed as the weighted sum of:
> AYS + AYB + GYM + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)
> where:  
> - AYS = yearly salary adjusted for cost of living  
> - AYB = yearly bonus adjusted for cost of living  
> - GYM = Gym Membership Allowance ($0 to $500 annually)  
> - LT = leave time  
> - RWT = telework days per week  

This requirement is met through the implementation of the Job Score Class. This class takes the job attribute data and data from the Comparison Settings to determine the appropriate Job Score based on the attribute weights for each job. One thing to note, the Job Score Class is not run by the user and the CalculateJobScore method will be called when the Comparison UI page is open through the Ranking List Class ReorderList method. 

## Requirement 7
> The user interface must be intuitive and responsive.

This was not included within the class diagram as these will be managed by the user interface classes.

## Requirement 8 
> For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

The diagram assumes that data of the classes are accessible within the same system and the application does not rely on additional network services to be able to perform CRUD operations. 
