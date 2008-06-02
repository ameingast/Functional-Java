package at.yomi.functor.parallel.aggregator;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.Fold;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.pair.functor.FoldSecond;

;

public class FoldWorker<A,B> extends AbstractWorker<A,B> {
	private final B e;

	private final FoldFunctor<A,B> functor;

	public static <A> A start(final Integer workerCount, final Integer commitInterval,
			final ListAggregator<A> aggregator, final List<A> as,
			final FoldFunctor<A,A> foldFunctor, final A e) throws InterruptedException {
		final List<AbstractWorker<A,A>> workers = new ArrayList<AbstractWorker<A,A>>(workerCount);

		for (int i = 0; i < workerCount; i++)
			workers.add(new FoldWorker<A,A>(commitInterval, aggregator, foldFunctor, e));
		setWork(workers, as);
		return new Fold<A,A>(foldFunctor).apply(aggregator.getResult(), e);
	}

	public FoldWorker(final Integer commitInterval, final Aggregator<B,?> aggregator,
			final FoldFunctor<A,B> functor, final B e) {
		super(commitInterval, aggregator);
		this.functor = functor;
		this.e = e;
	}

	@Override
	public void run() {
		aggregator.add(new FoldSecond<Integer,A,B>(functor).apply(items, e), items.size());
	}
}
