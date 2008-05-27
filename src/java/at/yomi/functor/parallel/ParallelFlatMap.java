package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.FlatMap;
import at.yomi.functor.MapFold;
import at.yomi.functor.f.MapFunctor;

public class ParallelFlatMap<A,B> extends FlatMap<A,B> {
	protected final Integer commitInterval;

	protected final Integer workerCount;

	public ParallelFlatMap(final MapFunctor<A,B> functor, final Integer workerCount,
			final Integer commitInterval) {
		super(functor);
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	@Override
	protected MapFold<List<A>,List<B>,List<B>> getMapFold() {
		return new ParallelMapFold<List<A>,List<B>,List<B>>(mapFunctor, foldFunctor, workerCount,
				commitInterval);
	}
}
