package at.yomi.mp;

public class ShutdownMessage<A> extends Message<A> {
	public ShutdownMessage(final AbstractReceiver<A> sender, final A content) {
		super(sender, content);
	}
}
