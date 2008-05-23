package at.yomi.mp;

import java.lang.reflect.Method;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import at.yomi.mp.util.BlockingLinkedList;

public abstract class AbstractReceiver<A> extends Thread {

	private final Queue<Message<A>> msgs = new BlockingLinkedList<Message<A>>();

	private final Semaphore shutdownLock = new Semaphore(0);

	private boolean shutdown = false;

	public AbstractReceiver() {
		start();
		MP.register(this);
	}

	public void run() {
		Message<A> a = null;

		try {
			while (!shutdown)
				if (null != (a = msgs.poll()))
					findAndExecuteHandler(a);
		} catch (final DeliveryException e) {
			MP.unregister(this);
			MP.shutDown();
			throw new RuntimeException(e);
		}

		shutdownLock.release();
	}

	public void receive(final Message<A> msg) {
		msgs.add(msg);
	}

	public void shutDown() {
		new ShutdownMessage<A>(null, null).send(this);
	}

	public void waitForShutDown() throws InterruptedException {
		shutdownLock.acquire();
	}

	public void handle(final ShutdownMessage<A> msg) {
		shutdown = true;
	}

	/**
	 * FIXME: replace this with something the java compiler can actually handle. <br>
	 * work around java's lack of covariant parameter types by manually searching for the
	 * appropriate method at runtime. a __VERY__ bad approach.
	 * 
	 * @throws DeliveryException
	 * @throws InterruptedException
	 */
	private void findAndExecuteHandler(final Message<A> a) throws DeliveryException {
		try {
			final Method m = getClass().getMethod("handle", a.getClass());
			m.invoke(this, a);
		} catch (final Exception e) {
			throw new DeliveryException(this + ": Could not deliver message <" + a.toString()
					+ ">, because <" + getClass() + "> is not able to receive messages of type <"
					+ a.getClass() + ">: ");

		}
	}
}
