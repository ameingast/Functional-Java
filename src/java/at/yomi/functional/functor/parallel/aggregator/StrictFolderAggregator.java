package at.yomi.functional.functor.parallel.aggregator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.yomi.functional.functor.Folder;
import at.yomi.pair.Pair;
import at.yomi.pair.PairFirstElementComparator;

public class StrictFolderAggregator<A,B> extends FolderAggregator<A,B> {
	protected List<Pair<Integer,A>> items = new ArrayList<Pair<Integer,A>>();

	public StrictFolderAggregator(final Integer itemCount, final Folder<A,B> folder, final B e) {
		super(itemCount, folder, e);
	}

	@Override
	public synchronized void add(final List<Pair<Integer,A>> items) {
		this.items.addAll(items);
		counter.returnTickets(items.size());
	}

	@Override
	public B getResult() throws InterruptedException {
		counter.waitForLimit();
		Collections.sort(items, new PairFirstElementComparator<Integer,A>());
		for (final Pair<Integer,A> pair : items)
			e = folder.fold(pair.second, e);
		return e;
	}
}