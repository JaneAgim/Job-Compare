package edu.gatech.seclass.jobcompare6300.apis;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.dao.dbhelpers.DBHelper;
import edu.gatech.seclass.jobcompare6300.models.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.models.Job;

public class Apis {
    private DBHelper dbHelper;

    public Apis(Context context){
        dbHelper = new DBHelper(context, "jobs.db");
    }

   //Add a new job into database
    public void add(Job job) {
        dbHelper.insert(job);
    }

    //Update a job
    public void update(Job job) {
        dbHelper.update(job);
    }

    //Get a job by job id
    public Job get(int id) {
        return dbHelper.get(id);
    }

    // Get currentJob
    public Job getCurrentJob() {
        List<Job> jobs = getAllJobs();
        if(jobs == null || jobs.size() == 0) {
            return null;
        }

        for(Job job : jobs) {
            if(job.isCurrentJob()) {
                return job;
            }
        }

        return null;
    }

    // Get all jobs(sorted by rank)
    public List<Job> getAllJobs() {
        List<Job> jobs = dbHelper.get();
        List<Job> updatedJobs = new ArrayList<>();
        ComparisonSettings comparisonSettings = this.getComparisonSettings();

        for(Job job : jobs) {
            job.setScore(calculateScore(job, comparisonSettings));
            updatedJobs.add(job);
        }

        Collections.sort(updatedJobs, new Comparator<Job>() {
            @Override
            public int compare(Job jobA, Job jobB) {
                return Double.compare(jobB.getScore(),jobA.getScore());
                //return Double.compare(jobA.getScore(),jobB.getScore());
            }
        });
        return updatedJobs;
    }

    // Remove all jobs
    public void removeAllJobs() {
        dbHelper.clearJobsTable();
    }

    // Remove a job by id
    public void deleteJob(int id) {
        dbHelper.delete(id);
    }

    // private helper method
    private double calculateScore(Job job, ComparisonSettings comparisonSettings) {
        double AYS = job.getYearlySalary();
        double AYB = job.getYearlyBonus();
        double GYM = job.getGymMembershipAllowance();
        int LT = job.getLeaveTime();
        int RWT = job.getAllowedWeeklyTeleworkDays();

        int AYSWeight = comparisonSettings.getYearlySalaryWeight();
        int AYBWeight = comparisonSettings.getYearlyBonusWeight();
        int GYMWeight = comparisonSettings.getGymMembershipAllowanceWeight();
        int LTWeight = comparisonSettings.getLeaveTimeWeight();
        int RWTWeight = comparisonSettings.getAllowedWeeklyTeleworkDaysWeight();
        int weightSum = AYSWeight + AYBWeight + GYMWeight + LTWeight + RWTWeight;
        double AYS_Weight = (double)AYSWeight / weightSum;
        double AYB_Weight = (double)AYBWeight / weightSum;
        double GYM_Weight = (double)GYMWeight / weightSum;
        double LT_Weight = (double)LTWeight / weightSum;
        double RWT_Weight = (double)RWTWeight / weightSum;

        double score = AYS_Weight * AYS + AYB_Weight * AYB + GYM_Weight * GYM + LT_Weight * (LT * AYS / 260)
                - RWT_Weight * ((260 - 52 * RWT) * (AYS / 260) / 8);

        //2/7 * AYS + 1/7 * AYB + 2/7 * GYM + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) * (AYS / 260) / 8)
        return score;
    }

    public List<Job> getAllJobsUnranked() {
        List<Job> jobs = dbHelper.get();
        List<Job> updatedJobs = new ArrayList<>();
        ComparisonSettings comparisonSettings = this.getComparisonSettings();

        for(Job job : jobs) {
            job.setScore(calculateScore(job, comparisonSettings));
            updatedJobs.add(job);
        }

        return updatedJobs;
    }






    // ComparisonSettings
    public ComparisonSettings getComparisonSettings() {
        ComparisonSettings comparisonSettings = dbHelper.getComparisonSettings();
        if(comparisonSettings == null) {
            comparisonSettings = new ComparisonSettings();
            dbHelper.insertComparisonSettings(comparisonSettings);
            return comparisonSettings;
        }else {
            return comparisonSettings;
        }
    }

    public void updateComparisonSettings(ComparisonSettings comparisonSettings) {
        ComparisonSettings oldComparisonSettings = dbHelper.getComparisonSettings();
        if(oldComparisonSettings == null) {
            dbHelper.insertComparisonSettings(comparisonSettings);
        }else {
            dbHelper.update(comparisonSettings);
        }
    }

    public void removeComparisonSettings() {
        dbHelper.clearComparisonSettingsTable();
    }

    private void insertComparisonSettings(ComparisonSettings comparisonSettings) {
        dbHelper.insertComparisonSettings(comparisonSettings);
    }
}
