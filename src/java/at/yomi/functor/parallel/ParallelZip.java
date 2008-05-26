package at.yomi.functor.parallel;

import at.yomi.functor.Zip;
import at.yomi.functor.ZipWith;
import at.yomi.functor.parallel.aggregator.MapWorker;
import at.yomi.pair.Pair;

public class ParallelZip<A,B> extends Zip<A,B> {
	protected final Integer workerCount;

	protected final Integer commitInterval;

	public ParallelZip() {
		this(MapWorker.DEFAULT_WORKER_COUNT, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelZip(final Integer workerCount) {
		this(workerCount, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelZip(final Integer workerCount, final Integer commitInterval) {
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	protected ZipWith<A,B,Pair<A,B>> getZipper() {
		return new ParallelZipWith<A,B,Pair<A,B>>(pairFunctor, workerCount, commitInterval);
	}
}
