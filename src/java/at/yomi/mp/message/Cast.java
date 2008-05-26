package at.yomi.mp.message;

import at.yomi.mp.receiver.Receiver;
import at.yomi.mp.receiver.Receiver1;

public abstract class Cast<A> implements IMessage<A> {
	public final A content;

	public Cast(final A content) {
		this.content = content;
	}

	public <T extends Receiver> void send(final T receiver) {
		receiver.receive(this);
	}

	public <T extends Receiver1<A>> void send(final T receiver) {
		receiver.receive(this);
	}

}
