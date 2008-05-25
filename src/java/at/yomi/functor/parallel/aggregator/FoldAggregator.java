package at.yomi.functor.parallel.aggregator;

import java.util.List;

import at.yomi.functor.Fold;
import at.yomi.pair.Pair;

public class FoldAggregator<A,B> extends Aggregator<A,B> {
	protected B e;
	protected Fold<A,B> folder;

	public FoldAggregator(final Integer itemCount, final Fold<A,B> folder, final B e) {
		super(itemCount);
		this.folder = folder;
		this.e = e;
	}

	@Override
	public synchronized void add(final List<Pair<Integer,A>> items) {
		for (final Pair<Integer,A> pair : items)
			e = folder.fold(pair.second, e);
		counter.returnTickets(items.size());
	}

	@Override
	public B getResult() throws InterruptedException {
		counter.waitForLimit();
		return e;
	}
}