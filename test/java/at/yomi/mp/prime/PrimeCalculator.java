package at.yomi.mp.prime;

import at.yomi.benchmark.Utils;
import at.yomi.mp.AbstractReceiver;
import at.yomi.mp.prime.Messages.IsPrimeMessage;

public class PrimeCalculator extends AbstractReceiver<Integer> {
	public void handle(final IsPrimeMessage msg) {
		if (Utils.isPrime(msg.content))
			Messages.get().new FoundPrimeMessage(this, msg.content).send(msg.sender);
		else
			Messages.get().new NoPrimeMessage(this, msg.content).send(msg.sender);
	}
}
