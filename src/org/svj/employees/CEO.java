package org.svj.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO extends Employee{
    private int avgStockPrice= 0;

    private final String ceoRegex="\\w+=(?<avgStockPrice>\\w+)";
    private final Pattern ceoPattern= Pattern.compile(ceoRegex);


    public CEO(String personText) {
        super(personText);
        Matcher ceoMatch= ceoPattern.matcher(personMatcher.group("details"));
        if(ceoMatch.find()){
            avgStockPrice = Integer.parseInt(ceoMatch.group("avgStockPrice"));
        }
    }
    public int getSalary(){
        return 5000* avgStockPrice;
    }
}
