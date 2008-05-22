package at.yomi.functional.functor.parallel.aggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ListAggregator<A> extends Aggregator<A,List<A>> {
	private final SortedMap<Integer,A> results = new TreeMap<Integer,A>();

	public ListAggregator(final Integer itemCount) {
		super(itemCount);
	}

	@Override
	public synchronized void add(final Map<Integer,A> items) {
		results.putAll(items);
		counter.returnTickets(items.size());
	}

	@Override
	public List<A> getResult() throws InterruptedException {
		counter.waitForLimit();
		return new ArrayList<A>(results.values());
	}
}