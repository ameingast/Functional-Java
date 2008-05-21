package functional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;

public abstract class Mapper<A, B> implements Functor<List<A>, List<B>, Object> {
	private static final Integer WORK_CNT = 10;
	
	private Integer tickets = 0;
	
	private Semaphore sem = new Semaphore(-1 * WORK_CNT + 1);
	
	public abstract B map(final A a);
	
	public List<B> apply(final List<A> as, final Object...os) {
		Aggregator aggregator = new Aggregator();
		List<Worker> workers = new ArrayList<Worker>(WORK_CNT);
		int j = 0;
		
		for (int i = 0; i < WORK_CNT; i++)
			workers.add(new Worker(aggregator));

		for (A a : as)
			workers.get((WORK_CNT - 1) % ++j).add(a, aggregator.getTicket());
		
		for (Worker worker : workers)
			worker.startWork();
		
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return aggregator.getResults();
	}
	
	class Worker extends Thread {
		private Map<Integer,A> items = new HashMap<Integer,A>();
		private Aggregator aggregator;
		
		public Worker(Aggregator aggregator) {
			this.aggregator = aggregator;
		}
		
		public synchronized void add(A a, int ticket) {
			items.put(ticket, a);
		}
		
		public void startWork() {
			for (Integer ticket : items.keySet()) {
				aggregator.add(ticket, map(items.get(ticket)));
			}
			sem.release();
		}
	}
	
	class Aggregator extends Thread {
		private SortedMap<Integer,B> results = new TreeMap<Integer,B>();
				
		public synchronized Integer getTicket() {
			return tickets++;
		}
		
		public synchronized void add(int ticket, B b) {
			results.put(ticket, b);
		}
		
		public synchronized List<B> getResults() {
			List<B> list = new ArrayList<B>();
			for (Integer ticket : results.keySet())
				list.add(results.get(ticket));
			return list;
		}
	}
}