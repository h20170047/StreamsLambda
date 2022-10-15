package org.svj.employees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesTest {

    @Test
    public void testNameToSalary(){
        Employees employees= new Employees();
        employees.main(new String[0]);
        int salary= employees.getSalary("Wilma");
        assertEquals(2506, salary);
    }


    @Test
    public void testBadName(){
        Employees employees= new Employees();
        employees.main(new String[0]);
        int salary= employees.getSalary("");
        assertEquals(-1, salary);
    }

}