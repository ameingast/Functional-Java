package at.yomi.functional.functor.parallel.aggregator;

import java.util.List;

import at.yomi.functional.functor.parallel.TicketCounter;
import at.yomi.pair.Pair;

public abstract class Aggregator<A,B> {
	protected final TicketCounter counter;

	public Aggregator(final Integer itemCount) {
		this.counter = new TicketCounter(itemCount);
	}

	public abstract void add(List<Pair<Integer,A>> items);

	public abstract B getResult() throws InterruptedException;

	public synchronized Integer getTicket() {
		return counter.getTicket();
	}
}