package at.yomi.functor.parallel;

import at.yomi.functor.ZipWith;
import at.yomi.functor.ZipWithTest;

public class ParallelZipWithTest extends ZipWithTest {
	protected ZipWith<Integer,Integer,Integer> getZipWith() {
		return new ParallelZipWith<Integer,Integer,Integer>() {
			@Override
			public Integer handle(final Integer a, final Integer b) {
				return (a == null ? 0 : a) + (b == null ? 0 : b);
			}
		};
	}
}
