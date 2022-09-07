package edu.gatech.seclass.jobcompare6300.dao.utils;

import android.provider.BaseColumns;

public final class JobsContract {
    private JobsContract() {};

    public static class JobEntry implements BaseColumns {
        public static final String TABLE_NAME = "jobs";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_COST_OF_LIVING = "costOfLiving";
        public static final String COLUMN_NAME_YEARLY_SALARY = "yearlySalary";
        public static final String COLUMN_NAME_YEARLY_BONUS = "yearlyBonus";
        public static final String COLUMN_NAME_ALLOWED_WEEKLY_TELEWORK_DAYS = "allowedWeeklyTeleworkDays";
        public static final String COLUMN_NAME_LEAVE_TIME = "leaveTime";
        public static final String COLUMN_NAME_GYM_MEMBERSHIP_ALLOWANCE = "gymMembershipAllowance";
        public static final String COLUMN_NAME_IS_CURRENT_JOB = "isCurrentJob";
    }

}
