package collections;

//Reference: https://www.w3resource.com/java-exercises/collection/array-list.php

/*
Even though the solution may involve using the Main method to execute the methods, I'll try
to generalize and code in a way that the methods will be invoked to complete the exercise
 */

import java.util.*;
import java.util.function.Consumer;

public class ArrayListExercises {
    public static void createAddAndPrint(List<String> colorsToAdd){
        List<String> colors = new ArrayList<>(colorsToAdd);
        colors.forEach(System.out::println);
    }

    public static <T> void iterate(ArrayList<T> arrayList){
        arrayList.forEach(System.out::println);
    }

    public static <T> T getElement(ArrayList<T> list, int index){
        return list.get(index);
    }

    public static <T> void UpdateElement(ArrayList<T> list, int index, T newElement){
        list.set(index,newElement);
    }

    public static <T> void removeThirdElement(ArrayList<T> list){
        list.remove(2);
    }

    public static <T> void searchElement(ArrayList<T> list, T elementToFound){
        list.removeIf(e -> e != elementToFound);
        System.out.println(list);
    }

    //Still don't know how to use generics for the parameter here
    public static void sort(ArrayList<String> list){
        Collections.sort(list);
    }

    public static <T> void copy(ArrayList<T> list1, ArrayList<T> list2){
        list2.addAll(list1);
    }

    public static Consumer<ArrayList> shuffle = Collections::shuffle;

    public static Consumer<ArrayList> reverse = Collections::reverse;

    public static <T> ArrayList<T> sublist(ArrayList<T> list, int from, int to){
        return new ArrayList<>(list.subList(from,to));
    }

    public static <T> void swap(ArrayList<T> list, int indexOfFirstElement, int indexOfSecondElement){
        Collections.swap(list,indexOfFirstElement,indexOfSecondElement);
    }

    public static <T> void join(ArrayList<T> list1, ArrayList<T> list2){
        list1.addAll(list2);
    }




    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        set.add(2);set.add(3);set.add(1);set.add(6);set.add(9);set.add(20);set.add(12);set.add(252);

        set.removeIf(e -> e > 7);

        System.out.println(0x3ee5);
    }
}
