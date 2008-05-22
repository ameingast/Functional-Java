package at.yomi.functional.functor.parallel;

import at.yomi.functional.functor.parallel.aggregator.Aggregator;
import at.yomi.functional.functor.parallel.aggregator.StrictFolderAggregator;

public abstract class StrictParallelMapFolder<A,B,C> extends ParallelMapFolder<A,B,C> {
	public StrictParallelMapFolder(Integer workerCount) {
		super(workerCount);
	}

	@Override
	protected Aggregator<B,C> getFolderAggregator(Integer itemCount, C e) {
		return new StrictFolderAggregator<B,C>(itemCount, folder, e);
	}
}
