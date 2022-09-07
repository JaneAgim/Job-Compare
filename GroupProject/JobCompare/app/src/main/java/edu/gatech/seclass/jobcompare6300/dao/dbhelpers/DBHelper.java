package edu.gatech.seclass.jobcompare6300.dao.dbhelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.dao.utils.ComparisonSettingsContract;
import edu.gatech.seclass.jobcompare6300.dao.utils.JobsContract;
import edu.gatech.seclass.jobcompare6300.models.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.models.Job;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String databaseName){
        super(context, databaseName,null,1);
    }

    private String createJobsTableQuery(){
        String query = "create table " + JobsContract.JobEntry.TABLE_NAME;
        query += " ( ";
        query += "id integer primary key, ";
        query += JobsContract.JobEntry.COLUMN_NAME_TITLE + " text, ";
        query += JobsContract.JobEntry.COLUMN_NAME_COMPANY + " text, ";
        query += JobsContract.JobEntry.COLUMN_NAME_LOCATION + " text, ";
        query += JobsContract.JobEntry.COLUMN_NAME_COST_OF_LIVING + " real, ";
        query += JobsContract.JobEntry.COLUMN_NAME_YEARLY_SALARY + " real, ";
        query += JobsContract.JobEntry.COLUMN_NAME_YEARLY_BONUS + " real, ";
        query += JobsContract.JobEntry.COLUMN_NAME_ALLOWED_WEEKLY_TELEWORK_DAYS + " integer, ";
        query += JobsContract.JobEntry.COLUMN_NAME_LEAVE_TIME + " integer, ";
        query += JobsContract.JobEntry.COLUMN_NAME_GYM_MEMBERSHIP_ALLOWANCE + " real, ";
        query += JobsContract.JobEntry.COLUMN_NAME_IS_CURRENT_JOB + " integer";
        query += " )";
        return query;
    }

    private String createComparisonSettingsTableQuery(){
        String query = "create table " + ComparisonSettingsContract.ComparisonSettingsEntry.TABLE_NAME;
        query += " ( ";
        query += "id integer primary key, ";
        query += ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT + " integer, ";
        query += ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT + " integer, ";
        query += ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_ALLOWED_WEEKLY_TELEWORK_DAYS_WEIGHT + " integer, ";
        query += ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_LEAVE_TIME_WEIGHT + " integer, ";
        query += ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_GYM_MEMBERSHIP_ALLOWANCE_WEIGHT + " integer";
        query += " )";
        return query;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE jobs");
        sqLiteDatabase.execSQL("DROP TABLE comparisonSettings");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createJobsTableQuery = createJobsTableQuery();
        String createComparisonSettingsTableQuery = createComparisonSettingsTableQuery();
        sqLiteDatabase.execSQL(createJobsTableQuery);
        sqLiteDatabase.execSQL(createComparisonSettingsTableQuery);
    }


    public void insert(Job job){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_TITLE, job.getTitle());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_COMPANY, job.getCompany());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_LOCATION, job.getLocation());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_COST_OF_LIVING, job.getCostOfLiving());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_YEARLY_SALARY, job.getYearlySalary());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_YEARLY_BONUS, job.getYearlyBonus());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_ALLOWED_WEEKLY_TELEWORK_DAYS, job.getAllowedWeeklyTeleworkDays());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_LEAVE_TIME, job.getLeaveTime());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_GYM_MEMBERSHIP_ALLOWANCE, job.getGymMembershipAllowance());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_IS_CURRENT_JOB, job.isCurrentJob());
        sqLiteDatabase.insert(JobsContract.JobEntry.TABLE_NAME, null, contentValues);
    }


    public void update(Job job) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_TITLE, job.getTitle());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_COMPANY, job.getCompany());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_LOCATION, job.getLocation());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_COST_OF_LIVING, job.getCostOfLiving());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_YEARLY_SALARY, job.getYearlySalary());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_YEARLY_BONUS, job.getYearlyBonus());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_ALLOWED_WEEKLY_TELEWORK_DAYS, job.getAllowedWeeklyTeleworkDays());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_LEAVE_TIME, job.getLeaveTime());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_GYM_MEMBERSHIP_ALLOWANCE, job.getGymMembershipAllowance());
        contentValues.put(JobsContract.JobEntry.COLUMN_NAME_IS_CURRENT_JOB, job.isCurrentJob());
        sqLiteDatabase.update(JobsContract.JobEntry.TABLE_NAME, contentValues, "id = ?",new String[] { Integer.toString(job.getId()) });
        //sqLiteDatabase.close();
    }


    public Job get(Integer id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor =  sqLiteDatabase.rawQuery( "select * from " + JobsContract.JobEntry.TABLE_NAME + " where id = " + id, null );
        if(cursor.moveToFirst() && !cursor.isAfterLast()) {
            return assignJobValues(cursor);
        }
        cursor.close();
        return null;
    }


    public List<Job> get(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        List<Job> jobs = new ArrayList<Job>();
        Cursor cursor =  sqLiteDatabase.rawQuery( "select * from " + JobsContract.JobEntry.TABLE_NAME, null );
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            jobs.add(assignJobValues(cursor));
        }
        cursor.close();
        //sqLiteDatabase.close();
        return jobs;
    }


    public boolean delete(Integer id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(JobsContract.JobEntry.TABLE_NAME,"id" + "= ? ", new String[] { Integer.toString(id) }) > 0;
    }

    public void clearJobsTable() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from "+ JobsContract.JobEntry.TABLE_NAME);
        //sqLiteDatabase.close();
    }

    private Job assignJobValues(Cursor cursor) {
        Job job = new Job();
        job.setId(cursor.getInt(cursor.getColumnIndex("id")));
        job.setTitle(cursor.getString(cursor.getColumnIndex(JobsContract.JobEntry.COLUMN_NAME_TITLE)));
        job.setCompany(cursor.getString(cursor.getColumnIndex(JobsContract.JobEntry.COLUMN_NAME_COMPANY)));
        job.setLocation(cursor.getString(cursor.getColumnIndex(JobsContract.JobEntry.COLUMN_NAME_LOCATION)));
        job.setCostOfLiving(cursor.getDouble(cursor.getColumnIndex(JobsContract.JobEntry.COLUMN_NAME_COST_OF_LIVING)));
        job.setYearlySalary(cursor.getDouble(cursor.getColumnIndex(JobsContract.JobEntry.COLUMN_NAME_YEARLY_SALARY)));
        job.setYearlyBonus(cursor.getDouble(cursor.getColumnIndex(JobsContract.JobEntry.COLUMN_NAME_YEARLY_BONUS)));
        job.setAllowedWeeklyTeleworkDays(cursor.getInt(cursor.getColumnIndex(JobsContract.JobEntry.COLUMN_NAME_ALLOWED_WEEKLY_TELEWORK_DAYS)));
        job.setLeaveTime(cursor.getInt(cursor.getColumnIndex(JobsContract.JobEntry.COLUMN_NAME_LEAVE_TIME)));
        job.setGymMembershipAllowance(cursor.getDouble(cursor.getColumnIndex(JobsContract.JobEntry.COLUMN_NAME_GYM_MEMBERSHIP_ALLOWANCE)));
        if(cursor.getInt(cursor.getColumnIndex(JobsContract.JobEntry.COLUMN_NAME_IS_CURRENT_JOB)) > 0){
            job.setCurrentJob(true);
        }
        return job;
    }





    // ComparisonSettings
    public void insertComparisonSettings(ComparisonSettings comparisonSettings) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT, comparisonSettings.getYearlySalaryWeight());
        contentValues.put(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT, comparisonSettings.getYearlyBonusWeight());
        contentValues.put(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_ALLOWED_WEEKLY_TELEWORK_DAYS_WEIGHT, comparisonSettings.getAllowedWeeklyTeleworkDaysWeight());
        contentValues.put(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_LEAVE_TIME_WEIGHT, comparisonSettings.getLeaveTimeWeight());
        contentValues.put(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_GYM_MEMBERSHIP_ALLOWANCE_WEIGHT, comparisonSettings.getGymMembershipAllowanceWeight());
        sqLiteDatabase.insert(ComparisonSettingsContract.ComparisonSettingsEntry.TABLE_NAME, null, contentValues);
    }


    public void clearComparisonSettingsTable() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from "+ ComparisonSettingsContract.ComparisonSettingsEntry.TABLE_NAME);
    }


    public void update(ComparisonSettings comparisonSettings) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT, comparisonSettings.getYearlySalaryWeight());
        contentValues.put(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT, comparisonSettings.getYearlyBonusWeight());
        contentValues.put(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_ALLOWED_WEEKLY_TELEWORK_DAYS_WEIGHT, comparisonSettings.getAllowedWeeklyTeleworkDaysWeight());
        contentValues.put(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_LEAVE_TIME_WEIGHT, comparisonSettings.getLeaveTimeWeight());
        contentValues.put(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_GYM_MEMBERSHIP_ALLOWANCE_WEIGHT, comparisonSettings.getGymMembershipAllowanceWeight());
        sqLiteDatabase.update(ComparisonSettingsContract.ComparisonSettingsEntry.TABLE_NAME, contentValues, "id = ?",new String[] { Integer.toString(1) });
    }

    public ComparisonSettings getComparisonSettings() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor =  sqLiteDatabase.rawQuery( "select * from " + ComparisonSettingsContract.ComparisonSettingsEntry.TABLE_NAME + " where id = 1", null );

        if(cursor.moveToFirst() && !cursor.isAfterLast()) {
            return assignComparisonSettingsValues(cursor);
        }
        cursor.close();
        return null;
    }

    private ComparisonSettings assignComparisonSettingsValues(Cursor cursor) {
        ComparisonSettings comparisonSettings = new ComparisonSettings();
        comparisonSettings.setYearlySalaryWeight(cursor.getInt(cursor.getColumnIndex(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT)));
        comparisonSettings.setYearlyBonusWeight(cursor.getInt(cursor.getColumnIndex(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT)));
        comparisonSettings.setAllowedWeeklyTeleworkDays(cursor.getInt(cursor.getColumnIndex(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_ALLOWED_WEEKLY_TELEWORK_DAYS_WEIGHT)));
        comparisonSettings.setLeaveTimeWeight(cursor.getInt(cursor.getColumnIndex(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_LEAVE_TIME_WEIGHT)));
        comparisonSettings.setGymMembershipAllowanceWeight(cursor.getInt(cursor.getColumnIndex(ComparisonSettingsContract.ComparisonSettingsEntry.COLUMN_NAME_GYM_MEMBERSHIP_ALLOWANCE_WEIGHT)));
        return comparisonSettings;
    }
}
