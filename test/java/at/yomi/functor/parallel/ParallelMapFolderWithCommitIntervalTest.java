package at.yomi.functor.parallel;

import at.yomi.functor.MapFold;
import at.yomi.functor.MapFoldTest;
import at.yomi.functor.parallel.ParallelMapFold;
import at.yomi.functor.parallel.StrictParallelMapFold;

public class ParallelMapFolderWithCommitIntervalTest extends MapFoldTest {
	@Override
	protected MapFold<Integer,Integer,String> getMulTenConcatAsStringFolder() {
		return new StrictParallelMapFold<Integer,Integer,String>(WORKER_COUNT, COMMIT_INTERVAL) {
			@Override
			public String fold(final Integer b, final String e) {
				return concat(b, e);
			}

			@Override
			public Integer map(final Integer a) {
				return mulTen(a);
			}
		};
	}

	@Override
	protected MapFold<Integer,Integer,Integer> getAddOneSumFolder() {
		return new ParallelMapFold<Integer,Integer,Integer>(WORKER_COUNT, COMMIT_INTERVAL) {

			@Override
			public Integer fold(final Integer b, final Integer e) {
				return sum(e, b);
			}

			@Override
			public Integer map(final Integer a) {
				return addOne(a);
			}
		};
	}
}