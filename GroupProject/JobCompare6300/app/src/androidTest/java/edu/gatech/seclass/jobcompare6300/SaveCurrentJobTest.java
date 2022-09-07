package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import edu.gatech.seclass.jobcompare6300.apis.Apis;
import edu.gatech.seclass.jobcompare6300.models.Job;
import android.view.View;

@RunWith(AndroidJUnit4.class)
public class SaveCurrentJobTest {
    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private View view;
    private Apis apis;
    private List<Job> originalList;
    private List<Job> updatedList;

    @Rule
    public ActivityTestRule<MainActivity> mActivity = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setUp() throws Exception {
        view = new View(appContext);
        apis = new Apis(appContext);
        originalList = new ArrayList<Job>();
        updatedList = new ArrayList<Job>();

        originalList = apis.getAllJobs();
    }

    //1. Test user is able to save job offer to database
    @Test
    public void testSaveJob() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("4"));
        onView(withId(R.id.salaryID)).perform(replaceText("100500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();
        if (apis.getCurrentJob() == null) {
            Assert.assertEquals((originalList.size()),updatedList.size()+1);
        }
        else {
            Assert.assertEquals((originalList.size()),updatedList.size());
        }
    }

    //2. Test user is not able to save job w/out job title
    @Test
    public void testNotSaveTitle() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText(""));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("4"));
        onView(withId(R.id.salaryID)).perform(replaceText("100500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.titleID)).check(matches(hasErrorText("This field cannot be left empty.")));
    }

    //3. Test user is not able to save job w/out company
    @Test
    public void testNotSaveCompany() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText(""));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("4"));
        onView(withId(R.id.salaryID)).perform(replaceText("100500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.companyID)).check(matches(hasErrorText("This field cannot be left empty.")));
    }

    //4. Test user is not able to save job w/out location
    @Test
    public void testNotSaveLocation() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText(""));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("4"));
        onView(withId(R.id.salaryID)).perform(replaceText("100500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.locationID)).check(matches(hasErrorText("This field cannot be left empty.")));
    }

    //5. Test user is not able to save job w/out cOL index
    @Test
    public void testNotSaveCOL() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText(""));
        onView(withId(R.id.salaryID)).perform(replaceText("100500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.costOfLivingID)).check(matches(hasErrorText("Enter a positive index integer.")));
    }

    //6. Test user is not able to save job w/out cOL index w/ numeric value
    @Test
    public void testNotSaveCOLNotNum() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("test"));
        onView(withId(R.id.salaryID)).perform(replaceText("100500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.costOfLivingID)).check(matches(hasErrorText("Enter a positive index integer.")));
    }

    //7. Test user is not able to save job w/out salary
    @Test
    public void testNotSaveSalary() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText(""));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.salaryID)).check(matches(hasErrorText("Enter a non-negative number with no commas.")));
    }

    //8. Test user is not able to save job w/ non numeric salary
    @Test
    public void testNotSaveSalaryNotNum() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("test3"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.salaryID)).check(matches(hasErrorText("Enter a non-negative number with no commas.")));
    }

    //9. Test user is not able to save job w/out bonus
    @Test
    public void testNotSaveBonus() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("100500"));
        onView(withId(R.id.bonusID)).perform(replaceText(""));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.bonusID)).check(matches(hasErrorText("Enter a non-negative number with no commas.")));
    }

    //10. Test user is not able to save job w/ non numeric bonus
    @Test
    public void testNotSaveBonusNotNum() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("10500"));
        onView(withId(R.id.bonusID)).perform(replaceText("test3"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.bonusID)).check(matches(hasErrorText("Enter a non-negative number with no commas.")));
    }


    //11. Test user is not able to save job w/out telework days
    @Test
    public void testNotSaveTelework() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("100500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText(""));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.teleworkDaysID)).check(matches(hasErrorText("Enter an integer between 0 and 5. ")));
    }

    //12. Test user is not able to save job w/ non numeric telework days
    @Test
    public void testNotSaveTeleworkNotNum() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("10500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("test"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.teleworkDaysID)).check(matches(hasErrorText("Enter an integer between 0 and 5. ")));
    }

    //13. Test user is not able to save job w/ telework days > 5
    @Test
    public void testNotSaveTeleworkGreater() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("10500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("6"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.teleworkDaysID)).check(matches(hasErrorText("Enter an integer between 0 and 5. ")));
    }

    //14. Test user is not able to save job w/ telework days < 0
    @Test
    public void testNotSaveTeleworkLess() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("10500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("-1"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.teleworkDaysID)).check(matches(hasErrorText("Enter an integer between 0 and 5. ")));
    }

    //15. Test user is not able to save job w/out leave
    @Test
    public void testNotSaveLeave() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("100500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText(""));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.leaveID)).check(matches(hasErrorText("Enter a non-negative number with no commas.")));
    }

    //16. Test user is not able to save job w/ non numeric leave
    @Test
    public void testNotSaveLeaveNotNum() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("10500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("500"));
        onView(withId(R.id.leaveID)).perform(replaceText("test"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.leaveID)).check(matches(hasErrorText("Enter a non-negative number with no commas.")));
    }

    // 17. Test user is not able to save job w/out gym
    @Test
    public void testNotSaveGym() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("100500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText(""));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.gymID)).check(matches(hasErrorText("Enter a number between 0 and 500.")));
    }

    //18. Test user is not able to save job w/ non numeric gym
    @Test
    public void testNotSaveGymNotNum() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("10500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("test"));
        onView(withId(R.id.gymID)).perform(replaceText("test"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.gymID)).check(matches(hasErrorText("Enter a number between 0 and 500.")));
    }

    //19. Test user is not able to save job w/ telework days > 500
    @Test
    public void testNotSaveGymGreater() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("10500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("3"));
        onView(withId(R.id.gymID)).perform(replaceText("501"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.gymID)).check(matches(hasErrorText("Enter a number between 0 and 500.")));
    }

    //20. Test user is not able to save job w/ telework days < 0
    @Test
    public void testNotSaveGymLess() {
        onView(withId(R.id.currentJobActivity)).perform(click());
        onView(withId(R.id.titleID)).perform(replaceText("title2"));
        onView(withId(R.id.companyID)).perform(replaceText("company"));
        onView(withId(R.id.locationID)).perform(replaceText("location"));
        onView(withId(R.id.costOfLivingID)).perform(replaceText("3"));
        onView(withId(R.id.salaryID)).perform(replaceText("10500"));
        onView(withId(R.id.bonusID)).perform(replaceText("10500"));
        onView(withId(R.id.teleworkDaysID)).perform(replaceText("20"));
        onView(withId(R.id.gymID)).perform(replaceText("-1"));
        onView(withId(R.id.leaveID)).perform(replaceText("20"));
        onView(withId(R.id.saveID)).perform(click());

        updatedList = apis.getAllJobs();

        Assert.assertEquals(originalList.size(), updatedList.size());
        onView(withId(R.id.gymID)).check(matches(hasErrorText("Enter a number between 0 and 500.")));
    }

}

