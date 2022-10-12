package org.svj;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PeopleMatching {
    public static void main(String[] args) {
        String people= """
                Flinstone, Fred, 1/1/1900
                Rubble, Barney, 2/2/1905
                Flinstone, Wilma, 3/3/1910
                Rubble, Betty, 4/4/1915
                """;
        String regex="(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4})\\n";
        Pattern pattern=Pattern.compile(regex, Pattern.COMMENTS);
        Matcher matcher= pattern.matcher(people);

        while (matcher.find()) {
            System.out.format("%s %s %s\n", matcher.group("firstName"), matcher.group("lastName"), matcher.group("dob"));
        }
    }
}
