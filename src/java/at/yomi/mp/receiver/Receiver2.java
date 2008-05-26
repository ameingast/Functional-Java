package at.yomi.mp.receiver;

import at.yomi.mp.message.IMessage;

public interface Receiver2<A,B> extends Receiver1<A> {
	void handle(IMessage<B> b);
}
