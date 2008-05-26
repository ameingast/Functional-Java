package at.yomi.mp.message;

import at.yomi.mp.receiver.Receiver;

public abstract class Message<A,B> implements IMessage<A> {
	public final Receiver sender;
	public final A content;

	public <T extends Receiver> Message(final T sender, final A content) {
		this.sender = sender;
		this.content = content;
	}
}
