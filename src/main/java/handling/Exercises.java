package handling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//https://www.w3resource.com/java-exercises/exception/index.php
public class Exercises {

    private static final Logger log = LoggerFactory.getLogger(Exercises.class);

    public static void exceptionIfNumberIsOdd(int n){
        try{
            if((n & 1) == 1) throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            log.info("IllegalArgumentException caught!! The number is odd!" );
            throw e;
        }
    }

    //Using IOException due to the usage of "readLine()". FileNotFound is a subclass of IOException, but it's not thrown by readLine();
    public static void exceptionIfFileNotFound(Path path) throws IOException {
        try{
            BufferedReader reader = Files.newBufferedReader(path);
            String line = reader.readLine();
            while (line != null){
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e){
            log.info("IOException caught!!");
            throw e;
        }
    }

    public static void exceptionIfNumberIsPositive(Path path) throws IOException {
        try{
            BufferedReader reader = Files.newBufferedReader(path);
            String line = reader.readLine();
            while (line != null){
                if(Integer.parseInt(line) > 0) throw new IOException();
                line = reader.readLine();
            }
        } catch (IOException e){
            log.info("IOException caught!!");
            throw e;
        }
    }

    public static void exceptionIfNumberIsDuplicate(List<Integer> list){
        List<Integer> processed = new ArrayList<>();
        for (Integer number: list){
            if(processed.contains(number)) {
                throw new IllegalStateException("A",null);
            } else {
                processed.add(number);
            }
        }
    }

    public static void exceptionIfStringDoesNotContainVowels(String string){
        Pattern pattern = Pattern.compile("[aeiou]");
        Matcher matcher = pattern.matcher(string);
        if(!matcher.find()) throw new MatchException("No vowels were found in this string!!",null);
    }
}