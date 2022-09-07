package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
//import com.google.gson.Gson;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.apis.Apis;
import edu.gatech.seclass.jobcompare6300.dao.utils.ComparisonSettingsContract;
import edu.gatech.seclass.jobcompare6300.models.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.models.Job;


public class CurrentJobActivity extends AppCompatActivity {
    EditText title;
    EditText company;
    EditText location;
    EditText cOL;
    EditText salary;
    EditText bonus;
    EditText teleworkDays;
    EditText leave;
    EditText gymAllowance;

    //User user;
    Job job;
    Apis apis = new Apis(this);
    boolean errorCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_job);
        /*
        Job job1 = new Job();
        Job job2 = new Job();
        apis.add(job1);
        apis.add(job2);

        List<Job> alljobs = apis.getAllJobs();*/
       // apis.removeComparisonSettings();
       // comparisonSettings.setGymMembershipAllowanceWeight(10);
       // apis.updateComparisonSettings(comparisonSettings);
      //  jobsApis.insertComparisonSettings(new ComparisonSettings());
        //ComparisonSettings comparisonSettings = jobsApis.getComparisonSettings();
        //Gson gson = new Gson();
        //String receiveUser = getIntent().getStringExtra("CurrentUser");
        //user = gson.fromJson(receiveUser, User.class);

        title = (EditText) findViewById(R.id.titleID);
        company = (EditText) findViewById(R.id.companyID);
        location = (EditText) findViewById(R.id.locationID);
        cOL = (EditText) findViewById(R.id.costOfLivingID);
        salary = (EditText) findViewById(R.id.salaryID);
        bonus = (EditText) findViewById(R.id.bonusID);
        teleworkDays = (EditText) findViewById(R.id.teleworkDaysID);
        leave = (EditText) findViewById(R.id.leaveID);
        gymAllowance = (EditText) findViewById(R.id.gymID);

        /*
        private void testAddJob() {
            JobsApis jobsApis = new JobsApis(this);
            Job job = new Job();
            job.setCompany("Facebook");
            job.setTitle("Software Engineer");
            jobsApis.add(job);
        }

         */

        //JobsApis jobsApis = new JobsApis(this);

        if (!(apis.getCurrentJob() == null)) {
            job = apis.getCurrentJob();
            title.setText(job.getTitle());
            company.setText(job.getCompany());
            location.setText(job.getLocation());
            cOL.setText(String.valueOf(job.getCostOfLiving()));
            salary.setText(String.valueOf(job.getYearlySalary()));
            bonus.setText(String.valueOf(job.getYearlyBonus()));
            teleworkDays.setText(String.valueOf(job.getAllowedWeeklyTeleworkDays()));
            leave.setText(String.valueOf(job.getLeaveTime()));
            gymAllowance.setText(String.valueOf(job.getGymMembershipAllowance()));
        }
        else {
            job = new Job();
            job.setCurrentJob(true);
        }
    }

    public boolean isNumeric(String string) {
        try {
            Double.parseDouble(string);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    public void onSave(View view) {
        SaveJob saveJob = new SaveJob();
        int status = saveJob.save(view, job, apis, title, company, location, cOL, salary, bonus, teleworkDays, leave, gymAllowance);
        if (status == 0) {
            Intent sIntent = new Intent(this, MainActivity.class);
            setResult(RESULT_OK, sIntent);
            finish();
        }
        /*//Toast error = Toast.makeText(CurrentJobActivity.this,"Error, check fields.", Toast.LENGTH_LONG);
        if (!title.getText().toString().isEmpty()) {
            job.setTitle(title.getText().toString());
        }
        else {
            title.setError("This field cannot be left empty.");
            //error.show();
            errorCheck = true;
        }
        if (!company.getText().toString().isEmpty()) {
            job.setCompany(company.getText().toString());
        }
        else {
            company.setError("This field cannot be left empty.");
            //error.show();
            errorCheck = true;
        }
        if (!location.getText().toString().isEmpty()) {
            job.setLocation(location.getText().toString());
        }
        else {
            location.setError("This field cannot be left empty.");
            //error.show();
            errorCheck = true;
        }
        if (isNumeric(cOL.getText().toString()) && (Double.parseDouble(cOL.getText().toString()) > 0)) {
        //if (isNumeric(cOL.getText().toString()) && (Integer.parseInt(cOL.getText().toString()) > 0)) {
            job.setCostOfLiving(Double.parseDouble(cOL.getText().toString()));
            //job.setCostOfLiving(Integer.parseInt(cOL.getText().toString()));
        }
        else {
            cOL.setError("Enter a positive index integer.");
           // error.show();
            errorCheck = true;
        }
        if (isNumeric(salary.getText().toString())) {
            job.setYearlySalary(Double.parseDouble(salary.getText().toString()));
        }
        else {
            salary.setError("Enter a number with no commas.");
           // error.show();
            errorCheck = true;
        }
        if (isNumeric(bonus.getText().toString())) {
            job.setYearlyBonus(Double.parseDouble(bonus.getText().toString()));
        }
        else {
            bonus.setError("Enter a number with no commas.");
           // error.show();
            errorCheck = true;
        }
        if (isNumeric(teleworkDays.getText().toString()) && (Integer.parseInt(teleworkDays.getText().toString()) >= 0) && (Integer.parseInt(teleworkDays.getText().toString()) <= 5)) {
            job.setAllowedWeeklyTeleworkDays(Integer.parseInt(teleworkDays.getText().toString()));
        }
        else {
            teleworkDays.setError("Enter an integer between 0 and 5. ");
            //error.show();
            errorCheck = true;
        }
        if (isNumeric(leave.getText().toString())) {
            job.setLeaveTime(Integer.parseInt(leave.getText().toString()));
        }
        else {
            leave.setError("Enter a number with no commas.");
            //error.show();
            errorCheck = true;
        }
        if (isNumeric(gymAllowance.getText().toString()) && (Double.parseDouble(gymAllowance.getText().toString()) >= 0) && (Double.parseDouble(gymAllowance.getText().toString()) <= 500)) {
            job.setGymMembershipAllowance(Double.parseDouble(gymAllowance.getText().toString()));
        }
        else {
            gymAllowance.setError("Enter a number between 0 and 500.");
           // error.show();
            errorCheck = true;
        }
        if (!errorCheck) {
            //JobController.updateCurrentJob(Job)
            if (jobsApis.getCurrentJob() == null) {
                job.setCurrentJob(true);
                jobsApis.add(job);
                //user.getJobOffers().set(0, job);

                //
            }
            else {
                jobsApis.update(job);
                //user.getJobOffers().set(0, job);
            }

            Intent sIntent = new Intent(this, MainActivity.class);
            //Gson send = new Gson();
            //String sendUser = send.toJson(user);
            //sIntent.putExtra("CurrentUser", sendUser);
            setResult(RESULT_OK, sIntent);
            finish();
        }
        errorCheck = false;
    }
         */
    }

    public void onCancel(View view) {
        finish();
    }
}
