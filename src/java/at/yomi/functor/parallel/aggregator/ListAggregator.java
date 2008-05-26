package at.yomi.functor.parallel.aggregator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.yomi.pair.Pair;
import at.yomi.pair.PairFirstElementComparator;

public class ListAggregator<A> extends Aggregator<A,List<A>> {
	private final List<Pair<Integer,A>> items = new ArrayList<Pair<Integer,A>>();

	public ListAggregator(final Integer itemCount) {
		super(itemCount);
	}

	@Override
	public synchronized void add(final List<Pair<Integer,A>> items) {
		this.items.addAll(items);
		counter.returnTickets(items.size());
	}

	// FIXME: sometimes a concurrent modification exception is thrown
	@Override
	public List<A> getResult() throws InterruptedException {
		final List<A> results = new ArrayList<A>(items.size());

		counter.waitForLimit();
		Collections.sort(items, new PairFirstElementComparator<Integer,A>());
		for (final Pair<Integer,A> pair : items)
			results.add(pair.second);
		return results;
	}

	@Override
	public synchronized void add(final A a, final Integer amount) {
		items.add(new Pair<Integer,A>(0, a));
		counter.returnTickets(amount);
	}
}