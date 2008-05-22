package at.yomi.functional.functor.parallel.aggregator;

import java.util.Map;

import at.yomi.functional.functor.parallel.TicketCounter;

public abstract class Aggregator<A,B> {
	protected final TicketCounter counter;

	public Aggregator(Integer itemCount) {
		this.counter = new TicketCounter(itemCount);
	}

	public abstract void add(Map<Integer,A> items);

	public abstract B getResult() throws InterruptedException;

	public synchronized Integer getTicket() {
		return counter.getTicket();
	}
}
