package at.yomi.functional.functor.parallel;

import at.yomi.functional.functor.MapFolder;
import at.yomi.functional.functor.MapFolderTest;

public class ParallelMapFolderTest extends MapFolderTest {
	protected MapFolder<Integer,Integer,String> getMulTenConcatAsStringFolder() {
		return new StrictParallelMapFolder<Integer,Integer,String>(WORK_COUNT) {
			public String fold(Integer b, String e) {
				return e + b.toString();
			}

			public Integer map(Integer a) {
				return 10 * a;
			}
		};
	}

	protected MapFolder<Integer,Integer,Integer> getAddOneSumFolder() {
		return new ParallelMapFolder<Integer,Integer,Integer>(WORK_COUNT) {
			public Integer fold(Integer b, Integer e) {
				return e + b;
			}

			public Integer map(Integer a) {
				return a + 1;
			}
		};
	}
}
