package com.coding_test.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HourlyEmployeeTests {

    @Test
    public void check() {
        Hourly hourlyMark = new Hourly(1, "Mark");

        hourlyMark.Work(260);
        assertEquals(10.00, hourlyMark.getVacationDaysAvailable());
        assertEquals(0, hourlyMark.getWorkDaysPerYear());
    }
}
