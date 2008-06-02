package at.yomi.mp;

import java.lang.reflect.Method;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import at.yomi.collection.BlockingLinkedList;
import at.yomi.mp.exception.DeliveryException;
import at.yomi.mp.message.Info;
import at.yomi.mp.message.ShutdownMessage;
import at.yomi.mp.receiver.Receiver;

public abstract class AbstractReceiver extends Thread implements Receiver {

	private final Queue<Info> msgs = new BlockingLinkedList<Info>();

	private final Semaphore shutdownLock = new Semaphore(0);

	private boolean shutdown = false;

	// FIXME: when working w/ pool the whole thing deadlocks
	public AbstractReceiver() {
		// ThreadPoolExecutorSingleton.getInstance().execute(this);
		new Thread(this).start();
		MPService.register(this);
	}

	public void run() {
		Info msg = null;

		try {
			while (!shutdown)
				if (null != (msg = msgs.poll()))
					findAndExecuteHandler(msg);
		} catch (final DeliveryException e) {
			MPService.unregister(this);
			MPService.shutDown();
			throw new RuntimeException(e);
		}

		shutdownLock.release();
	}

	public void shutDown() {
		new ShutdownMessage().send(this);
	}

	public void waitForShutDown() throws InterruptedException {
		shutdownLock.acquire();
	}

	@Override
	public void handle(final ShutdownMessage msg) {
		shutdown = true;
	}

	@Override
	public void receive(final Info msg) {
		msgs.add(msg);
	}

	/**
	 * FIXME: replace this with something the java compiler can actually handle. <br>
	 * work around java's lack of covariant parameter types by manually searching for the
	 * appropriate method at runtime. a __VERY__ bad approach.
	 * 
	 * @throws DeliveryException
	 */
	private void findAndExecuteHandler(final Info a) throws DeliveryException {
		try {
			for (final Method m : getClass().getMethods()) {
				if (!m.getName().startsWith("handle"))
					continue;
				if (m.getParameterTypes().length > 0
						&& m.getParameterTypes()[0].equals(a.getClass())) {
					m.invoke(this, a);
					return;
				}
			}
			throw new Exception();
		} catch (final Exception e) {
			throw new DeliveryException(this + ": Could not deliver message <" + a + ">, because <"
					+ getClass() + "> is not able to receive messages of type <" + a.getClass()
					+ ">");
		}
	}
}
