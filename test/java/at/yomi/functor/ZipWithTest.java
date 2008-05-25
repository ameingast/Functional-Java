package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ZipWithTest extends BaseTest {
	@Test
	public void testAddNumbersInLists() {
		assertArrayEquals(iterateZip(smallData, smallData).toArray(), getZipWith().apply(smallData,
				smallData).toArray());
	}

	protected ZipWith<Integer,Integer,Integer> getZipWith() {
		return new ZipWith<Integer,Integer,Integer>() {
			@Override
			public Integer handle(final Integer a, final Integer b) {
				return (a == null ? 0 : a) + (b == null ? 0 : b);
			}
		};
	}

	private List<Integer> iterateZip(final List<Integer> as, final List<Integer> bs) {
		final List<Integer> zs = new ArrayList<Integer>();
		int i = 0;

		for (i = 0; i < as.size(); i++)
			zs.add(as.get(i) + (i < bs.size() ? bs.get(i) : 0));
		for (; i < bs.size(); i++)
			zs.add((i < as.size() ? as.get(i) : 0) + bs.get(i));
		return zs;
	}
}
