package org.svj;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;

public class BigData {
    record person(String firstName, String lastName, BigDecimal salary, String state, char gender){}
    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
//            Map<String, String> listState =
            TreeMap<String, Map<Character, String>> result = Files.lines(Path.of("C:\\Users\\svjra\\Documents\\git\\Springboot\\StreamsLambda\\src\\org\\svj\\resources\\people.csv"))
//                    .parallel()
                    .skip(1)
                    .map(o -> o.split(","))
                    .map(s -> new person(s[2], s[4], new BigDecimal(s[25]), s[32], s[5].strip().charAt(0)))
                    .collect(
                            groupingBy(person::state, TreeMap::new,
                                    groupingBy(person::gender,
                                            collectingAndThen(
                                                    reducing(BigDecimal.ZERO, person::salary, (a, b) -> a.add(b)),
                                                    NumberFormat.getCurrencyInstance()::format))
                            ));
//                    .forEach((state, salary)-> System.out.printf("%s-> %s%n", state, salary));
                    // Map<String, Map<Character, String>>
//                    .mapToLong(person::salary)
//                    .sum();
//                    .collect(Collectors.summingLong(person::salary));
            long endTime = System.currentTimeMillis();
//            System.out.printf("$%,d.00\n",sum);
//            System.out.println(listState);
            System.out.println(result);
            System.out.println((endTime-startTime)/1000.0+" seconds");
//                    .forEach(System.out::println);
//                    .collect(Collectors.counting());
//            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
