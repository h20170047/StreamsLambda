package org.svj.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class  Employee {
    protected final DateTimeFormatter dtFormatter= DateTimeFormatter.ofPattern("M/d/yyyy");
    protected final NumberFormat moneyFormat= NumberFormat.getCurrencyInstance();
    private final static String PEOPLE_REGEX ="(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s+(?<role>\\w+),\\s+\\{(?<details>.*)}\\n";
    protected final static Pattern PEOPLE_PATTERN =Pattern.compile(PEOPLE_REGEX, Pattern.COMMENTS);
    protected final Matcher personMatcher;
    protected String lastName;
    protected String firstName;
    protected LocalDate dob;

    public Employee() {
        personMatcher= null;
        firstName="N/A"; lastName="N/A";
    }

    public static Employee createEmployee(String employeeText) {
        Matcher personMatcher = Employee.PEOPLE_PATTERN.matcher(employeeText);
        if(personMatcher.find())
            return switch (personMatcher.group("role")){
                case "Programmer" -> new Programmer(employeeText);
                case "Manager" -> new Manager(employeeText);
                case "Analyst" ->new Analyst(employeeText);
                case "CEO" -> new CEO(employeeText);
                default -> new EmptyEmployee();
            };
        return new EmptyEmployee();
    }

    public abstract int getSalary();

    protected Employee(String personText){
        personMatcher = Employee.PEOPLE_PATTERN.matcher(personText);
        if(personMatcher.find()) {
            lastName = personMatcher.group("lastName");
            firstName = personMatcher.group("firstName");
            dob = LocalDate.from(dtFormatter.parse(personMatcher.group("dob")));
        }
    }

    @Override
    public String toString() {
        return String.format("%s, %s: %s - %s", lastName, firstName, moneyFormat.format(getSalary()), moneyFormat.format(getBonus()));
    }

    public double getBonus(){
        return getSalary()*1.1;
    }

    private static final class EmptyEmployee extends Employee{
        protected EmptyEmployee() {
            super();
        }

        public int getSalary(){
            return 0;
        }

    }
}
