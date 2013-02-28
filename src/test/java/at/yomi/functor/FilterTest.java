package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class FilterTest extends BaseTest {
    protected Filter<Integer> getEvenFilter() {
        return new Filter<Integer>(Utils.isEvenFunctor);
    }

    @Test
    public void testFilterEven() {
        assertArrayEquals(Utils.filterEven(data).toArray(), getEvenFilter()
            .apply(data).toArray());
    }
}