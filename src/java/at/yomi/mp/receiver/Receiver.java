package at.yomi.mp.receiver;

import at.yomi.mp.message.AbstractMessage;

public interface Receiver {
	<T extends AbstractMessage<?>> void receive(T msg);
}
