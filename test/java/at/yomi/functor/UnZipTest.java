package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

import at.yomi.pair.Pair;

public class UnZipTest extends BaseTest {
	public UnZip<Integer,Integer> getUnZipper() {
		return new UnZip<Integer,Integer>();
	}

	@Test
	public void testUnZip() {
		final Pair<List<Integer>,List<Integer>> pair = getUnZipper().apply(
				new Zip<Integer,Integer>().apply(data, data));

		assertArrayEquals(pair.first.toArray(), data.toArray());
		assertArrayEquals(pair.second.toArray(), data.toArray());
	}
}
