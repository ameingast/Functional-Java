package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.Map;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.parallel.aggregator.ListAggregator;
import at.yomi.functor.parallel.aggregator.MapWorker;

public class ParallelMap<A,B> extends Map<A,B> {
	protected final Integer workerCount;

	protected final Integer commitInterval;

	public ParallelMap(final MapFunctor<A,B> functor, final Integer workerCount,
			final Integer commitInterval) {
		super(functor);
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	@Override
	public List<B> apply(final List<A> as) {
		try {
			return MapWorker.start(workerCount, commitInterval, new ListAggregator<B>(as.size()),
					as, functor);
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}