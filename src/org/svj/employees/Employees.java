package org.svj.employees;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

public class Employees {
    public static void main(String[] args) {
        String peopleText= """
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone2, Fred2, 1/1/1900, Programmerzzzz, {locpd=1300,yoe=14,iq=100}
                Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
                Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=1630,yoe=3,iq=115}
                Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
                Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
                Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100,dr=4}
                Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200,dr=2}
                Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500,dr=8}
                Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175,dr=20}
                Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
                Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=4}
                Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=5}
                Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=6}
                Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=9}
                Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
                """;
//        String peopleRegex="(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s+(?<role>\\w+),\\s+\\{(?<details>.*)}\\n";
//        Pattern peoplePattern=Pattern.compile(peopleRegex, Pattern.COMMENTS);
        Matcher peopleMatcher= Employee.PEOPLE_PATTERN.matcher(peopleText);

//        Flyer flyer= new CEO("");
//        flyer.fly();

//        Programmer coder= new Programmer("");
//        coder.cook("Hamburger");

        int totalSalaries= 0;
        IEmployee employee= null;
        List<IEmployee> employees= new ArrayList<>(16);
        while (peopleMatcher.find()) {
            employee= Employee.createEmployee(peopleMatcher.group());
            employees.add(employee);
        }

        IEmployee myEmployee = employees.get(4);
        System.out.println(employees.contains(myEmployee));

        IEmployee employee1 = Employee.createEmployee("Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}");
        System.out.println(employees.contains(employee1));

        System.out.println(myEmployee.equals(employee1));


//        List<String> undesirables= List.of("Wilma5", "Barney4", "Fred2");
//        removeUndesirables(employees, undesirables);
//
//        IEmployee[] arrayEmps = employees.toArray(new IEmployee[0]);
//        for(IEmployee emp: arrayEmps){
//            if(emp instanceof Employee tempEmp)
//            System.out.println("From new array: "+tempEmp.toString());
//        }
//
//
//        for(IEmployee worker: employees){
//            System.out.println(worker.toString());
//            totalSalaries+= worker.getSalary();
//        }
//
//        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
//        System.out.format("Total payout is %s", currencyFormatter.format(totalSalaries));

//        peopleText.lines()
//                .map(Employee:: createEmployee)
//                .forEach(System.out::println);
    }

    private static void removeUndesirables(List<IEmployee> employees, List<String> removalNames) {
        for(Iterator<IEmployee> it = employees.iterator(); it.hasNext();){
            IEmployee worker = it.next();
            if(worker instanceof Employee tempWorker){
                if( removalNames.contains( tempWorker.firstName) ){
                    it.remove();
                }
            }
        }
    }
}
