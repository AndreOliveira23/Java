package lambdas;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

//Reference: https://www.w3resource.com/java-exercises/lambda/index.php

public class Exercises {

    public static IntBinaryOperator sumTwoIntegers = Math::addExact;
    public static Predicate<String> checkEmptyString = String::isEmpty;
    public static Function<String, String> upperCase = String::toUpperCase;
    public static Function<String, String> lowerCase = String::toLowerCase;
    public static Function<List<Integer>,List<Integer>> filterEven = (list) -> list.stream().filter(l -> l % 2 == 0).collect(Collectors.toList());
    public static Function<List<Integer>,List<Integer>> filterOdd = (list) -> list.stream().filter(l -> l % 2 != 0).collect(Collectors.toList());
    public static Function<List<String>, List<String>> sortStringList = (list) -> list.stream().sorted(String::compareToIgnoreCase).toList();
    public static Function<List<Double>,Double> findAvg = (list) -> {
        final double[] sum = {0};
        list.forEach(num -> sum[0] +=num);
        return sum[0] /list.size();
    };

    public static Function<List<Integer>,List<Integer>> removeDuplicates = (list) -> list.stream().distinct().collect(Collectors.toList());

    //For recursive lambdas, the lambda must invoke "MyClass.lambda.method"
    public static UnaryOperator<Integer> factorial = (n) -> n == 0 ? 1 : n * Exercises.factorial.apply(n-1);

    public static Predicate<Integer> isPrime = (n) -> {
        if(n < 1) return false;
        if(n == 2) return true;
        if(n%2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return true;
    };

    public static BiFunction<String, String, String> concat = String::concat;
    public static Function<List<Integer>,Integer> findMax = (list) -> { list = list.stream().sorted().toList(); return list.getLast();};
    public static Function<List<Integer>,Integer> findMin = (list) -> { list = list.stream().sorted().toList(); return list.getFirst();};

};
