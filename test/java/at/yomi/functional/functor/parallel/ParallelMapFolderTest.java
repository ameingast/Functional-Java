package at.yomi.functional.functor.parallel;

import at.yomi.functional.functor.MapFolder;
import at.yomi.functional.functor.MapFolderTest;

public class ParallelMapFolderTest extends MapFolderTest {
	@Override
	protected MapFolder<Integer,Integer,String> getMulTenConcatAsStringFolder() {
		return new StrictParallelMapFolder<Integer,Integer,String>(WORK_COUNT) {
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
	protected MapFolder<Integer,Integer,Integer> getAddOneSumFolder() {
		return new ParallelMapFolder<Integer,Integer,Integer>(WORK_COUNT) {
			@Override
			public Integer fold(final Integer b, final Integer e) {
				return sum(b, e);
			}

			@Override
			public Integer map(final Integer a) {
				return addOne(a);
			}
		};
	}
}