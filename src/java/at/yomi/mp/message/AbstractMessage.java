package at.yomi.mp.message;

import at.yomi.mp.receiver.Receiver1;

public abstract class AbstractMessage<A> {
	public final A content;

	public AbstractMessage(final A content) {
		this.content = content;
	}

	public <T extends AbstractMessage<A>,U extends Receiver1<T>> void send(final U receiver) {
		receiver.receive(this);
	}

	public String toString() {
		return "<" + content != null ? content.toString() : super.toString() + ">";
	}
}
