package org.svj;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TranscriptParser {
    public static void main(String[] args) {
        try {
            String studentReportCardTxt = Files.lines(Path.of("C:\\Users\\svjra\\Documents\\git\\Springboot\\StreamsLambda\\src\\org\\svj\\resources\\StudentReportCard.txt"))
                    .collect(Collectors.joining("\n"));
//            studentReportCardTxt= """
//                    Student Number:	1234598872			Grade:		11
//                    Birthdate:		01/02/2000			Gender:	M
//                    State ID:		8923827123
//
//                    Cumulative GPA (Weighted)		3.82
//                    Cumulative GPA (Unweighted)	3.46
//                    """;

            String regex = """
                           Student\\sNumber:\\s+(?<studentNumber>\\d{10}).* # get student number
                           Grade:\\s+(?<grade>\\d{1,2}).* # get student grade 
                           Birthdate:\\s+(?<birthMonth>\\d{2})/(?<birthDay>\\d{2})/(?<birthYear>\\d{4}).* # get student B'date
                           Gender:\\s(?<gender>\\w+)\\b.*  # get gender of student
                           State\\sID:\\s+(?<stateId>\\d+)\\b.* # get stateID	
                           Weighted\\)\\s+(?<weightedGPA>[\\d\\.]+)\\b.*
                           Unweighted\\)\\s+(?<unweightedGPA>[\\d\\.]+)\\b.*  # *? mentions to regex not to be greedy, and starts scanning from beginning of string, rather than backtrack after taking all possible characters
                           """;
            Pattern cardPattern= Pattern.compile(regex, Pattern.DOTALL | Pattern.COMMENTS);
            Matcher cardMatcher= cardPattern.matcher(studentReportCardTxt);
            if(cardMatcher.matches()){
                System.out.format("Student Number: %s\n", cardMatcher.group("studentNumber"));
                System.out.format("Student Grade: %s\n", cardMatcher.group("grade"));
                System.out.format("Student Birthday: %s/%s/%s\n", cardMatcher.group("birthMonth"), cardMatcher.group("birthDay"), cardMatcher.group("birthYear"));
                System.out.format("Student Gender: %s\n", cardMatcher.group("gender"));
                System.out.format("State ID: %s\n", cardMatcher.group("stateId"));
                System.out.format("weightedGPA: %s\n", cardMatcher.group("weightedGPA"));
                System.out.format("unweightedGPA: %s\n", cardMatcher.group("unweightedGPA"));
            }

//            System.out.println(studentReportCardTxt);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
