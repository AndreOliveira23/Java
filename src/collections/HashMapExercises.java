package collections;


import java.util.Map;
import java.util.function.BiConsumer;

//Reference: https://www.w3resource.com/java-exercises/collection/hash-map.php
public class HashMapExercises {


    public void associate(Map<Object, Object> map, Object key, Object value ){
        map.put(key,value);
    }

    public int getEntries(Map<Object, Object> map){
        return map.entrySet().size();
    }

    /* After implementing this solution, I saw the solution, and there, the types of the map were hard-coded,
    so just for purposes of learning, I'll start to choose the types as well */

    //Implementation 1, using inlined lambdas. Advantage: Readability, elegance | Disadvantage: lacks type specificity by using 'Object' as a type (bad practice)
    public static BiConsumer< Map<Object, Object>, Map<Object, Object> > copyMap = (map, map2) -> map2.putAll(map);

    //Implementation 2, using generics. Advantage: maintain readability and also do not use Object as a type.
    public static <K, V> void copyGenericMap(Map<K, V> source, Map<K, V> destination) {
        destination.putAll(source);
    }



}
