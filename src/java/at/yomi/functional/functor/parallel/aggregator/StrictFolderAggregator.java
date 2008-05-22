package at.yomi.functional.functor.parallel.aggregator;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import at.yomi.functional.functor.Folder;

public class StrictFolderAggregator<A,B> extends FolderAggregator<A,B> {
	protected SortedMap<Integer,A> items = new TreeMap<Integer,A>();

	public StrictFolderAggregator(final Integer itemCount, final Folder<A,B> folder, final B e) {
		super(itemCount, folder, e);
	}

	@Override
	public synchronized void add(final Map<Integer,A> items) {
		this.items.putAll(items);
		counter.returnTickets(items.size());
	}

	@Override
	public B getResult() throws InterruptedException {
		counter.waitForLimit();
		for (final Integer ticket : items.keySet())
			e = folder.fold(items.get(ticket), e);
		return e;
	}
}