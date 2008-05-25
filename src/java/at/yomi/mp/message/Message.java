package at.yomi.mp.message;

import at.yomi.mp.receiver.Receiver1;

public abstract class Message<A,B> extends AbstractMessage<A> {
	public Receiver1<B> sender;

	public <T extends Receiver1<B>> Message(final T sender, final A content) {
		super(content);
		this.sender = sender;
	}
}
