package com.coding_test.backend;

public class EmployeeUpdateRequest {
    private float vacationTime;
    private int workDays;

    public float getVacationTime() {
        return this.vacationTime;
    }

    public void setVacationTime(float vactaionTime) {
        this.vacationTime = vactaionTime;
    }

    public int getWorkDays() {
        return this.workDays;
    }

    public void setWorkDays(int workDays) {
        this.workDays = workDays;
    }

}
