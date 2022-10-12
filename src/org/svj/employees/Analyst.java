package org.svj.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst extends Employee{
    private int projectCount= 0;

    private final String analystRegex="\\w+=(?<projectCount>\\w+)";
    private final Pattern analystPattern= Pattern.compile(analystRegex);

    public Analyst(String personText) {
        super(personText);
        Matcher analystMatch= analystPattern.matcher(personMatcher.group("details"));
        if(analystMatch.find()){
            projectCount = Integer.parseInt(analystMatch.group("projectCount"));
        }
    }
    public int getSalary(){
        return 2500+ projectCount*2;
    }
}
