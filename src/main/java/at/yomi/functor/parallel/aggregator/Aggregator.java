package at.yomi.functor.parallel.aggregator;

import java.util.List;

import at.yomi.functor.parallel.utils.TicketCounter;
import at.yomi.pair.Pair;

public abstract class Aggregator<A, B> {
    protected final TicketCounter counter;

    public Aggregator(final Integer itemCount) {
        this.counter = new TicketCounter(itemCount);
    }

    public abstract void add(A a, Integer amount);

    public abstract void add(List<Pair<Integer, A>> items);

    public abstract B getResult() throws InterruptedException;

    public synchronized List<Integer> getTickets(final Integer amount) {
        return counter.getTickets(amount);
    }
}