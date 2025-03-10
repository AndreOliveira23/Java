package streams;

import java.util.List;

import lambdas.Exercises.*;

import static lambdas.Exercises.filterEven;

public class Main {
    record Car(String type, String make, String model, Integer engineCapacity) {}

    public static void main(String[] args) {
           List<Car> cars = List.of(
                   new Car("sedan","BMW","530",1998),
                   new Car("sedan","Audi","A5",1998),
                   new Car("sedan","Mercedes","E-class",2500),
                   new Car("hatchback","Skoda","Octavia",1600),
                   new Car("hatchback","Toyota","TypeR",1450)
           );


        List<String> strings = List.of("one", "two", "three", "four");

        List<Integer> l = strings.stream()
                .map(String::length)
                .filter(s-> s < 5)
                .toList();

        System.out.println(l);
    }
}
