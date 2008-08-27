package at.yomi.collection;

import java.util.ArrayList;
import java.util.List;

public abstract class Helper {
    public static <A> List<List<A>> split(final List<A> as, final int n) {
        int j = 0, k = 0;
        final List<List<A>> splitList = new ArrayList<List<A>>(n);

        for (int i = 0; i < n; i++) {
            j = k;
            k += i == n - 1 ? as.size() : (as.size() / n);
            splitList.add(as.subList(j, k < as.size() ? k : as.size()));
        }
        return splitList;
    }
}
