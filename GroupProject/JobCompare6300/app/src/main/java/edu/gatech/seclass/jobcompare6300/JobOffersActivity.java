package edu.gatech.seclass.jobcompare6300;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



import edu.gatech.seclass.jobcompare6300.apis.Apis;
import edu.gatech.seclass.jobcompare6300.models.Job;

public class JobOffersActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText title;
    EditText company;
    EditText location;
    EditText cOL;
    EditText salary;
    EditText bonus;
    EditText teleworkDays;
    EditText leave;
    EditText gymAllowance;


    //List<Job> jobOffers = new ArrayList<Job>();
    Job job = new Job();
    Apis apis = new Apis(this);
    boolean errorCheck = false;
    int offerNumber;
    ActivityResultLauncher<Intent> compareLauncher;
    //Job[] compareTwo = new Job[2];
    int currentJobId;
    int offerJobId;
    SaveJob saveJob = new SaveJob();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offers);

        Spinner spinner = (Spinner) findViewById(R.id.selectJob);
        spinner.setOnItemSelectedListener(this);
        Button button= (Button) findViewById(R.id.CompareID);
        /*
        List<Job> tempList = apis.getAllJobs();
        List<Job> jobOfferList = new ArrayList<Job>();
        //Null pointer exception
        Job currentJob = apis.getCurrentJob();
        if(currentJob == null) {
            currentJob = new Job();
        }

        for(int i = 0; i < tempList.size(); i++) {
            Job tempJob = tempList.get(i);

            int currentID = currentJob.getId();
            //System.out.println("current ID: " + currentID);

            if ((tempJob.getId()) != currentID) {
                jobOfferList.add(tempJob);
                System.out.println("job ID: " + tempJob.getId());
            }
        }
        */

        ArrayAdapter arr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, apis.getAllJobsUnranked());
        arr.insert("Select your Job Offer",0);
        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr);

        title = (EditText) findViewById(R.id.titleID);
        company = (EditText) findViewById(R.id.companyID);
        location = (EditText) findViewById(R.id.locationID);
        cOL = (EditText) findViewById(R.id.costOfLivingID);
        salary = (EditText) findViewById(R.id.salaryID);
        bonus = (EditText) findViewById(R.id.bonusID);
        teleworkDays = (EditText) findViewById(R.id.teleworkDaysID);
        leave = (EditText) findViewById(R.id.leaveID);
        gymAllowance = (EditText) findViewById(R.id.gymID);

        title.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equals("")){
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

        compareLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Toast compareDone = Toast.makeText(JobOffersActivity.this, "Comparison completed.", Toast.LENGTH_SHORT);
                        compareDone.show();
                    }
                });
    }

    public void clearForm() {
        title.setText("");
        company.setText("");
        location.setText("");
        cOL.setText("");
        salary.setText("");
        bonus.setText("");
        teleworkDays.setText("");
        leave.setText("");
        gymAllowance.setText("");
    }
    public boolean isNumeric(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void save(View view) {
        int status = saveJob.save(view, job, apis, title, company, location, cOL, salary, bonus, teleworkDays, leave, gymAllowance);
        if (status == 0) {
            Intent sIntent = new Intent(this, MainActivity.class);
            setResult(RESULT_OK, sIntent);
            Toast saveToast = Toast.makeText(JobOffersActivity.this, "Success: Job Saved.", Toast.LENGTH_SHORT);
            saveToast.show();
        }
    }

    public void onSave(View view) {
        save(view);

        Spinner spinner = (Spinner) findViewById(R.id.selectJob);
        spinner.setOnItemSelectedListener(this);

        List<Job> tempList = apis.getAllJobs();
        List<Job> jobOfferList = new ArrayList<Job>();
        Job currentJob = apis.getCurrentJob();
        if(currentJob == null) {
            currentJob = new Job();
        }

        /*
        for(int i = 0; i < tempList.size(); i++) {
            Job tempJob = tempList.get(i);
            int currentID = currentJob.getId();
            System.out.println("current ID: " + currentID);

            if ((tempJob.getId()) != currentID) {
                jobOfferList.add(tempJob);
                System.out.println("job ID: " + tempJob.getId());
            }
        }
        */
        ArrayAdapter arr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, apis.getAllJobsUnranked());
        arr.insert("Select your Job Offer",0);
        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr);

    }

    public void onEnterAnother(View view) {
        save(view);
        job = new Job();

        Spinner spinner = (Spinner) findViewById(R.id.selectJob);
        spinner.setOnItemSelectedListener(this);
        /*
        List<Job> tempList = apis.getAllJobs();
        List<Job> jobOfferList = new ArrayList<Job>();
        Job currentJob = apis.getCurrentJob();
        if(currentJob == null) {
            currentJob = new Job();
        }

        for(int i = 0; i < tempList.size(); i++) {
            Job tempJob = tempList.get(i);
            int currentID = currentJob.getId();
            System.out.println("current ID: " + currentID);

            if ((tempJob.getId()) != currentID) {
                jobOfferList.add(tempJob);
                System.out.println("job ID: " + tempJob.getId());
            }
        }

        */

        ArrayAdapter arr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, apis.getAllJobsUnranked());
        arr.insert("Select your Job Offer",0);
        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr);

        Toast saveToast = Toast.makeText(JobOffersActivity.this, "Success: Job Saved.", Toast.LENGTH_SHORT);
        saveToast.show();

        clearForm();
    }
    /* Can reconsider this when ComparisonActivity is done. Maybe we can simply pass on the IDs? */
    public void onCompare(View view) {
        if (apis.getAllJobs().size() > 1 && !job.getTitle().equals("")) {

            Toast comparison = Toast.makeText(JobOffersActivity.this, "Comparing offer to current job.", Toast.LENGTH_SHORT);
            comparison.show();

            Intent sendIntent = new Intent(this, ComparisonActivity.class);

            currentJobId = apis.getCurrentJob().getId();
            //if (offerJobId == 0){ }


            //Gson gson = new Gson();
            String stringCurrentJob = Integer.toString(currentJobId);
            String stringOffer = Integer.toString(offerJobId);
            sendIntent.putExtra("CurrentJob", stringCurrentJob);
            sendIntent.putExtra("Offer", stringOffer);
            //String offerNoString = (offerNumber - 1) + "";
            //sendIntent.putExtra("OfferNumber", offerNoString);
            compareLauncher.launch(sendIntent);
            setResult(RESULT_OK, sendIntent);

        }
        else if (apis.getCurrentJob() == null){
            Toast error = Toast.makeText(JobOffersActivity.this, "Error: current job not present", Toast.LENGTH_LONG);
            error.show();
        }
        else {
            Toast error = Toast.makeText(JobOffersActivity.this, "Error: job detail incomplete or not saved " + offerNumber, Toast.LENGTH_LONG);
            error.show();
        }


    }

    public void onCancel(View view) {
        //user.setJobOffers(new ArrayList<Job>());
        //offerNumber = user.getJobOffers().size() + 1;
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //offerJobId =  ;
        if (position == 0){
            clearForm();
        }
        if (position > 0) {

            offerJobId = position;
            //offerJobId = position+1;
            String selectedItemText = (String) apis.get(position).toString();
            //Job job = apis.get(position);
            job = apis.get(offerJobId);
            title.setText(job.getTitle());
            company.setText(job.getCompany());
            location.setText(job.getLocation());
            cOL.setText(String.valueOf(job.getCostOfLiving()));
            salary.setText(String.valueOf(job.getYearlySalary()));
            bonus.setText(String.valueOf(job.getYearlyBonus()));
            teleworkDays.setText(String.valueOf(job.getAllowedWeeklyTeleworkDays()));
            leave.setText(String.valueOf(job.getLeaveTime()));
            gymAllowance.setText(String.valueOf(job.getGymMembershipAllowance()));
            Toast.makeText
                    (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                    .show();

            /*if (job.isCurrentJob()){
                clearForm();
                Toast.makeText
                        (getApplicationContext(), "Enter or Select a Job Offer", Toast.LENGTH_SHORT)
                        .show();

            }else {

                title.setText(job.getTitle());
                company.setText(job.getCompany());
                location.setText(job.getLocation());
                cOL.setText(String.valueOf(job.getCostOfLiving()));
                salary.setText(String.valueOf(job.getYearlySalary()));
                bonus.setText(String.valueOf(job.getYearlyBonus()));
                teleworkDays.setText(String.valueOf(job.getAllowedWeeklyTeleworkDays()));
                leave.setText(String.valueOf(job.getLeaveTime()));
                gymAllowance.setText(String.valueOf(job.getGymMembershipAllowance()));
                Toast.makeText
                        (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                        .show();
            }*/

            //job = apis.get(offerJobId);
            //System.out.println("ID: " + job.getId() + " Job Name: " + job.getTitle() + " Company: " + job.getCompany());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}