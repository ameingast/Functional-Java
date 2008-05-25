package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.Zipper;
import at.yomi.functor.ZipWith;
import at.yomi.functor.parallel.aggregator.Worker;
import at.yomi.pair.Pair;

public abstract class ParallelZipWith<A,B,C> extends ZipWith<A,B,C> {

	private final ZipWith<A,B,C> self = this;

	protected final Integer workerCount;

	protected final Integer commitInterval;

	public ParallelZipWith() {
		this(Worker.DEFAULT_WORKER_COUNT, Worker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelZipWith(final Integer workerCount) {
		this(workerCount, Worker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelZipWith(final Integer workerCount, final Integer commitInterval) {
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	@Override
	public List<C> apply(final List<A> as, final List<B> bs) {
		return new ParallelMapper<Pair<A,B>,C>(workerCount, commitInterval) {
			@Override
			public C map(final Pair<A,B> pair) {
				return self.handle(pair.first, pair.second);
			}
		}.apply(new Zipper<A,B>().apply(as, bs));
	}
}
