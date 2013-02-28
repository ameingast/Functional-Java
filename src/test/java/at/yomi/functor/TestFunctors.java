package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import at.yomi.utils.Functors;

public class TestFunctors extends BaseTest {
    @Test
    public void testQSort() {
        final List<Integer> r = Functors.qSort.apply(data);
        Collections.sort(data);
        assertArrayEquals(data.toArray(), r.toArray());
    }
}
