package com.blogspot.pbetkier.collections;

import java.util.*;

public class BasicCollectionsCreator {

    public List<String> createPrepopulatedList() {
        List<String> created = new ArrayList<>();
        created.add("alpha");
        created.add("beta");
        created.add("gamma");
        return created;
//        Lists.newArrayList("alpha", "beta", "gamma");
    }

    public Set<Integer> createPrepopulatedSet() {
        return new HashSet<>(Arrays.asList(1, 3, 5));
//        Sets.newHashSet(1, 3, 5)
    }

}
