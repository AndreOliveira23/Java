package lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//Reference: https://www.w3resource.com/java-exercises/lambda/index.php

public class Exercises {

    public static IntBinaryOperator sumTwoIntegers = Math::addExact;
    public static Predicate<String> checkEmptyString = String::isEmpty;

    //Exercise 3
    public static Function<String, String> upperCase = String::toUpperCase;
    public static Function<String, String> lowerCase = String::toLowerCase;

    //Exercise 4
    public static Function<List<Integer>, List<Integer>> filterEven = (list) -> list.stream().filter(l -> l % 2 == 0).collect(Collectors.toList());
    public static Function<List<Integer>, List<Integer>> filterOdd = (list) -> list.stream().filter(l -> l % 2 != 0).collect(Collectors.toList());

    public static Function<List<String>, List<String>> sortStringList = (list) -> list.stream().sorted(String::compareToIgnoreCase).toList();

    public static Function<List<Double>, Double> findAvg = (list) -> {
        final double[] sum = {0};
        list.forEach(num -> sum[0] += num);
        return sum[0] / list.size();
    };

    public static Function<List<Integer>, List<Integer>> removeDuplicates = (list) -> list.stream().distinct().collect(Collectors.toList());

    //Exercise 8 | For recursive lambdas, the lambda must invoke "MyClass.lambda.method"
    public static UnaryOperator<Integer> factorial = (n) -> n == 0 ? 1 : n * Exercises.factorial.apply(n - 1);


    public static Predicate<Integer> isPrime = (n) -> {
        if (n < 1) return false;
        if (n == 2) return true;
        return IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(i -> n % i == 0);
    };


    public static BiFunction<String, String, String> concat = String::concat;

    //Exercise 11
    public static Function<List<Integer>, Integer> findMax = (list) -> { list = list.stream().sorted().toList();return list.getLast(); };
    public static Function<List<Integer>, Integer> findMin = (list) -> { list = list.stream().sorted().toList(); return list.getFirst(); };

    //Exercise 12. Part two is equivalent to sum()
    public static Function<List<Integer>, Integer> multiplyAll = (list) -> {
        final int[] mult = {1,0};
        list.forEach(num -> mult[0] *= num);
        return mult[0];
    };

    public static Function<List<Integer>, Integer> sumAll = (list) -> {
        final int[] sum = {0};
        list.forEach(num -> sum[0] += num);
        return sum[0];
    };


    public static Function<String, Integer> countWords = (string) -> Arrays.stream(string.split(" ")).toList().size();


    //Exercise 15
    public static Function<List<Integer>, Integer> sumOfSquareOfAllOds = (list) -> {
        list = filterOdd.apply(list);
        final int[] sum = {0};
        list.forEach(num -> sum[0] += (int) Math.pow(num,2));
        return sum[0];
    };

    public static Function<List<Integer>, Integer> sumOfSquareOfAllEven = (list) -> {
        list = filterEven.apply(list);
        final int[] sum = {0};
        list.forEach(num -> sum[0] += (int) Math.pow(num,2));
        return sum[0];
    };

    public static BiPredicate<List<String>, String> checkIfListContainWord = List::contains;
}
