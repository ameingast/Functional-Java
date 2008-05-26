package at.yomi.mp.receiver;

import at.yomi.mp.message.IMessage;

public interface Receiver {
	<T extends IMessage<?>> void receive(T msg);
}
