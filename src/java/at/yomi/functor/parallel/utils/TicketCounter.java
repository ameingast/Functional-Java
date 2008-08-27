package at.yomi.functor.parallel.utils;

import java.util.List;
import java.util.concurrent.Semaphore;

import at.yomi.collection.Enumeration;

public class TicketCounter {
    private final Semaphore counter;

    private Integer tickets = 0;

    public TicketCounter(final Integer counter) {
        this.counter = new Semaphore(-1 * counter + 1);
    }

    public void returnTickets(final Integer amount) {
        counter.release(amount);
    }

    public void waitForLimit() throws InterruptedException {
        counter.acquire();
    }

    public synchronized List<Integer> getTickets(final Integer amount) {
        final List<Integer> result = new Enumeration(tickets, tickets + amount);
        tickets += amount;
        return result;
    }
}