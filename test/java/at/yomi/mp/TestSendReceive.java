package at.yomi.mp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.yomi.mp.receiver.CounterMessage;
import at.yomi.mp.receiver.CounterReceiver;

public class TestSendReceive {
	@Test
	public void testCount() throws InterruptedException {
		final CounterReceiver r1 = new CounterReceiver();
		final CounterReceiver r2 = new CounterReceiver();

		new CounterMessage(r1, 100000).send(r2);
		r1.waitForShutDown();
		r2.waitForShutDown();

		assertEquals(0, r1.getResult());
		assertEquals(0, r2.getResult());
		r1.shutDown();
		r2.shutDown();
	}
}
