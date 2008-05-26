package at.yomi.mp.receiver;

import at.yomi.mp.message.IMessage;

public interface Receiver1<A> extends Receiver {
	void handle(IMessage<A> a);
}
