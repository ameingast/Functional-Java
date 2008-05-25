package at.yomi.functor.parallel.utils;

import java.util.concurrent.Semaphore;

public class TicketCounter {
	private final Semaphore counter;

	private Integer tickets = 0;

	public TicketCounter(final Integer counter) {
		this.counter = new Semaphore(-1 * counter + 1);
	}

	public void returnTickets(final Integer amount) {
		counter.release(amount);
	}

	public void returnTicket() {
		counter.release();
	}

	public void waitForLimit() throws InterruptedException {
		counter.acquire();
	}

	public synchronized Integer getTicket() {
		return tickets++;
	}
}