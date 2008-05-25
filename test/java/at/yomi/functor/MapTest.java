package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import at.yomi.functor.Map;

public class MapTest extends BaseTest {
	protected Map<Integer,Integer> getMulTenMapper() {
		return new Map<Integer,Integer>() {
			@Override
			public Integer map(final Integer a) {
				return 10 * a;
			}
		};
	}

	protected Map<Integer,Integer> getAddOneMapper() {
		return new Map<Integer,Integer>() {
			@Override
			public Integer map(final Integer a) {
				return a + 1;
			}
		};
	}

	@Test
	public void testAddOne() {
		final List<Integer> bs = getAddOneMapper().apply(data);

		final List<Integer> cs = new ArrayList<Integer>();
		for (final Integer i : data)
			cs.add(i + 1);
		assertArrayEquals(cs.toArray(), bs.toArray());
	}

	@Test
	public void testMulTen() {
		final List<Integer> bs = getMulTenMapper().apply(data);

		final List<Integer> cs = new ArrayList<Integer>();
		for (final Integer i : data)
			cs.add(i * 10);
		assertArrayEquals(cs.toArray(), bs.toArray());
	}
}