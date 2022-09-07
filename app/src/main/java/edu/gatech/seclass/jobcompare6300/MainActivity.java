package edu.gatech.seclass.jobcompare6300;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean created;

    ActivityResultLauncher<Intent> cJLauncher, jOLauncher, aWSLauncher, compLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        Job job = new Job();
        job.setCompany("Cisco");
        job.setTitle("Software Engineer ||");
        job.setCurrentJob(true);
        jobsApis.add(job);


        for(Job j : jobsApis.getAllJobs()) {
            if(j.isCurrentJob()) {
                j.setCompany("Airbnb");
                jobsApis.update(j);
            }
        }


        for(Job j : jobsApis.getAllJobs()) {
            if(j.isCurrentJob()) {
                int id = j.getId();
                jobsApis.deleteJob(id);
            }
        }
        */

        cJLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent receiveIntent = result.getData();
                            String receiveUser = receiveIntent.getStringExtra("CurrentUser");
                            Toast saved = Toast.makeText(MainActivity.this, "Current job saved", Toast.LENGTH_SHORT);
                            saved.show();

                        }
                    }
                });
        jOLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent receiveIntent = result.getData();
                        }
                    }
                });
        compLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent receiveIntent = result.getData();
                        }
                    }
                });
        aWSLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent receiveIntent = result.getData();
                            Toast saved = Toast.makeText(MainActivity.this, "Weights saved", Toast.LENGTH_SHORT);
                            saved.show();
                        }
                    }
                });

    }

/* Examples of how to use apis
    private void testAddJob() {
        JobsApis jobsApis = new JobsApis(this);
        Job job = new Job();
        job.setCompany("Facebook");
        job.setTitle("Software Engineer");
        jobsApis.add(job);
    }

    private void clearAllJobs() {
        JobsApis jobsApis = new JobsApis(this);
        jobsApis.removeAllJobs();
    }

    private List<Job> getAllJobs() {
        JobsApis jobsApis = new JobsApis(this);
        List<Job> jobs = jobsApis.getAllJobs();
        return jobs;
    }
    */



    public void currentJobClick(View view) {
        Intent sendIntent = new Intent(this, CurrentJobActivity.class);
        //Gson gson = new Gson();
        //String stringUserSent = gson.toJson(user);
        //sendIntent.putExtra("CurrentUser", stringUserSent);
        cJLauncher.launch(sendIntent);
    }

    public void jobOffersClick(View view) {
        Intent sendIntent = new Intent(this, JobOffersActivity.class);
        //Gson gson = new Gson();
        //String stringUserSent = gson.toJson(user);
        //sendIntent.putExtra("CurrentUser", stringUserSent);

        jOLauncher.launch(sendIntent);
    }

    public void settingsClick(View view) {
        Intent sendIntent = new Intent(this, AdjustSettingsActivity.class);
        //Gson gson = new Gson();
        //String stringUserSent = gson.toJson(user);
        //sendIntent.putExtra("CurrentUser", stringUserSent);

        aWSLauncher.launch(sendIntent);
    }

    public void compareClick(View view) {
        Intent sendIntent = new Intent(this, ComparePageActivity.class);
        //Gson gson = new Gson();
        //String stringUserSent = gson.toJson(user);
        //sendIntent.putExtra("CurrentUser", stringUserSent);

        compLauncher.launch(sendIntent);
    }


    public void onResetCurrentJob (View view) {
        /*
        user.setCurrentJob(null);
        user.getJobOffers().set(0, new Job());
        Toast reset = Toast.makeText(MainActivity.this, "Current job reset", Toast.LENGTH_SHORT);
        reset.show();
        */
    }
    public void onResetJobOffers (View view) {
        /*
        user.setJobOffers(new ArrayList<Job>());
        user.getJobOffers().add(new Job());
        user.setOfferNumber(user.getJobOffers().size());
        Toast reset = Toast.makeText(MainActivity.this, "Job offers reset", Toast.LENGTH_SHORT);
        reset.show();
        */
    }
    public void onResetWeightSettings (View view) {
        /*
        //user.setWeights(new int[5]);
        //Toast reset = Toast.makeText(MainActivity.this, "Weights reset", Toast.LENGTH_SHORT);
        reset.show();
        */
    }

}