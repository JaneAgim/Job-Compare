# Assignment 5: Software Design Documentation
When the app is started, the user is presented with the main menu, which allows the user to 
(1) enter or edit current job details
: **Accomplished by UI, call JobController. updateCurrentJob(Job): void API in UML.**

(2) enter job offers
: **Accomplished by UI, call JobController.addJobOffer(Job): void API in UML.**

(3) adjust the comparison settings
: **Accomplished by UI, call ComparisonSettingsController.setComparisionSetting(ComparisonSetting):void API in UML**
 or 
(4) compare job offers (disabled if no job offers were entered yet)
: **job ids were captured when the user selects the two jobs, when Compare button is fired, JobController.getJobOffer(jobId): JobOffer API will be called for each id, listing comparisons of the two jobs.**

When choosing to enter current job details, a user will:
Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
* Title
* Company
* Location (entered as city and state)
* Cost of living in the location (expressed as an index)
* Yearly salary
* Yearly bonus
* Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
* Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
* Gym membership allowance ($0 to $500 annually)
		**: Accomplished by UI.**

Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
: **Cancel and exit without saving will be accomplished by UI, save will be accomplished by calling JobController. + updateCurrentJob(Job): void void API in UML.**

When choosing to enter job offers, a user will:
	Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job: **Accomplished by UI.**
	Be able to either save the job offer details or cancel
	**: Cancel and exit without saving will be accomplished by UI, save will be accomplished by calling JobController. + updateJobOffer(Job, jobId): void API in UML.**

Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
: **Enter another offer will be accomplished by UI, return to the main menu will be by UI, compare the offer (if they saved it) with the current job details (if present) will be implemented by calling JobController.getJobOffer(jobId): JobOffer API will be called for each id(of the two), listing comparisons of the two jobs.**

When adjusting the comparison settings, the user can assign integer weights to:
* Yearly salary
* Yearly bonus
* Allowed weekly telework days
* Leave time
* Gym membership allowance
* If no weights are assigned, all factors are considered equal.
: **Accomplished by UI, call ComparisonSettingsController.setComparisionSetting(ComparisonSetting):void API in UML**

When choosing to compare job offers, a user will:
Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
 : Accomplished by calling JobController.+ getAllJobOffers(): List<Job> Ranking will be executed upon adding new jobs to the list by calling JobController.- rankJobOffers(void): void. 
Select two jobs to compare and trigger the comparison: Accomplished by UI.
Be shown a table comparing the two jobs, displaying, for each job:
* Title
* Company
* Location 
* Yearly salary adjusted for cost of living
* Yearly bonus adjusted for cost of living
* Allowed weekly telework days
* Leave time
* Gym Membership Allowance
: **job ids were captured when the user selects the two jobs, when Compare button is fired, JobController.getJobOffer(jobId): JobOffer API will be called for each id, listing comparisons of the two jobs.**

Be offered to perform another comparison or go back to the main menu.
: **Accomplished by UI.**
When ranking jobs, a job’s score is computed as the weighted sum of:
AYS + AYB + GYM + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)
where:
* AYS = yearly salary adjusted for cost of living
* AYB = yearly bonus adjusted for cost of living
* GYM = Gym Membership Allowance ($0 to $500 annually)
* LT = leave time
* RWT = telework days per week
* The rationale for the RWT subformula is:
* value of an employee hour = (AYS / 260) / 8
* commute hours per year (assuming a 1-hour/day commute) = 1 * (260 - 52 * RWT), therefore travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8

For example, if the weights are 2 for the yearly salary, 2 for Gym Membership, and 1 for all other factors, the score would be computed as:

2/7 * AYS + 1/7 * AYB + 2/7 * GYM + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) * (AYS / 260) / 8)
: **This will be implemented in JobController.- rankJobOffers(void): void method, which is called upon when new jobs are added. So that getJobOffers will directly show sorted job offers.**

The user interface must be intuitive and responsive.
	: **This will be implemented by UI.**
For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).