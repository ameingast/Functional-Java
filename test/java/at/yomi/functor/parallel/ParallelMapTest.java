package at.yomi.functor.parallel;

import at.yomi.functor.Map;
import at.yomi.functor.MapTest;
import at.yomi.functor.parallel.ParallelMap;

public class ParallelMapTest extends MapTest {
	@Override
	protected Map<Integer,Integer> getAddOneMapper() {
		return new ParallelMap<Integer,Integer>(WORKER_COUNT) {
			@Override
			public Integer map(final Integer a) {
				return a + 1;
			}
		};
	}

	@Override
	protected Map<Integer,Integer> getMulTenMapper() {
		return new ParallelMap<Integer,Integer>(WORKER_COUNT) {
			@Override
			public Integer map(final Integer a) {
				return 10 * a;
			}
		};
	}
}
