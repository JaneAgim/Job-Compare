
# **Job Hunt App Design Description Document**

1)  When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare (disabled if no job offers were entered yet).

	*There is a main menu class (entry point), Main, that contains methods that link to the 4 operations mentioned here [jobDetails(), jobOffers(), compare(), adjustSettings()]. It also contains a User object to keep track of all the data being transferred between the different classes. This object is consequently present in all the other classes and is passed between them.*

 2)  When choosing to _enter current job details,_ a user will:

	 a)  Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
			i.  Title
			ii.  Company
			iii.  Location (entered as city and state)
			iv.  Cost of living in the location (expressed as an index)
			v.  Yearly salary
			vi.  Yearly bonus
			vii.  Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
			viii.  Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
			ix.  Gym membership allowance ($0 to $500 annually)
		b)  Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

		*The main menu links to a class called CurrentJob that contains a user interface that allows the user to enter [enter()] or edit [edit()] all the fields above. There are also methods in this class for saving [save()] and cancelling the menu [cancelAndExit()].*

3)  When choosing to _enter job offers,_ a user will:
	a)  Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
	b)  Be able to either save the job offer details or cancel.
	c)  Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

	*The main menu also links to a class called JobOffers which houses a user interface and has a method to enter the details of the offer [enter[]), save the job offer details [save()], cancel the interface [cancel()], enter another offer [enterAnother()], return to the main menu [returnToMain()].*

4)  When _adjusting the comparison settings,_ the user can assign integer _weights_ to:
	a)  Yearly salary
	b)  Yearly bonus
	c)  Allowed weekly telework days
	d)  Leave time
	e)  Gym membership allowance
	If no weights are assigned, all factors are considered equal.

	*The main menu links to the AdjustSettings class that contains a user interface and has a method [adjustWeights()] to adjust the individual weights in the array that houses them (weights[]).*

5)  When choosing to _compare job offers,_ a user will:
	a)  Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
	b)  Select two jobs to compare and trigger the comparison.
	c)  Be shown a table comparing the two jobs, displaying, for each job:
	i)  Title
	ii)  Company
	iii)  Location
	iv)  Yearly salary adjusted for cost of living
	v)  Yearly bonus adjusted for cost of living
	vi)  Allowed weekly telework days
	vii)  Leave time
	viii)  Gym Membership Allowance
	d)  Be offered to perform another comparison or go back to the main menu.

	*Finally, the main menu links to the Compare class that has a user interface and a method to rank the offers [rankOffers()], display the offers [displayOffers()], select two jobs and compare them [selectAndCompare()], perform another comparison [compareAnother()], return to main menu [returnToMain()].*

16)  When ranking jobs, a job’s score is computed as the **weighted** sum of:  
      
    AYS + AYB + GYM + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)  
      
    where:  
    AYS = yearly salary adjusted for cost of living  
    AYB = yearly bonus adjusted for cost of living  
    GYM = Gym Membership Allowance ($0 to $500 annually)  
    LT = leave time  
    RWT = telework days per week  
    
    The rationale for the RWT subformula is:
    i)  value of an employee hour = (AYS / 260) / 8
    ii)  commute hours per year (assuming a 1-hour/day commute) =  
    1 * (260 - 52 * RWT)
    iii)  therefore **travel-time cost =** **(260 - 52 * RWT) * (AYS / 260) / 8**
    For example, if the weights are 2 for the yearly salary, 2 for Gym Membership, and 1 for all other factors, the score would be computed as:  
    2/7 * AYS + 1/7 * AYB + 2/7 * GYM + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) * (AYS / 260) / 8)

*This portion of the design will be implemented within the rankOffers() method inside the Compare class, so there was no need to visually represent it.*  

7)  The user interface must be intuitive and responsive.

	*This is GUI-specific so there was no need to visually represent it in the diagram.*

8)  For simplicity, you may assume there is a _single system_ running the app (no communication or saving between devices is necessary).

	*This is represented by the lack of communication leading out of the system represented in the diagram.*

