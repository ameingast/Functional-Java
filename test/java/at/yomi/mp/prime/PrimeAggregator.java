package at.yomi.mp.prime;

import java.util.ArrayList;
import java.util.List;

import at.yomi.mp.AbstractReceiver;
import at.yomi.mp.prime.Messages.FoundPrimeCast;
import at.yomi.mp.prime.Messages.IsPrimeMessage;
import at.yomi.mp.prime.Messages.NoPrimeCast;
import at.yomi.mp.prime.Messages.StartCalculatingMessage;
import at.yomi.mp.receiver.Receiver3;

/**
 * TODO: The message model is broken -> An Integer-Receiver cannot send booleans -> maybe implement
 * multiple interfaces (Receiver<Boolean>, Receiver<Integer>...)
 * 
 * 
 * @author ameingast
 */
public class PrimeAggregator extends AbstractReceiver implements
		Receiver3<FoundPrimeCast,NoPrimeCast,StartCalculatingMessage> {
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
		new StartCalculatingMessage().send(this);
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

	public Integer getPrimeSum() throws InterruptedException {
		waitForShutDown();
		return sum;
	}

	@Override
	public void handle3(final StartCalculatingMessage b) {
		int j = 0;

		for (final Integer number : data)
			new IsPrimeMessage(this, number).send(primeCalculators.get(j++
					% primeCalculators.size()));
	}

	@Override
	public void handle2(final NoPrimeCast a) {
		incCounter();
	}

	@Override
	public void handle1(final FoundPrimeCast c) {
		sum += c.content;
		incCounter();
	}
}
