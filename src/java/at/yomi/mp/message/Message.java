package at.yomi.mp.message;

import at.yomi.mp.receiver.Receiver;

public abstract class Message<A,B> extends Cast<A> {
	public final Receiver sender;

	public <T extends Receiver> Message(final T sender, final A content) {
		super(content);
		this.sender = sender;
	}
}
