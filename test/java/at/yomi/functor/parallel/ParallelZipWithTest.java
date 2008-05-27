package at.yomi.functor.parallel;

import at.yomi.functor.Utils;
import at.yomi.functor.ZipWith;
import at.yomi.functor.ZipWithTest;

public class ParallelZipWithTest extends ZipWithTest {
	protected ZipWith<Integer,Integer,Integer> getZipWith() {
		return new ParallelZipWith<Integer,Integer,Integer>(Utils.safeSumFunctor, WORKER_COUNT,
				COMMIT_INTERVAL);
	}
}
