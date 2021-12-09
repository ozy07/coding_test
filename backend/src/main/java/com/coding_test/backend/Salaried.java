package com.coding_test.backend;

import org.apache.commons.math3.util.Precision;

public class Salaried implements Employee {

    private int id;
    private String name;
    private float vacationDaysAvailable;
    private int workDaysPerYear;
    private EmployeeType employeeType;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getVacationDaysAvailable() {
        return this.vacationDaysAvailable;
    }

    public void setVacationDaysAvailable(float vacationDaysAvailable) {
        this.vacationDaysAvailable = vacationDaysAvailable;
    }

    public int getWorkDaysPerYear() {
        return this.workDaysPerYear;
    }

    public void setWorkDaysPerYear(int workDaysPerYear) {
        this.workDaysPerYear = workDaysPerYear;
    }

    public EmployeeType getEmployeeType() {
        return this.employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Salaried(int id, String name, EmployeeType employeeType) {
        this.id = id;
        this.name = name;
        this.employeeType = employeeType;
        workDaysPerYear = 260;
        vacationDaysAvailable = (float) 0.0;
    }

    @Override
    public boolean Work(int daysWorked) {
        int remainingWorkDays = workDaysPerYear - daysWorked;
        if (remainingWorkDays < 0) {
            return false; // No more days left to work OR cannot work those days since they will go over
                          // the 260 limit
        } else {
            updateEarnedVacation(daysWorked);
            workDaysPerYear = remainingWorkDays;
            return true;
        }
    }

    private void updateEarnedVacation(int daysWorked) {
        if (EmployeeType.MANAGER == employeeType) {
            float workDaysForOneVactionDay = (float) 8.66666667; // Managers get 30 days i.e. 260/30
            vacationDaysAvailable += Precision.round(daysWorked / workDaysForOneVactionDay, 1); // single float
            // 8.66666667 work days = 1 vacation day
            // daysWorked = vacationDaysEarned
        } else {
            float workDaysForOneVactionDay = (float) 17.3333333; // General Salaried workers get 15 days i.e. 260 / 15
            vacationDaysAvailable += Precision.round(daysWorked / workDaysForOneVactionDay, 1); // single float
        }
    }

    @Override
    public boolean TakeVacation(float vactaionTime) {
        float vacationLeft = vacationDaysAvailable - vactaionTime;
        if (vacationDaysAvailable < 0 || vacationDaysAvailable == 0 || vacationLeft < 0) {
            return false; // No Vacation Days to take OR trying to take more than is available
        } else {
            vacationDaysAvailable = vacationLeft; // Vacation days has been taken so update available days
            return true;
        }
    }

}
