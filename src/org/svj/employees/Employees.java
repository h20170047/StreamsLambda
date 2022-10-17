package org.svj.employees;

import java.text.NumberFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.function.Predicate.not;

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

//        Optional<Employee> optionalEmployee = peopleText.lines()
//                .map(Employee::createEmployee)
//                .map(e -> (Employee) e)
//                .filter(not(e -> "N/A".equals(e.getFirstName())))
////                        .map(e->e.getSalary())
////                        .distinct()
////                .forEach(System.out::println);
////                .noneMatch(e -> e.getSalary() <0);
//                .findFirst();
////        boolean salaryFilter =
////                (boolean) optionalEmployee;
////        optionalEmployee= Optional.ofNullable(null);
//        System.out.format(optionalEmployee
//                .map(Employee::getLastName)
//                .orElse("Nobody"));

//                .map(Employee::getFirstName)
//                .map(name->name.split(""))
//                .flatMap(Arrays::stream)
//                .distinct()
//                .forEach(v-> System.out.print(String.valueOf(v)));

        Predicate<String> dummyEmployee = e -> e.contains("Programmerzzz");
        Predicate<Employee> nonEmptyEmployee = not(e -> "N/A".equals(e.getLastName()));
        Predicate<Employee> over5KSalary = e -> e.getSalary() > 5000;
//        double result = peopleText.lines()
//                .map(Employee::createEmployee)
//                .map(iEmployee -> (Employee)iEmployee)
////                .filter(emptyEmployee)
////                .filter(e->e.getSalary()>5000)
//                .filter(over5KSalary.and(nonEmptyEmployee))
//                .sorted(comparing(Employee::getLastName)
//                        .thenComparing(Employee::getFirstName)
//                        .thenComparingInt(Employee::getSalary))
//                .skip(5)
//                .mapToInt(Employees:: showEmpAndGetSalary)
//                .reduce(Integer::max).orElse(-1);
//        System.out.printf("Sum of salary is %s", NumberFormat.getCurrencyInstance().format(result));
        String concatenated = Stream.of("tom")
                .reduce((a,b)->a.toUpperCase().concat("_").concat(b.toUpperCase())).orElse("Empty String");
//        TOM_JERRY_MARY_SAM
        System.out.println(concatenated);
    }

    private static int showEmpAndGetSalary(IEmployee e) {
        System.out.println(e.toString());
        return e.getSalary();
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
