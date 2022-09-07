package edu.gatech.seclass.jobcompare6300;

import android.app.Activity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.apis.Apis;
import edu.gatech.seclass.jobcompare6300.models.Job;


public class ComparePageActivity extends AppCompatActivity{

    Apis apis = new Apis(this);
    Integer compareJob1ID= 0;
    Integer compareJob2ID= 0;
    private JobsAdapter jAdapter;
    ActivityResultLauncher<Intent> compareLauncher;
    //int offerJobId;
    Job job = new Job();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  //offerJobId =  ;
                  if (position > 0) {
                      compareJob1ID = position;
                      //offerJobId = position+1;
                      String selectedItemText = (String) apis.get(position).toString();
                      //Job job = apis.get(position);
                      job = apis.get(compareJob1ID);


                      Toast.makeText
                              (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                              .show();

                      //job = apis.get(offerJobId);
                      //System.out.println("ID: " + job.getId() + " Job Name: " + job.getTitle() + " Company: " + job.getCompany());
                  }
              }
              @Override
              public void onNothingSelected(AdapterView<?> parent) { }
          });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   //offerJobId =  ;
                   if (position > 0) {
                       compareJob2ID = position;
                       //offerJobId = position+1;
                       String selectedItemText = (String) apis.get(position).toString();
                       //Job job = apis.get(position);
                       job = apis.get(compareJob2ID);


                       Toast.makeText
                               (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                               .show();

                       //job = apis.get(offerJobId);
                       //System.out.println("ID: " + job.getId() + " Job Name: " + job.getTitle() + " Company: " + job.getCompany());
                   } else {
                       compareJob1ID= 0;
                       compareJob2ID= 0;
                   }
               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jAdapter = new JobsAdapter(this, apis);
        recyclerView.setAdapter(jAdapter);

        //List<Job> jobOfferList = apis.getAllJobs();
        List<Job> jobOfferList = apis.getAllJobsUnranked();
        //List<Job> jobOfferList2 = apis.getAllJobs();
        List<Job> jobOfferList2 = apis.getAllJobsUnranked();

        ArrayAdapter arr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jobOfferList);
        arr.insert("Select Job 1",0);
        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr);
        ArrayAdapter arr2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jobOfferList2);
        arr2.insert("Select Job 2",0);
        spinner2.setAdapter(arr2);









        compareLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Toast compareDone = Toast.makeText(ComparePageActivity.this, "Comparison completed.", Toast.LENGTH_SHORT);
                        compareDone.show();
                    }
                });

    }

    public void onCompare(View view) {

        if (!(compareJob1ID.equals(0)||compareJob2ID.equals(0))) {
            if (!(compareJob1ID.equals(compareJob2ID))){
                Intent sendIntent = new Intent(this, ComparisonActivity.class);
                System.out.println(compareJob1ID);
                System.out.println(compareJob2ID);
                String stringCurrentJob = Integer.toString(compareJob1ID);
                String stringOffer = Integer.toString(compareJob2ID);
                sendIntent.putExtra("CurrentJob", stringCurrentJob);
                sendIntent.putExtra("Offer", stringOffer);

                compareLauncher.launch(sendIntent);
                setResult(RESULT_OK, sendIntent);
            } else {
                Toast.makeText
                        (getApplicationContext(), "Please select two different jobs", Toast.LENGTH_SHORT)
                        .show();
            }



        }else {
            Toast.makeText
                    (getApplicationContext(), "Please select two different jobs", Toast.LENGTH_SHORT)
                    .show();

        }








    }

    public void onReturn(View view) {
        finish();
    }


}
