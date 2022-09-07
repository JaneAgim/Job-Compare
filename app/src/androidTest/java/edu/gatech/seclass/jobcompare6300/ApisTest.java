package edu.gatech.seclass.jobcompare6300;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.apis.Apis;
import edu.gatech.seclass.jobcompare6300.models.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.models.Job;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApisTest {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    @Test
    public void useAppContext() {
        // Context of the app under test.
        assertEquals("edu.gatech.seclass.jobcompare6300", appContext.getPackageName());
    }

    @Test
    public void comparisonSettingsApiTest() {
        Apis apis = new Apis(appContext);
        ComparisonSettings comparisonSettings = new ComparisonSettings();
        comparisonSettings.setYearlySalaryWeight(1);
        comparisonSettings.setAllowedWeeklyTeleworkDays(2);
        comparisonSettings.setYearlyBonusWeight(3);
        comparisonSettings.setLeaveTimeWeight(4);
        comparisonSettings.setGymMembershipAllowanceWeight(5);
        apis.updateComparisonSettings(comparisonSettings);
        ComparisonSettings comparisonSettingsInStore = apis.getComparisonSettings();
        Assert.assertEquals(comparisonSettings.getYearlySalaryWeight(), comparisonSettingsInStore.getYearlySalaryWeight());
        Assert.assertEquals(comparisonSettings.getAllowedWeeklyTeleworkDaysWeight(), comparisonSettingsInStore.getAllowedWeeklyTeleworkDaysWeight());
        Assert.assertEquals(comparisonSettings.getYearlyBonusWeight(), comparisonSettingsInStore.getYearlyBonusWeight());
        Assert.assertEquals(comparisonSettings.getLeaveTimeWeight(), comparisonSettingsInStore.getLeaveTimeWeight());
        Assert.assertEquals(comparisonSettings.getGymMembershipAllowanceWeight(), comparisonSettingsInStore.getGymMembershipAllowanceWeight());
    }


    @Test
    public void jobApiTest() {
        Apis apis = new Apis(appContext);
        Job job = new Job();
        job.setCompany("Cisco");
        job.setLocation("CA");
        job.setAllowedWeeklyTeleworkDays(5);
        job.setCurrentJob(false);
        job.setTitle("Senior Software Engineer");
        job.setYearlySalary(20000);
        job.setYearlyBonus(30000);
        job.setCostOfLiving(2000);
        job.setLeaveTime(5);
        job.setGymMembershipAllowance(6);
        apis.add(job);
        List<Job> allJobs = apis.getAllJobs();
        Job loadedJob = new Job();
        for(Job j : allJobs) {
            if(j.getCompany().equals(job.getCompany())) {
                loadedJob = j;
            }
        }
        Assert.assertEquals(loadedJob.getAllowedWeeklyTeleworkDays(), job.getAllowedWeeklyTeleworkDays());
        Assert.assertEquals(loadedJob.getLocation(), job.getLocation());
        Assert.assertEquals(loadedJob.getTitle(), job.getTitle());
        Assert.assertEquals(loadedJob.getLeaveTime(), job.getLeaveTime());
        Assert.assertEquals(loadedJob.isCurrentJob(), job.isCurrentJob());
    }


}