// package at.yomi.mp.prime;
//
// import at.yomi.benchmark.Utils;
// import at.yomi.mp.AbstractReceiver;
// import at.yomi.mp.prime.Messages.IsPrimeMessage;
// import at.yomi.mp.receiver.Receiver1;
//
// public class PrimeCalculator extends AbstractReceiver implements Receiver1<IsPrimeMessage> {
// public void handle(final IsPrimeMessage msg) {
// if (Utils.isPrime(msg.content))
// Messages.get().new FoundPrimeCast(msg.content).send(msg.sender);
// else
// Messages.get().new NoPrimeCast(msg.content).send(msg.sender);
// }
// }
