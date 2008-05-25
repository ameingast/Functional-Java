package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.Fold;
import at.yomi.functor.MapFold;
import at.yomi.functor.f.F;
import at.yomi.functor.parallel.aggregator.Aggregator;
import at.yomi.functor.parallel.aggregator.FoldAggregator;
import at.yomi.functor.parallel.aggregator.Worker;

public abstract class ParallelMapFold<A,B,C> extends MapFold<A,B,C> {

	private final MapFold<A,B,C> self = this;

	protected final Integer workerCount;

	protected final Integer commitInterval;

	protected Fold<B,C> folder = new Fold<B,C>() {
		@Override
		public C fold(B b, C e) {
			return self.fold(b, e);
		}
	};

	public ParallelMapFold() {
		this(Worker.DEFAULT_WORKER_COUNT);
	}

	public ParallelMapFold(final Integer workerCount) {
		this(workerCount, Worker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelMapFold(final Integer workerCount, final Integer commitInterval) {
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	protected Aggregator<B,C> getFolderAggregator(final Integer itemCount, final C e) {
		return new FoldAggregator<B,C>(itemCount, folder, e);
	}

	@Override
	public C apply(final List<A> as, final C c) {
		final Aggregator<B,C> aggregator = getFolderAggregator(as.size(), c);

		Worker.createWorkers(workerCount, commitInterval, aggregator, as, true, new F<A,B>() {
			public B apply(final A a) {
				return map(a);
			}
		});
		try {
			return aggregator.getResult();
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
