package org.svj.employees;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee implements Chef{
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

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public int getYearsOfExp() {
        return yearsOfExp;
    }

    public void setYearsOfExp(int yearsOfExp) {
        this.yearsOfExp = yearsOfExp;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Programmer)) return false;
        if (!super.equals(o)) return false;
        Programmer that = (Programmer) o;
        return getIq() == that.getIq();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIq());
    }
}
