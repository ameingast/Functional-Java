package at.yomi.mp;

import java.util.ArrayList;
import java.util.List;

public final class MPService {
	private static final List<AbstractReceiver> receivers = new ArrayList<AbstractReceiver>();

	public static synchronized void register(final AbstractReceiver receiver) {
		receivers.add(receiver);
	}

	public static synchronized void unregister(final AbstractReceiver receiver) {
		receivers.remove(receiver);
	}

	public static void startUp() {

	}

	public static synchronized void shutDown() {
		try {
			for (final AbstractReceiver receiver : receivers) {
				receiver.shutDown();
				receiver.join();
			}
		} catch (final InterruptedException e) {
			throw new RuntimeException("Could not shutdown MP system");
		}
	}
}
