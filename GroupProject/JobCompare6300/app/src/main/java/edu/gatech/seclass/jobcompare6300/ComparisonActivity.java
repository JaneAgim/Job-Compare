package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.gatech.seclass.jobcompare6300.apis.Apis;
import edu.gatech.seclass.jobcompare6300.models.Job;

public class ComparisonActivity extends AppCompatActivity {
    TextView header1;
    TextView header2;

    TextView title;
    TextView company;
    TextView location;
    TextView cOL;
    TextView salary;
    TextView bonus;
    TextView teleworkDays;
    TextView leave;
    TextView gymAllowance;

    TextView title2;
    TextView company2;
    TextView location2;
    TextView cOL2;
    TextView salary2;
    TextView bonus2;
    TextView teleworkDays2;
    TextView leave2;
    TextView gymAllowance2;

    Apis apis = new Apis(this);

    //Job[] compared = new Job[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison);

        header2 = (TextView) findViewById(R.id.header2ID);

        title = (TextView) findViewById(R.id.titleID);
        company = (TextView) findViewById(R.id.companyID);
        location = (TextView) findViewById(R.id.locationID);
        cOL = (TextView) findViewById(R.id.costOfLivingID);
        salary = (TextView) findViewById(R.id.salaryID);
        bonus = (TextView) findViewById(R.id.bonusID);
        teleworkDays = (TextView) findViewById(R.id.teleworkDaysID);
        leave = (TextView) findViewById(R.id.leaveID);
        gymAllowance = (TextView) findViewById(R.id.gymID);

        title2 = (TextView) findViewById(R.id.title2ID);
        company2 = (TextView) findViewById(R.id.company2ID);
        location2 = (TextView) findViewById(R.id.location2ID);
        cOL2 = (TextView) findViewById(R.id.costOfLiving2ID);
        salary2 = (TextView) findViewById(R.id.salary2ID);
        bonus2 = (TextView) findViewById(R.id.bonus2ID);
        teleworkDays2 = (TextView) findViewById(R.id.teleworkDays2ID);
        leave2 = (TextView) findViewById(R.id.leave2ID);
        gymAllowance2 = (TextView) findViewById(R.id.gym2ID);


        String stringCurrentJob = getIntent().getStringExtra("CurrentJob");
        String stringOffer = getIntent().getStringExtra("Offer");
        if (!(stringCurrentJob == null)){
            if (!(stringOffer == null)){
                Job job1 = apis.get(Integer.parseInt(stringCurrentJob));
                Job job2 = apis.get(Integer.parseInt(stringOffer));

                System.out.println(job2);
                title.setText(job1.getTitle());
                company.setText(job1.getCompany());
                location.setText(job1.getLocation());
                cOL.setText(job1.getCostOfLiving() + "");
                salary.setText(job1.getYearlySalary() + "");
                bonus.setText(job1.getYearlyBonus() + "");
                teleworkDays.setText(job1.getAllowedWeeklyTeleworkDays() + "");
                leave.setText(job1.getLeaveTime() + "");
                gymAllowance.setText(job1.getGymMembershipAllowance() + "");

                title2.setText(job2.getTitle());
                company2.setText(job2.getCompany());
                location2.setText(job2.getLocation());
                cOL2.setText(job2.getCostOfLiving() + "");
                salary2.setText(job2.getYearlySalary() + "");
                bonus2.setText(job2.getYearlyBonus() + "");
                teleworkDays2.setText(job2.getAllowedWeeklyTeleworkDays() + "");
                leave2.setText(job2.getLeaveTime() + "");
                gymAllowance2.setText(job2.getGymMembershipAllowance() + "");
            }
        }




    }
    public void onReturn(View view) {
        finish();
    }
}