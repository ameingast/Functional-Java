package at.yomi.functor.parallel;

import at.yomi.functor.Zip;
import at.yomi.functor.ZipWith;
import at.yomi.functor.parallel.aggregator.Worker;
import at.yomi.pair.Pair;

public class ParallelZip<A,B> extends Zip<A,B> {

	protected final Integer workerCount;

	protected final Integer commitInterval;

	public ParallelZip() {
		this(Worker.DEFAULT_WORKER_COUNT, Worker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelZip(final Integer workerCount) {
		this(workerCount, Worker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelZip(final Integer workerCount, final Integer commitInterval) {
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	protected ZipWith<A,B,Pair<A,B>> getZipper() {
		return new ParallelZipWith<A,B,Pair<A,B>>(workerCount, commitInterval) {
			@Override
			public Pair<A,B> handle(final A a, final B b) {
				return new Pair<A,B>(a, b);
			}
		};
	}
}
