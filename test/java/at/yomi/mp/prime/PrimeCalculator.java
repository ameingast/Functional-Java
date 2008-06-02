package at.yomi.mp.prime;

import at.yomi.functor.Utils;
import at.yomi.mp.AbstractReceiver;
import at.yomi.mp.prime.Messages.FoundPrimeCast;
import at.yomi.mp.prime.Messages.IsPrimeMessage;
import at.yomi.mp.prime.Messages.NoPrimeCast;
import at.yomi.mp.receiver.Receiver1;

public class PrimeCalculator extends AbstractReceiver implements Receiver1<IsPrimeMessage> {
	public void handle1(final IsPrimeMessage msg) {
		if (Utils.isPrime(msg.content))
			new FoundPrimeCast(msg.content).send(msg.sender);
		else
			new NoPrimeCast(msg.content).send(msg.sender);
	}
}
