package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import at.yomi.pair.Pair;

public class ZipTest extends BaseTest {
	private final List<Integer> as = Arrays.asList(1, 2, 3, 4, 5);

	private final List<Integer> bs = Arrays.asList(1, 2, 3, 4);

	protected Zip<Integer,Integer> getZip() {
		return new Zip<Integer,Integer>();
	}

	@Test
	public void testGenericIntegerZip() {
		assertArrayEquals(iterateZip(data, data).toArray(), getZip().apply(data, data).toArray());
	}

	@Test
	public void testFirstListLonger() {
		assertArrayEquals(iterateZip(as, bs).toArray(), getZip().apply(as, bs).toArray());
	}

	@Test
	public void testSecondListLonger() {
		assertArrayEquals(iterateZip(bs, as).toArray(), getZip().apply(bs, as).toArray());
	}

	private List<Pair<Integer,Integer>> iterateZip(final List<Integer> as, final List<Integer> bs) {
		final List<Pair<Integer,Integer>> zs = new ArrayList<Pair<Integer,Integer>>();
		int i = 0;

		for (i = 0; i < as.size(); i++)
			zs.add(new Pair<Integer,Integer>(as.get(i), i < bs.size() ? bs.get(i) : null));
		for (; i < bs.size(); i++)
			zs.add(new Pair<Integer,Integer>(i < as.size() ? as.get(i) : null, bs.get(i)));
		return zs;
	}
}
