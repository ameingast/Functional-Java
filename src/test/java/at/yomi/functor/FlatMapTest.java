package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class FlatMapTest extends BaseTest {
    @SuppressWarnings("unchecked")
    protected final List<List<Integer>> aas = new ArrayList<List<Integer>>(
        Arrays.asList(data, data, data));

    protected FlatMap<Integer, Integer> getMulTenFlatMap() {
        return new FlatMap<Integer, Integer>(Utils.mulTenFunctor);
    }

    @Test
    public void testAddTwoAndFlatten() {
        final List<Integer> es = getMulTenFlatMap().apply(aas);
        final List<Integer> results = new ArrayList<Integer>();

        for (final Collection<Integer> as : aas)
            for (final Integer i : as)
                results.add(i * 10);
        assertArrayEquals(results.toArray(), es.toArray());
    }
}
