package org.svj.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Employee{
    private int orgSize= 0;
    private int directReport= 0;

    private final String managerRegex="\\w+=(?<orgSize>\\w+),\\w+=(?<dr>\\w+)";
    private final Pattern managerPattern= Pattern.compile(managerRegex);

    public Manager(String personText) {
        super(personText);
        Matcher managerMatch= managerPattern.matcher(personMatcher.group("details"));
        if(managerMatch.find()){
            orgSize = Integer.parseInt(managerMatch.group("orgSize"));
            directReport = Integer.parseInt(managerMatch.group("dr"));
        }
    }
    public int getSalary(){
        return 3500+ orgSize* directReport;
    }
}
