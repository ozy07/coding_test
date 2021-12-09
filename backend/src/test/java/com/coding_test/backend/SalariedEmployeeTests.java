package com.coding_test.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SalariedEmployeeTests {

    @Test
    public void check() {
        Salaried managerJason = new Salaried(2, "Jason", EmployeeType.MANAGER);
        Salaried generalSophie = new Salaried(3, "Sophie", EmployeeType.GENERAL);

        managerJason.Work(260);
        assertEquals(30.00, managerJason.getVacationDaysAvailable());
        assertEquals(0, managerJason.getWorkDaysPerYear());

        generalSophie.Work(260);
        assertEquals(15.00, generalSophie.getVacationDaysAvailable());
        assertEquals(0, generalSophie.getWorkDaysPerYear());

        managerJason.TakeVacation((float) 20.50);
        assertEquals(9.50, managerJason.getVacationDaysAvailable());
        managerJason.TakeVacation(10);
        assertEquals(9.50, managerJason.getVacationDaysAvailable());

        generalSophie.TakeVacation((float) 20.50);
        assertEquals(15, generalSophie.getVacationDaysAvailable());
        generalSophie.TakeVacation(10);
        assertEquals(5.00, generalSophie.getVacationDaysAvailable());
    }
}
