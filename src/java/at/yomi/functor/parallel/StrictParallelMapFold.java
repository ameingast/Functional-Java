package at.yomi.functor.parallel;

import at.yomi.functor.parallel.aggregator.Aggregator;
import at.yomi.functor.parallel.aggregator.StrictFoldAggregator;

public abstract class StrictParallelMapFold<A,B,C> extends ParallelMapFold<A,B,C> {

	public StrictParallelMapFold() {
		super();
	}

	public StrictParallelMapFold(final Integer workerCount) {
		super(workerCount);
	}

	public StrictParallelMapFold(final Integer workerCount, final Integer commitInterval) {
		super(workerCount, commitInterval);
	}

	@Override
	protected Aggregator<B,C> getFolderAggregator(final Integer itemCount, final C e) {
		return new StrictFoldAggregator<B,C>(itemCount, folder, e);
	}
}