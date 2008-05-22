package at.yomi.functional.functor.parallel;

import at.yomi.functional.functor.parallel.aggregator.Aggregator;
import at.yomi.functional.functor.parallel.aggregator.StrictFolderAggregator;

public abstract class StrictParallelMapFolder<A,B,C> extends ParallelMapFolder<A,B,C> {

	public StrictParallelMapFolder() {
		super();
	}

	public StrictParallelMapFolder(final Integer workerCount) {
		super(workerCount);
	}

	public StrictParallelMapFolder(final Integer workerCount, final Integer commitInterval) {
		super(workerCount, commitInterval);
	}

	@Override
	protected Aggregator<B,C> getFolderAggregator(final Integer itemCount, final C e) {
		return new StrictFolderAggregator<B,C>(itemCount, folder, e);
	}
}