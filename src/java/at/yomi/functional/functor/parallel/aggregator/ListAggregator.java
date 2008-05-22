package at.yomi.functional.functor.parallel.aggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


public class ListAggregator<A> extends Aggregator<A,List<A>> {
	private SortedMap<Integer,A> results = new TreeMap<Integer,A>();

	public ListAggregator(Integer itemCount) {
		super(itemCount);
	}

	public synchronized void add(Map<Integer,A> items) {
		results.putAll(items);
		counter.returnTickets(items.size());
	}

	public List<A> getResult() throws InterruptedException {
		counter.waitForLimit();
		return new ArrayList<A>(results.values());
	}
}