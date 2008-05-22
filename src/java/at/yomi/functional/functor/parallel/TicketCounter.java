package at.yomi.functional.functor.parallel;

import java.util.concurrent.Semaphore;

public class TicketCounter {
	private final Semaphore counter;
	private int tickets = 0;

	public TicketCounter(Integer limit) {
		this.counter = new Semaphore(-1 * limit + 1);
	}

	public void returnTickets(Integer amount) {
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