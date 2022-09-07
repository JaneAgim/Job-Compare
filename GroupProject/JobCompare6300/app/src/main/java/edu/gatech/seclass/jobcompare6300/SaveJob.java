package edu.gatech.seclass.jobcompare6300;

//import androidx.appcompat.app.AppCompatActivity;

//import android.content.Intent;
//mport android.os.Bundle;
import android.view.View;
import android.widget.EditText;

//import edu.gatech.seclass.jobcompare6300.MainActivity;
import edu.gatech.seclass.jobcompare6300.apis.Apis;
import edu.gatech.seclass.jobcompare6300.models.Job;

public class SaveJob {
    boolean errorCheck = false;
    boolean jobSaved = false;
    //Job sJob;

    public boolean isNumeric(String string) {
        try {
            Double.parseDouble(string);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    public int save(View view, Job job, Apis apis, EditText title, EditText company, EditText location, EditText cOL, EditText salary, EditText bonus, EditText teleworkDays, EditText leave, EditText gymAllowance) {

        if (!title.getText().toString().isEmpty()) {
            job.setTitle(title.getText().toString());
        }
        else {
            title.setError("This field cannot be left empty.");
            errorCheck = true;
        }
        if (!company.getText().toString().isEmpty()) {
            job.setCompany(company.getText().toString());
        }
        else {
            company.setError("This field cannot be left empty.");
            errorCheck = true;
        }
        if (!location.getText().toString().isEmpty()) {
            job.setLocation(location.getText().toString());
        }
        else {
            location.setError("This field cannot be left empty.");
            errorCheck = true;
        }
        if (isNumeric(cOL.getText().toString()) && (Double.parseDouble(cOL.getText().toString()) > 0)) {
            job.setCostOfLiving(Double.parseDouble(cOL.getText().toString()));
        }
        else {
            cOL.setError("Enter a positive index integer.");
            errorCheck = true;
        }
        if (isNumeric(salary.getText().toString()) && (Double.parseDouble(salary.getText().toString()) > -1)) {
            job.setYearlySalary(Double.parseDouble(salary.getText().toString()));
        }
        else {
            salary.setError("Enter a non-negative number with no commas.");
            errorCheck = true;
        }
        if (isNumeric(bonus.getText().toString()) && (Double.parseDouble(bonus.getText().toString()) > -1)) {
            job.setYearlyBonus(Double.parseDouble(bonus.getText().toString()));
        }
        else {
            bonus.setError("Enter a non-negative number with no commas.");
            errorCheck = true;
        }
        if (isNumeric(teleworkDays.getText().toString()) && (Integer.parseInt(teleworkDays.getText().toString()) >= 0) && (Integer.parseInt(teleworkDays.getText().toString()) <= 5)) {
            job.setAllowedWeeklyTeleworkDays(Integer.parseInt(teleworkDays.getText().toString()));
        }
        else {
            teleworkDays.setError("Enter an integer between 0 and 5. ");
            errorCheck = true;
        }
        if (isNumeric(leave.getText().toString()) && (Integer.parseInt(leave.getText().toString()) > -1)) {
            job.setLeaveTime(Integer.parseInt(leave.getText().toString()));
        }
        else {
            leave.setError("Enter a non-negative number with no commas.");
            errorCheck = true;
        }
        if (isNumeric(gymAllowance.getText().toString()) && (Double.parseDouble(gymAllowance.getText().toString()) >= 0) && (Double.parseDouble(gymAllowance.getText().toString()) <= 500)) {
            job.setGymMembershipAllowance(Double.parseDouble(gymAllowance.getText().toString()));
        }
        else {
            gymAllowance.setError("Enter a number between 0 and 500.");
            errorCheck = true;
        }
        if (!errorCheck) {
            if (apis.getCurrentJob() == null) {
                apis.add(job);
            }
            else if(job.isCurrentJob()){
                apis.update(job);
            }
            else if (apis.get(job.getId()) == null) {
            //else if (!job.isCurrentJob() && apis.get(job.getId()) == null) {
                apis.add(job);
            }
            else {
                apis.update(job);
            }
            //jobSaved = true;
            //sJob = job;
            return 0;
        }
        errorCheck = false;
        return 1;
    }
}