package at.yomi.functor.parallel.aggregator;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.MapFunctor;
import at.yomi.pair.Pair;

public class MapWorker<A,B> extends AbstractWorker<A,B> {
	private final MapFunctor<A,B> functor;

	public static <A,B,C> C start(final Integer workerCount, final Aggregator<B,C> aggregator,
			final List<A> as, final MapFunctor<A,B> mapFunctor) throws InterruptedException {
		final List<AbstractWorker<A,B>> workers = new ArrayList<AbstractWorker<A,B>>(workerCount);

		for (int i = 0; i < workerCount; i++)
			workers.add(new MapWorker<A,B>(aggregator, mapFunctor));
		setWork(workers, as);
		return aggregator.getResult();
	}

	private MapWorker(final Aggregator<B,?> aggregator, final MapFunctor<A,B> functor) {
		super(aggregator);
		this.functor = functor;
	}

	@Override
	public void run() {
		final List<Pair<Integer,B>> results = new ArrayList<Pair<Integer,B>>(items.size());

		for (final Pair<Integer,A> pair : items)
			results.add(new Pair<Integer,B>(pair.first, functor.apply(pair.second)));
		aggregator.add(results);
	}
}