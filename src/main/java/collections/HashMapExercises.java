package collections;

import java.util.*;

//Reference: https://www.w3resource.com/java-exercises/collection/hash-map.php

    /* After implementing the solution for exercise 3, I saw the solution, and there, the types of the map were hard-coded,
    so just for purposes of learning, I'll start to using generics whenever is possible, or, at least, choose the types as well */

public class HashMapExercises {

    public static <K,V> void  associate(Map<K, V> map, K key, V value ){
        map.put(key,value);
    }

    public static <K,V> int getEntries(Map<K, V> map){
        return map.entrySet().size();
    }

    public static <K, V> void copyMap(Map<K, V> source, Map<K, V> destination) {
        destination.putAll(source);
    }

    public static <K,V> void clearMap(Map<K,V> map){
        map.clear();
    }

    public static <K,V> boolean checkIfMapIsEmpty(Map<K,V> map){
        return map.isEmpty();
    }

    public static <K,V> HashMap<K,V> getHashmapCopy(HashMap<K,V> map){
        return new HashMap<>(map);
    }

    public static <K,V> boolean checkIfMapContainKey(Map<K,V> map, K key){
        return map.containsKey(key);
    }

    public static <K,V> boolean checkIfMapContainValue(Map<K,V> map, V value){
        return map.containsValue(value);
    }

    public static <K,V> Set getMappings(Map<K,V> map) { return map.entrySet(); }

    public static <K,V> V getValue(Map<K, V> map, K key) { return map.get(key);}

    public static <K,V> Set<K> getKeys(Map<K,V> map) { return map.keySet(); }

    public static <K,V> Collection<V> getValues(Map<K,V> map) { return map.values(); }
}
