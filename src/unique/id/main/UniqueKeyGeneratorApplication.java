package unique.id.main;

import unique.id.alg.UniqueKey;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class UniqueKeyGeneratorApplication {
    public static void main(String[] args) {

        Set<String> uniqueKeySet = new HashSet<>();
        Map<String, Integer> duplicateMap = new HashMap<>();

        long start = System.currentTimeMillis();

        for (int i = 1; i <= 10000000; i ++) {
            String key = UniqueKey.getUniqueKey();
            if (!uniqueKeySet.add(key))
                duplicateMap.put(key, duplicateMap.getOrDefault(key, 0) + 1);
        }

        System.out.println(String.format("%.3f seconds", (System.currentTimeMillis() - start) / 1000.0));
        System.out.println(duplicateMap);

    }
}