package at.yomi.mp.message;

import at.yomi.mp.receiver.Receiver1;

public interface IMessage<A> {
	<T extends Receiver1<A>> void send(T receiver);
}
