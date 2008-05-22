package at.yomi.functional.functor;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import at.yomi.functional.BaseTest;

public class MapperTest extends BaseTest {

	private final Mapper<Integer,Integer> addOneMapper;
	private final Mapper<Integer,Integer> mulTenMapper;

	public MapperTest() {
		addOneMapper = getAddOneMapper();
		mulTenMapper = getMulTenMapper();
	}

	protected Mapper<Integer,Integer> getMulTenMapper() {
		return new Mapper<Integer,Integer>() {
			@Override
			public Integer map(final Integer a) {
				return 10 * a;
			}
		};
	}

	protected Mapper<Integer,Integer> getAddOneMapper() {
		return new Mapper<Integer,Integer>() {
			@Override
			public Integer map(final Integer a) {
				return a + 1;
			}
		};
	}

	@Test
	public void testAddOne() {
		final List<Integer> bs = addOneMapper.apply(as);

		final List<Integer> cs = new ArrayList<Integer>();
		for (final Integer i : as)
			cs.add(i + 1);
		assertArrayEquals(cs.toArray(), bs.toArray());
	}

	@Test
	public void testMulTen() {
		final List<Integer> bs = mulTenMapper.apply(as);

		final List<Integer> cs = new ArrayList<Integer>();
		for (final Integer i : as)
			cs.add(i * 10);
		assertArrayEquals(cs.toArray(), bs.toArray());
	}
}