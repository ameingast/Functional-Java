package at.yomi.functional.functor.parallel.aggregator;

import java.util.Map;

import at.yomi.functional.functor.Folder;

public class FolderAggregator<A,B> extends Aggregator<A,B> {
	protected B e;
	protected Folder<A,B> folder;

	public FolderAggregator(final Integer itemCount, final Folder<A,B> folder, final B e) {
		super(itemCount);
		this.folder = folder;
		this.e = e;
	}

	@Override
	public synchronized void add(final Map<Integer,A> items) {
		for (final Integer ticket : items.keySet())
			e = folder.fold(items.get(ticket), e);
		counter.returnTickets(items.size());
	}

	@Override
	public B getResult() throws InterruptedException {
		counter.waitForLimit();
		return e;
	}
}