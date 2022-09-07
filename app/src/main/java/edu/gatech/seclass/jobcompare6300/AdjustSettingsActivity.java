package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.apis.Apis;
import edu.gatech.seclass.jobcompare6300.models.ComparisonSettings;

public class AdjustSettingsActivity extends AppCompatActivity {

    EditText yearlySalary;
    EditText yearlyBonus;
    EditText teleworkDays;
    EditText leaveWeight;
    EditText gymWeight;


    //int[] weights = new int[5];
    ComparisonSettings parameters = new ComparisonSettings();
    Apis apis = new Apis(this);
    boolean errorCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_settings);

        parameters = apis.getComparisonSettings();


        yearlySalary = (EditText) findViewById(R.id.yearlySalaryID);
        yearlyBonus = (EditText) findViewById(R.id.yearlyBonusID);
        teleworkDays = (EditText) findViewById(R.id.teleworkWeightID);
        leaveWeight = (EditText) findViewById(R.id.leaveWeightID);
        gymWeight = (EditText) findViewById(R.id.gymWeightID);



        yearlySalary.setText(parameters.getYearlySalaryWeight()+ "");
        yearlyBonus.setText(parameters.getYearlyBonusWeight() + "");
        teleworkDays.setText(parameters.getAllowedWeeklyTeleworkDaysWeight() + "");
        leaveWeight.setText(parameters.getLeaveTimeWeight()+ "");
        gymWeight.setText(parameters.getGymMembershipAllowanceWeight() + "");
    }

    public boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    public boolean isPosNum(int value) {
        if (value > 0)
            return true;
        else
            return false;
    }

    public void onReturnToMain(View view) {
        if (isNumeric(yearlySalary.getText().toString())) {
            if(isPosNum(Integer.parseInt(yearlySalary.getText().toString())))
                parameters.setYearlySalaryWeight(Integer.parseInt(yearlySalary.getText().toString()));
            else {
                yearlySalary.setError("Enter positive integer in this field");
                errorCheck = true;
            }
        }
        else {
            yearlySalary.setError("Enter positive integer in this field");
            errorCheck = true;
        }
        if (isNumeric(yearlyBonus.getText().toString())) {
            if(isPosNum(Integer.parseInt(yearlyBonus.getText().toString())))
                parameters.setYearlyBonusWeight(Integer.parseInt(yearlyBonus.getText().toString()));
            else {
                yearlyBonus.setError("Enter positive integer in this field");
                errorCheck = true;
            }
        }
        else {
            yearlyBonus.setError("Enter positive integer in this field");
            errorCheck = true;
        }
        if (isNumeric(teleworkDays.getText().toString())) {
            if(isPosNum(Integer.parseInt(teleworkDays.getText().toString())))
                parameters.setAllowedWeeklyTeleworkDays(Integer.parseInt(teleworkDays.getText().toString()));
            else {
                teleworkDays.setError("Enter positive integer in this field");
                errorCheck = true;
            }
        }
        else {
            teleworkDays.setError("Enter positive integer in this field");
            errorCheck = true;
        }
        if (isNumeric(leaveWeight.getText().toString())) {
            if(isPosNum(Integer.parseInt(teleworkDays.getText().toString())))
                parameters.setLeaveTimeWeight(Integer.parseInt(leaveWeight.getText().toString()));
            else {
                leaveWeight.setError("Enter positive integer in this field");
                errorCheck = true;
            }
        }
        else {
            leaveWeight.setError("Enter positive integer in this field");
            errorCheck = true;
        }
        if (isNumeric(gymWeight.getText().toString())) {
            if(isPosNum(Integer.parseInt(gymWeight.getText().toString())))
                parameters.setGymMembershipAllowanceWeight(Integer.parseInt(gymWeight.getText().toString()));
            else {
                gymWeight.setError("Enter positive integer in this field");
                errorCheck = true;
            }
        }
        else {
            gymWeight.setError("Enter positive integer in this field");
            errorCheck = true;
        }
        if (!errorCheck) {
            /*
            //Replace with ComparisonSettingsApis.setComparisonSetting(parameter)
            //where parameter = ComparisonSetting (Model) object
            user.setWeights(weights);
            */
            Intent sIntent = new Intent(this, MainActivity.class);
            apis.updateComparisonSettings(parameters);

            setResult(RESULT_OK, sIntent);
            finish();
        }
        else {
            Toast error = Toast.makeText(AdjustSettingsActivity.this, "Error: check fields", Toast.LENGTH_LONG);
            error.show();
            errorCheck = false;
        }

    }
}