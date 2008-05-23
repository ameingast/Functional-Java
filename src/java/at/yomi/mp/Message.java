package at.yomi.mp;

public abstract class Message<A> {
	public final A content;
	public final AbstractReceiver<A> sender;

	public Message(final AbstractReceiver<A> sender, final A content) {
		this.sender = sender;
		this.content = content;
	}

	public void send(final AbstractReceiver<A> receiver) {
		receiver.receive(this);
	}

	public String toString() {
		return content.toString();
	}
}
