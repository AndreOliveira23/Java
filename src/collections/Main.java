package collections;

import java.util.*;
import java.util.function.Predicate;

public class Main {
    //The Collections Framework is a set of interfaces that models different way of storing data in different types of containers.

    //

    /* THE COLLECTION HIERARCHY
    |->Iterable -> Iterable: Super interface of Collection. Implement Iterable = being able to iterate over. Not considered part of the Collections Framework
          |
          |
         Collection
               |
               |->List -> Have index for added elements
               |   |----->ArrayList
               |   |
               |   |----->LinkedList = doubly linked list implementation
               |
               |->Set -> Does not have index(or any particular order), and does not have duplicates
               |    |
               |    |----->SortedSet =
               |    |          |
               |    |          |->TreeSet = implementation of SortedSet (interface that extends Set)
               |    |
               |    |----->HashSet
               |    |
               |    |----->LinkedHashSet
               |
               |
               |->Queue ->
                   |
                   |->PriorityQueue
                   |
                   |->Deque
                       |
                       |->ArrayDeque

    THE MAP HIERARCHY
    |->Map -> Stores key-value pairs.
        |        From a technical POV, as the Collection Interface is the root interface for collections and the Map interface
        |        is the root interface for maps, there's no direct relationship between interfaces related to collections and related to map
        |        but, the java tutorial at dev.java/learn brings them altogether probably due to the fact that they all serve to store and access data
        |
        |
        |->HashMap
        |
        |
        |->LinkedHashMap
        |
        |
        |->SortedMap
               |
               |
               |->TreeMap
    */
    public static void main(String[] args) {
        Predicate<String> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNullOrEmpty = isNull.or(isEmpty);

        List<String> strings = new ArrayList<>();
        strings.add(null);
        strings.add("");
        strings.add("one");
        strings.add("two");
        strings.add("");
        strings.add("three");
        strings.add(null);

        System.out.println("strings = " + strings);
        strings.removeIf(isNullOrEmpty);
        System.out.println("filtered strings = " + strings);

        Map<UUID,String> map = new HashMap<>();

        map.put(UUID.randomUUID(),"TEST");
        map.put(UUID.randomUUID(),"TEST2");
        System.out.println(map);


    }
}
