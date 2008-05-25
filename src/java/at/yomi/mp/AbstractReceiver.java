package at.yomi.mp;

import java.lang.reflect.Method;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import at.yomi.collection.BlockingLinkedList;
import at.yomi.mp.exception.DeliveryException;
import at.yomi.mp.message.AbstractMessage;
import at.yomi.mp.message.ShutdownMessage;
import at.yomi.mp.receiver.Receiver;

// FIXME: interfaces can only be implemented once... hahahah
public abstract class AbstractReceiver extends Thread implements Receiver {

	private final Queue<AbstractMessage<?>> msgs = new BlockingLinkedList<AbstractMessage<?>>();

	private final Semaphore shutdownLock = new Semaphore(0);

	private boolean shutdown = false;

	// FIXME: when working w/ pool the whole thing deadlocks
	public AbstractReceiver() {
		// ThreadPoolExecutorSingleton.getInstance().execute(this);
		new Thread(this).start();
		MPService.register(this);
	}

	public void run() {
		AbstractMessage<?> msg = null;

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

	// FIXME: use this approach for delivering isntread of findAndEx...
	// public <T extends AbstractMessage<?>> void handle(final T msg) {
	//
	// }

	public void handle(final ShutdownMessage msg) {
		shutdown = true;
	}

	@Override
	public <T extends AbstractMessage<?>> void receive(final T msg) {
		msgs.add(msg);
	}

	/**
	 * FIXME: replace this with something the java compiler can actually handle. <br>
	 * work around java's lack of covariant parameter types by manually searching for the
	 * appropriate method at runtime. a __VERY__ bad approach.
	 * 
	 * @throws DeliveryException
	 */
	private void findAndExecuteHandler(final AbstractMessage<?> a) throws DeliveryException {
		try {
			final Method m = getClass().getMethod("handle", a.getClass());
			m.invoke(this, a);
		} catch (final NoSuchMethodException e) {
			e.printStackTrace();
			throw new DeliveryException(this + ": Could not deliver message <" + a + ">, because <"
					+ getClass() + "> is not able to receive messages of type <" + a.getClass()
					+ ">");
		} catch (final Exception e) {
			throw new DeliveryException(e);
		}
	}
}
