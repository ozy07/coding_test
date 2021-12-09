package com.coding_test.backend;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    public List<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        loadEmployees();
    }

    private boolean updateEmployeeWorkDays(int id, int daysWorked) {
        Employee employee = findEmployee(id);
        if (employee == null) {
            return false;
        } else {
            return employee.Work(daysWorked);
        }
    }

    public String updateEmployee(EmployeeUpdateRequest request, int id) {
        int daysWorked = request.getWorkDays();
        float vacationTime = request.getVacationTime();
        Boolean updatedWork = null;
        Boolean takeVacation = null;
        String response = "Could not make any updates to employee with id:" + id + " ";

        if (daysWorked != 0) {
            updatedWork = updateEmployeeWorkDays(id, daysWorked);
            response = "";
        }

        if (vacationTime != 0.0f) {
            takeVacation = requestVacation(id, vacationTime);
            response = "";
        }

        if (updatedWork != null && updatedWork == true) {
            response += "Sucessfully updated the hours worked. ";
        } else if (updatedWork != null && updatedWork == false) {
            response += "Failed to update the hours worked, Make sure you are not working overtime. ";
        }

        if (takeVacation != null && takeVacation == true) {
            response += "Sucessfully taken the vacation earned. ";
        } else if (takeVacation != null && takeVacation == false) {
            response += "Failed to approve vacation time, Make sure you have vacation time to take. ";
        }

        return response;
    }

    private boolean requestVacation(int id, float vacationDays) {

        Employee employee = findEmployee(id);
        if (employee == null) {
            return false;
        } else {
            return employee.TakeVacation(vacationDays);
        }
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    private void loadEmployees() {
        employees.add(new Hourly(1, "Fred Quimby"));
        employees.add(new Hourly(2, "Cori Sally"));
        employees.add(new Hourly(3, "Larry Broad"));
        employees.add(new Hourly(4, "Brumford Flee"));
        employees.add(new Hourly(5, "Gulag Flee"));
        employees.add(new Hourly(6, "Operati Pat"));
        employees.add(new Hourly(7, "Shioban Grum"));
        employees.add(new Hourly(8, "Posh Dole"));
        employees.add(new Hourly(9, "Toscan Vaught"));
        employees.add(new Hourly(10, "Prosecco Dransk"));
        employees.add(new Salaried(11, "Lori Fluwep", EmployeeType.MANAGER));
        employees.add(new Salaried(12, "Mona Queen", EmployeeType.MANAGER));
        employees.add(new Salaried(13, "Darling Sokar", EmployeeType.MANAGER));
        employees.add(new Salaried(14, "Pawson Garri", EmployeeType.MANAGER));
        employees.add(new Salaried(15, "Nurtica Yam", EmployeeType.MANAGER));
        employees.add(new Salaried(16, "Macaroni Cheese", EmployeeType.MANAGER));
        employees.add(new Salaried(17, "Peter Trad", EmployeeType.MANAGER));
        employees.add(new Salaried(18, "Pan Plect", EmployeeType.MANAGER));
        employees.add(new Salaried(19, "Globe Traed", EmployeeType.MANAGER));
        employees.add(new Salaried(20, "Kendal Plesa", EmployeeType.MANAGER));
        employees.add(new Salaried(21, "Connor Wedge", EmployeeType.GENERAL));
        employees.add(new Salaried(22, "Chad Platt", EmployeeType.GENERAL));
        employees.add(new Salaried(23, "Sister Rum", EmployeeType.GENERAL));
        employees.add(new Salaried(24, "Zack Kums", EmployeeType.GENERAL));
        employees.add(new Salaried(25, "Zani Deyhot", EmployeeType.GENERAL));
        employees.add(new Salaried(26, "Greg Gerrat", EmployeeType.GENERAL));
        employees.add(new Salaried(27, "Tom Asse", EmployeeType.GENERAL));
        employees.add(new Salaried(28, "Comfort Okunu", EmployeeType.GENERAL));
        employees.add(new Salaried(29, "Glori Flail", EmployeeType.GENERAL));
        employees.add(new Salaried(30, "Debbi Hulk", EmployeeType.GENERAL));
    }

    public Employee findEmployee(int id) {
        for (Employee employee : employees) {
            if (id == employee.getId()) {
                return employee;
            }
        }
        return null;
    }
}
