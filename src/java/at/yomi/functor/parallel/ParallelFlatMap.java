package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.FlatMap;
import at.yomi.functor.MapFold;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.parallel.aggregator.MapWorker;

public class ParallelFlatMap<A,B> extends FlatMap<A,B> {
	private final Integer commitInterval;

	private final Integer workerCount;

	public ParallelFlatMap(final MapFunctor<A,B> functor) {
		this(functor, MapWorker.DEFAULT_WORKER_COUNT, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelFlatMap(final MapFunctor<A,B> functor, final Integer workerCount) {
		this(functor, workerCount, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

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
