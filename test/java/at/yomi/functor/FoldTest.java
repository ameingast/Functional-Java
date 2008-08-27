package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FoldTest extends BaseTest {
    protected Fold<Integer, Integer> getSumFold() {
        return new Fold<Integer, Integer>(Utils.sumFunctor);
    }

    protected Fold<String, String> getStringReverseFold() {
        return new Fold<String, String>(Utils.reverseStringFoldFunctor);
    }

    @Test
    public void testAddNumbers() {
        final Integer k = getSumFold().apply(data, 0);
        Integer r = 0;

        for (final Integer i : data)
            r += i;
        assertEquals(r, k);
    }

    @Test
    public void testStringReverse() {
        String buf = "";

        for (final String s : stringData)
            buf += s;
        assertEquals(Utils.reverse(buf), getStringReverseFold().apply(
            stringData, ""));
    }

    @Test
    public void collectLists() {
        final List<Integer> actual = new ArrayList<Integer>();
        final List<List<?>> nestedData = new ArrayList<List<?>>();

        nestedData.add(data);
        actual.addAll(data);
        nestedData.add(data);
        actual.addAll(data);
        nestedData.add(data);
        actual.addAll(data);
        nestedData.add(data);
        actual.addAll(data);

        assertArrayEquals(actual.toArray(), Utils.collectFold.apply(nestedData,
            new ArrayList<Integer>()).toArray());
    }
}