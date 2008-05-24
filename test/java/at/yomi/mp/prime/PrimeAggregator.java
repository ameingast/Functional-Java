package at.yomi.mp.prime;

import java.util.ArrayList;
import java.util.List;

import at.yomi.mp.AbstractReceiver;
import at.yomi.mp.prime.Messages.FoundPrimeMessage;
import at.yomi.mp.prime.Messages.NoPrimeMessage;
import at.yomi.mp.prime.Messages.StartCalculatingMessage;

/**
 * TODO: The message model is broken -> An Integer-Receiver cannot send booleans -> maybe implement
 * multiple interfaces (Receiver<Boolean>, Receiver<Integer>...)
 * 
 * 
 * @author ameingast
 */
public class PrimeAggregator extends AbstractReceiver<Integer> {
	private final List<Integer> data;

	private final List<PrimeCalculator> primeCalculators = new ArrayList<PrimeCalculator>();

	private int counter = 0;

	private Integer sum = 0;

	public PrimeAggregator(final Integer workerCount, final List<Integer> data) {
		super();

		this.data = data;
		addPrimeCalculators(workerCount);
	}

	private void addPrimeCalculators(final Integer workerCount) {
		for (int i = 0; i < (workerCount > 0 ? workerCount : 1); i++)
			primeCalculators.add(new PrimeCalculator());
	}

	public void startCalculating() {
		Messages.get().new StartCalculatingMessage(null, null).send(this);
	}

	public void handle(final StartCalculatingMessage msg) {
		int j = 0;

		for (final Integer number : data)
			Messages.get().new IsPrimeMessage(this, number).send(primeCalculators.get(j++
					% primeCalculators.size()));
	}

	public void handle(final FoundPrimeMessage msg) {
		sum += msg.content;
		incCounter();
	}

	public void handle(final NoPrimeMessage msg) {
		incCounter();
	}

	private void incCounter() {
		if (data.size() == ++counter)
			cleanUp();
	}

	private void cleanUp() {
		for (final PrimeCalculator pc : primeCalculators)
			pc.shutDown();
		shutDown();
	}

	public Integer getPrimeSum() {
		return sum;
	}
}
