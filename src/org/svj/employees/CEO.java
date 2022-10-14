package org.svj.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO extends Employee implements Flyer{
    private int avgStockPrice= 0;
    private Flyer pilot= new Pilot(1000, true);

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

    public int getHoursFlown() {
        return pilot.getHoursFlown();
    }

    public void fly() {
        pilot.fly();
    }

    public void setHoursFlown(int hoursFlown) {
        pilot.setHoursFlown(hoursFlown);
    }

    public boolean isIfr() {
        return pilot.isIfr();
    }

    public void setIfr(boolean ifr) {
        pilot.setIfr(ifr);
    }
}
