package org.svj.employees;

import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;

public class Employees {

    private static Set<IEmployee> employees;
    private static Map<String, Integer> empMap;

    public static void main(String[] args) {
        String peopleText= """
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=4000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=5000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=6000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=7000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=8000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmerzzz, {locpd=10000,yoe=10,iq=140}
                Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
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
        employees = new TreeSet<>(Comparator.comparingInt(IEmployee::getSalary));
        empMap = new LinkedHashMap<>();
        while (peopleMatcher.find()) {
            employee= Employee.createEmployee(peopleMatcher.group());
            Employee emp= (Employee) employee;
            employees.add(employee);
            empMap.putIfAbsent(emp.firstName, emp.getSalary());
        }

//        new ArrayList<>(employees).get(0);

        for(IEmployee worker: employees){
            System.out.println(worker.toString());
            totalSalaries+= worker.getSalary();
        }

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        System.out.format("Total payout is %s", currencyFormatter.format(totalSalaries));

        System.out.println("\nEmployees size is "+ employees.size());

        System.out.println(empMap.entrySet());

//        for(Map.Entry<String, Integer> entry: empMap.entrySet()){
//            System.out.printf("Key=  %s, Value= %s\n", entry.getKey(), entry.getValue());
//        }

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

    public int getSalary(String firstName) {
        return empMap.getOrDefault(firstName, -1);
    }
}
