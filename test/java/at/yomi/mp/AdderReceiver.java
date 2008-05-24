package at.yomi.mp;

public class AdderReceiver<A extends Number> extends AbstractReceiver<A> {
	public Long count = new Long(0);

	public void handle(final AddMessage msg) {
		count += msg.content.longValue();
	}

	public class AddMessage extends Message<A,A> {
		public AddMessage(final AbstractReceiver<A> sender, final A content) {
			super(sender, content);
		}
	}
}
