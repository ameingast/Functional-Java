package at.yomi.functor.parallel.aggregator;

import java.util.List;

import at.yomi.functor.Folder;
import at.yomi.pair.Pair;

public class FolderAggregator<A,B> extends Aggregator<A,B> {
	protected B e;
	protected Folder<A,B> folder;

	public FolderAggregator(final Integer itemCount, final Folder<A,B> folder, final B e) {
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