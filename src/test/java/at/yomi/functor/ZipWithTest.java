package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ZipWithTest extends BaseTest {
    @Test
    public void testAddNumbersInLists() {
        assertArrayEquals(Utils.iterateZipSum(smallData, smallData).toArray(),
            getZipWith().apply(smallData, smallData).toArray());
    }

    protected ZipWith<Integer, Integer, Integer> getZipWith() {
        return new ZipWith<Integer, Integer, Integer>(Utils.safeSumFunctor);
    }
}
