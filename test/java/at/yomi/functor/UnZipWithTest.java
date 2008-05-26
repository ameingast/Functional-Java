package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import at.yomi.pair.Pair;

public class UnZipWithTest extends BaseTest {
	protected UnZipWith<Integer,Integer,Integer> getPairAdder() {
		return new UnZipWith<Integer,Integer,Integer>(Utils.safePairAddFunctor);
	}

	@Test
	public void testAddPairElements() {
		final List<Pair<Integer,Integer>> zs = new Zip<Integer,Integer>().apply(data, data);
		final List<Integer> sum = new ArrayList<Integer>(data.size());

		for (final Pair<Integer,Integer> pair : zs)
			sum.add(Utils.safeSum(pair.first, pair.second));
		assertArrayEquals(sum.toArray(), getPairAdder().apply(zs).toArray());
	}
}
