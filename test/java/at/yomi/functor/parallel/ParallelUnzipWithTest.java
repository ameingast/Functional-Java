package at.yomi.functor.parallel;

import at.yomi.functor.UnZipWith;
import at.yomi.functor.UnZipWithTest;
import at.yomi.functor.Utils;

public class ParallelUnzipWithTest extends UnZipWithTest {
	protected UnZipWith<Integer,Integer,Integer> getPairAdder() {
		return new ParallelUnZipWith<Integer,Integer,Integer>(Utils.safePairAddFunctor);
	}
}
