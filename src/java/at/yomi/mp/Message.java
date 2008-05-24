package at.yomi.mp;

public abstract class Message<A,B> {
	public final A content;
	public final AbstractReceiver<B> sender;

	public Message(final AbstractReceiver<B> sender, final A content) {
		this.sender = sender;
		this.content = content;
	}

	public void send(final AbstractReceiver<A> receiver) {
		receiver.receive(this);
	}

	public String toString() {
		return content != null ? content.toString() : super.toString();
	}
}
