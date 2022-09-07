package edu.gatech.seclass.jobcompare6300.models;

public class Job {
    private int id;
    private String title = "";
    private String company = "";
    private String location = "";
    private double costOfLiving = 0.0;
    private double yearlySalary = 0.0;
    private double yearlyBonus = 0.0;
    private int allowedWeeklyTeleworkDays = 0;
    private int leaveTime = 0;
    private double gymMembershipAllowance = 0.0;
    private boolean isCurrentJob = false;
    private double score = 0.0;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getCostOfLiving() {
        return this.costOfLiving;
    }

    public void setCostOfLiving(double costOfLiving) {
        this.costOfLiving = costOfLiving;
    }

    public double getYearlySalary() {
        return this.yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public double getYearlyBonus() {
        return this.yearlyBonus;
    }

    public void setYearlyBonus(double yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public int getAllowedWeeklyTeleworkDays() {
        return this.allowedWeeklyTeleworkDays;
    }

    public void setAllowedWeeklyTeleworkDays(int allowedWeeklyTeleworkDays) {
        this.allowedWeeklyTeleworkDays = allowedWeeklyTeleworkDays;
    }

    public int getLeaveTime() {
        return this.leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public double getGymMembershipAllowance() {
        return gymMembershipAllowance;
    }

    public void setGymMembershipAllowance(double gymMembershipAllowance) {
        this.gymMembershipAllowance = gymMembershipAllowance;
    }

    public boolean isCurrentJob() {
        return this.isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        this.isCurrentJob = currentJob;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String toString() {
        if (this.isCurrentJob) {
            return "Score: " + String.valueOf(this.getScore()) + "', Title: '" + this.getTitle() + "(Current)', Company: '" + this.getCompany() + "'";

        } else {
            return "Title: '" + this.getTitle() + "', Company: '" + this.getCompany() + "'";
        }
    }
}
