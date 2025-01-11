package lambdas;
/*Lambdas are implementations of abstract methods of functional interfaces.

>A functional interface is an interface that contains only one abstract method

Reference: https://dev.java/learn/lambdas/
*/

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {

    //Example: Implementing a lambda for the "Predicate" functional interface
    //Predicate = boolean-valued function. Takes an argument and returns true or false;

    /*This lambda defines a Predicate that operates in on a String, receiving a string and
    checks if it's length is equals 3. (static just for calling inside main)*/
    static Predicate<String> predicate =  s -> s.length() == 3;


    //Example 2: Implementing a lambda for the "Consumer" functional interface
    //Consumer = Operation thar receives an object and returns nothing

    //This lambda defines a consumer that prints the given string
    static Consumer<String> consumer = System.out::println;

    //Example 3: Implementing a lambda for the "Running" functional interface
    //Runnable = core interface provided for representing multithreaded tasks
    /*
    The most common use case of the Runnable interface is when we want only to override the run method.
    When a thread is started by the object of any class which is implementing Runnable,
    then it invokes the run method in the separately executing thread.
     */

    static Runnable runnable = () -> System.out.println("Runnable called!");

    public static void main(String[] args) {

        //To call a predicate with lambda, just use the test() method.
        List<String> list = List.of("a","ab","aba","ab ab","ba");
        list.forEach(string -> System.out.println(predicate.test(string)));

        //To call a consumer with lambda, use the accept() method
        list.forEach(string  -> consumer.accept(string));

        //To call a runnable, use the run() method.
        runnable.run();

    }
}
