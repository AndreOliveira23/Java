import collections.HashMapExercises;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static collections.HashMapExercises.copyGenericMap;
import static collections.HashMapExercises.copyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashMapExercisesTest {

    @Test
    public void copyMapTest(){
        Map<Object, Object> map = Map.ofEntries(
                Map.entry(1,"1"),
                Map.entry(2,"2"),
                Map.entry(3,"3"),
                Map.entry(4,"4"),
                Map.entry(5,"5")
        );

        Map<Object,Object> map2 = new HashMap<>();
        Map<Object,Object> map3 = new HashMap<>();

        copyMap.accept(map,map2);//Testing implementation 1
        copyGenericMap(map,map3);//Testing implementation 2

       assertEquals(map,map2);
       assertEquals(map,map3);
    }
}
