package edu.gatech.seclass.jobcompare6300.dao.utils;

import android.provider.BaseColumns;

public class ComparisonSettingsContract {
    private ComparisonSettingsContract() {}
    public static class ComparisonSettingsEntry implements BaseColumns {
        public static final String TABLE_NAME = "comparisonSettings";
        public static final String COLUMN_NAME_YEARLY_SALARY_WEIGHT = "yearlySalaryWeight";
        public static final String COLUMN_NAME_YEARLY_BONUS_WEIGHT = "yearlyBonusWeight";
        public static final String COLUMN_NAME_ALLOWED_WEEKLY_TELEWORK_DAYS_WEIGHT = "allowedWeeklyTeleworkDaysWeight";
        public static final String COLUMN_NAME_LEAVE_TIME_WEIGHT = "leaveTimeWeight";
        public static final String COLUMN_NAME_GYM_MEMBERSHIP_ALLOWANCE_WEIGHT = "gymMembershipAllowanceWeight";
    }

}
