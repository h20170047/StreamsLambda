package org.svj;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPractice {
    public static void main(String[] args) {
//        System.out.println("Cat".toLowerCase().matches("cat"));
//        System.out.println("Cat".matches("[cC]at"));
//        System.out.println("Cat".matches("[a-fA-F]at"));
//        System.out.println("Mat".matches("[^a-z]at"));
//        System.out.println("0at".matches("\\w\\w\\w")); // word, number, _ matched \w
//        System.out.println("0".matches("\\d"));
//        System.out.println("331-333-7652".matches("\\d{3}-\\d{3}-\\d{4}"));
//        System.out.println("331.333.7652".matches("\\d{3}[-.]\\d{3}[-.]\\d{4}"));
//        System.out.println("331 333 7652".matches("\\d{3}[-.\\s]\\d{3}[-.\\s]\\d{4}"));
//        System.out.println("331    333 7652".matches("\\d{3}[-.\\s]+\\d{3}[-.\\s]+\\d{4}"));
//        System.out.println("3313337652".matches("\\d{3}[-.\\s]*\\d{3}[-.\\s]*\\d{4}"));
//        System.out.println("331333 7652".matches("\\d{3}[-.\\s]?\\d{3}[-.\\s]?\\d{4}")); //* any chars, + one or more, ? 1 or none
//        System.out.println("331333 652".matches("\\d{3}[-.\\s]?\\d{3}[-.\\s]?\\d{3,4}")); // low and high limits for set of chars using {}, mentioning only either of those limits keeps the other one open to Inf
//        System.out.println("331 333 652".matches("(\\d{3}[-.\\s]?){2}\\d{3,4}"));
//        System.out.println("333 652".matches("(\\d{3}[-.\\s]?){1,2}\\d{3,4}"));
        String regex = """
        # This is my regex to parse the parts of a phone number
        (?:(?<countryCode>\\d{1,2})[-.\\s])? # gets country code
        (?:([(]?(?<areaCode>\\d{3})[)]?)[-.\\s]?) # gets area code
        (?:(?<exchangeCode>\\d{3})[-.\\s]?) # gets exchange code
        (?<lineNumber>\\d{3,4}) # gets line number
        """;
        String samplePhone = "1.(232).333-2365";

//        System.out.println(samplePhone.matches(regex));  // std for US

        Pattern phoneNumberPattern= Pattern.compile(regex, Pattern.COMMENTS); // with Pattern.COMMENTS on, we need to represent space with \\s
        Matcher phoneNumbermatcher= phoneNumberPattern.matcher(samplePhone);

        if(phoneNumbermatcher.matches()){
            System.out.format("Country code: %s\n",phoneNumbermatcher.group("countryCode"));
            System.out.format("Area code: %s\n",phoneNumbermatcher.group("areaCode"));
            System.out.format("Exchange: %s\n",phoneNumbermatcher.group("exchangeCode"));
            System.out.format("Line number: %s\n", phoneNumbermatcher.group("lineNumber"));
        }
    }
}
