package at.yomi.functor.parallel;

import at.yomi.functor.ZipperTest;
import at.yomi.functor.Zipper;

public class ParallelZipperTest extends ZipperTest {
	@Override
	public Zipper<Integer,Integer> getZip() {
		return new ParallelZipper<Integer,Integer>(WORKER_COUNT, COMMIT_INTERVAL);
	}
}
