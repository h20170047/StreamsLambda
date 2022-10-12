package org.svj.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee{
    private int linesOfCode= 0;
    private int yearsOfExp= 0;
    private int iq= 0;

    private final String programmerRegex="\\w+=(?<locpd>\\w+),\\w+=(?<yoe>\\w+),\\w+=(?<iq>\\w+)";
    private final Pattern coderPattern= Pattern.compile(programmerRegex);

    public Programmer(String personText) {
        super(personText);
        Matcher coderMatch= coderPattern.matcher(personMatcher.group("details"));
        if(coderMatch.find()){
            linesOfCode = Integer.parseInt(coderMatch.group("locpd"));
            yearsOfExp = Integer.parseInt(coderMatch.group("yoe"));
            iq = Integer.parseInt(coderMatch.group("iq"));
        }
    }
    public int getSalary(){
        return 3000+ linesOfCode* yearsOfExp* iq;
    }
}
