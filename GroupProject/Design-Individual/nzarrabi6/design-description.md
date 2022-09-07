# Design Description Document

## Requirement 1
>When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet)

### Explanation 1 
A simple GUI would be created with a main menu containing 4 buttons, with respect to the four requirements listed above. These buttons will have onClicks attached to various class attributes and operations that are already present within my diagram. 
For the next set of requirements, I will specify exactly where to find these relevant classes.  


## Requirement 2 
>When choosing to enter current job details, a user will:
* Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
  * Title 
  * Company
  * Location (entered as city and state)
  * Cost of living in the location (expressed as an index)
  * Yearly salary
  * Yearly bonus
  * Allowed weekly telework days (expressed inclusively between 0 and 5)
  * Leave time (As a single overall number of days)
  * Gym membership allowance ($0 to $500 annually)
* Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

### Explanation 2 
Current job details are covered by the "CurrentJob" class that I created, which can be found in the UML diagram. Since the job offer details shared the same requirements as the current job details, I created a "JobOffer" class as well. Both of these are generalized in the "Job" class. As such, the attribute section of CurrentJob was left blank, as these requirements were already covered in the general Job class. 
The "JobOffer" and "CurrentJob" classes only differ in their operations. For this class, the setCurrentJob(User) operation allows us to rewrite the User's current job as need be, since we do not need more than one copy of a "current" job. 

## Requirement 3

> When choosing to enter job offers, a user will:
* Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
* Be able to either save the job offer details or cancel.
* Be able to:
  * (1) enter another offer
  * (2) return to the main menu
  * (3) compare the offer (if they saved it) with the current job details (if present).

### Explanation 3 
As stated above, the "JobOffer" class, only differs from the "CurrentJob" class by its operations. 
The setCurrentJob(User) function is replaced with the addToJobOffers(User) function, which saves lists of different copies to the User class. 
Both of these classes have a Save() and Cancel() operation which is found in the Job class, as indicated in Requirements #2 and #3. This would be further implemented in the GUI, returning to the homescreen when the Cancel operation is used.

## Requirement 4

> When adjusting the comparison settings, the user can assign integer weights to:
   * Yearly salary 
   * Yearly bonus
   * Allowed weekly telework days
   * Leave time
   * Gym membership allowance 
> If no weights are assigned, all factors are considered equal.


### Explanation 4
The integer weights are stored in the User Class, with the operation setPreferenceScale. This operation allows us to set integer weights for all of these job preferences. 
There is a utility function I named rank, which is used in a later class, that handles the case where there are no weights assigned, in its calculation of the job comparison metric. 

## Requirement 5
> When choosing to compare job offers, a user will:
* Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
* Select two jobs to compare and trigger the comparison.
* Be shown a table comparing the two jobs, displaying, for each job:
  * Title 
  * Company
  * Location (entered as city and state)
  * Yearly salary adjusted for cost of living
  * Yearly bonus adjusted for cost of living
  * Allowed weekly telework days 
  * Leave time 
  * Gym membership allowance 
* Be offered to perform another comparison or go back to the main menu


### Explanation 5
In the "CompareJobs" class, the list of stored job offers (jobList) is filtered by the rank function. I didn't know whether to include this "rank" function as a utility class or how to describe it in more detail within the CompareJobs class but its function is solely to compute the formula shown in #6. 
This is later used in the filter operation, which returns an OrderedList, based on the metric.
Finally, the tabulate operation takes two jobs as an input and accesses the class attributes of each job. This would be called and displayed by the GUI. 


## Requirement 6 
> When ranking jobs, a job’s score is computed as the weighted sum of:

> AYS + AYB + GYM + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)


### Explanation 6
Included in the "rank" function, as stated above 

## Requirement 7
> The user interface must be intuitive and responsive

### Explanation 7 
This would be represented entirely within the GUI implementation. 

## Requirement 8
> For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

### Explanation 8 
I assumed there was a single system running the app. 


