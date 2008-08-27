package at.yomi.collection;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class EnumerationTest {
    private static final Integer SIZE = 10000;

    @Test
    public void testEnumeration() {
        final List<Integer> l = new ArrayList<Integer>(SIZE);
        final Enumeration e = new Enumeration(0, SIZE);

        for (int i = 0; i < SIZE; i++)
            l.add(i);
        assertArrayEquals(l.toArray(), e.toArray());
    }
}
