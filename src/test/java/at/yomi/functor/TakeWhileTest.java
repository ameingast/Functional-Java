package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TakeWhileTest extends BaseTest {
    @Test
    public void testTakeWhileEven() {
        final List<Integer> result = new ArrayList<Integer>();

        for (final Integer i : data)
            if (i % 2 == 0)
                result.add(i);
            else
                break;

        assertArrayEquals(result.toArray(), Utils.takeWhileEven.apply(data)
            .toArray());
    }

    @Test
    public void testSumEvens() {
        Integer result = 0;

        for (final Integer i : data)
            if (i % 2 == 0)
                result += i;
            else
                break;

        assertEquals(result, Utils.sumFold.apply(Utils.takeWhileEven
            .apply(data), 0));
    }
}
