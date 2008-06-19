package at.yomi.functor.parallel;

import at.yomi.functor.BaseTest;
import at.yomi.functor.Map;
import at.yomi.functor.Utils;

public abstract class Functors extends BaseTest {
	public static final Map<Integer,Boolean> primeMap = new Map<Integer,Boolean>(
			Utils.isPrimeFunctor);

	public static final Map<Integer,Boolean> pPrimeMap = new ParallelMap<Integer,Boolean>(
			Utils.isPrimeFunctor, WORKER_COUNT);

}
