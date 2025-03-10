package streams;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Reference: https://www.w3resource.com/java-exercises/stream/index.php
 */
public class Exercises {

    private static Double average(List<Integer> integers) {
        return integers.stream().collect(Collectors.averagingInt(Integer::intValue));
    }

    //Exercise 2
    private static List<String> toUpperCase(List<String> strings) {
        return strings.stream().map(String::toUpperCase).toList();
    }


    private static List<String> toLowerCase(List<String> strings) {
        return strings.stream().map(String::toLowerCase).toList();
    }

    //Exercise 3
    private static Map<String, Integer> sumOfEvensAndOdds(List<Integer> integers) {
        Map<String, Integer> result = new HashMap<>();
        List<Integer> even = integers.stream().filter(i -> i % 2 == 0).toList();
        List<Integer> odd = integers.stream().filter(i -> i % 2 != 0).toList();
        result.put("Sum of all even ", even.stream().mapToInt(Integer::intValue).sum());
        result.put("Sum of all odd ", odd.stream().mapToInt(Integer::intValue).sum());
        return result;
    }

    //Exercise 4
    @SuppressWarnings("unchecked")
    private static <T> T removeDuplicates(List<T> list){
        return (T) list.stream().distinct().toList();
    }

    private static int countStringsStartingWith(List<String> list, char letter){
        return (int) list.stream().filter(word -> word.startsWith(String.valueOf(letter))).count();
    }

    /* Write a Java program to sort a list of strings in alphabetical order, ascending and descending using
    streams. */
    private static List<String> sortStringsAscending(List<String> list){
        return list.stream().sorted().toList(); //Default implementation uses natural order
    }


    private static List<String> sortStringsDescending(List<String> list){
      return list.stream().sorted(Collections.reverseOrder()).toList();
    }

    /*Write a Java program to find the maximum and minimum values in a list of integers using streams. */
    private static Map<String, Integer> findMaxAndMin(List<Integer> list){
        Map<String , Integer> result = new HashMap<>();
        int min = list.stream().sorted().toList().getFirst();
        int max = list.stream().sorted().toList().getLast();
        result.put("Max ",max);
        result.put("Min ",min);
        return result;
    }

    /*Write a Java program to find the second smallest and largest elements in a list of integers using streams. */
    private static Map<String, Integer> findSecondSmallestAndLargest(List<Integer> list){
        Map<String , Integer> result = new HashMap<>();
        int secondSmallest = list.stream().sorted().toList().get(1);
        int max = list.stream().sorted().toList().getLast();
        result.put("Max ",max);
        result.put("Second Smallest ",secondSmallest);
        return result;
    }


    public static void main(String[] args) {
        System.out.println(findSecondSmallestAndLargest(List.of(50,1,2,3,123,16425,123,2,3,5,-10,123,551,90)));

        List<String> strings = List.of("one","two","three","four");

        var map = strings.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        map.forEach((key, value) -> System.out.println(key + " :: " + value));
    }


}
