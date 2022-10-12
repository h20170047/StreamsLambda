package org.svj;

public class RegexPractice2 {
    public static void main(String[] args) {
//        System.out.println("doggy".matches(".{3}")); // ^ represents negation in [] and start of character when outside []. $ represents end of characters
        System.out.println("---".matches("\\W\\W\\W")); // \\W matches non word characters, and \\D matches non digit character, \\S non space character

    }
}
