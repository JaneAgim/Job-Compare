package edu.gatech.seclass.jobcompare6300.models;

public class ComparisonSettings {
    private int yearlySalaryWeight = 1;
    private int yearlyBonusWeight = 1;
    private int allowedWeeklyTeleworkDaysWeight = 1;
    private int leaveTimeWeight = 1;
    private int gymMembershipAllowanceWeight = 1;

    public int getYearlySalaryWeight() {
        return yearlySalaryWeight;
    }

    public void setYearlySalaryWeight(int yearlySalaryWeight) {
        this.yearlySalaryWeight = yearlySalaryWeight;
    }

    public int getYearlyBonusWeight() {
        return yearlyBonusWeight;
    }

    public void setYearlyBonusWeight(int yearlyBonusWeight) {
        this.yearlyBonusWeight = yearlyBonusWeight;
    }

    public int getAllowedWeeklyTeleworkDaysWeight() {
        return allowedWeeklyTeleworkDaysWeight;
    }

    public void setAllowedWeeklyTeleworkDays(int allowedWeeklyTeleworkDaysWeight) {
        this.allowedWeeklyTeleworkDaysWeight = allowedWeeklyTeleworkDaysWeight;
    }

    public int getLeaveTimeWeight() {
        return leaveTimeWeight;
    }

    public void setLeaveTimeWeight(int leaveTimeWeight) {
        this.leaveTimeWeight = leaveTimeWeight;
    }

    public int getGymMembershipAllowanceWeight() {
        return gymMembershipAllowanceWeight;
    }

    public void setGymMembershipAllowanceWeight(int gymMembershipAllowanceWeight) {
        this.gymMembershipAllowanceWeight = gymMembershipAllowanceWeight;
    }

}
