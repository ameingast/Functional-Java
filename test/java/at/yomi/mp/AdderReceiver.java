package at.yomi.mp;

import at.yomi.mp.receiver.Receiver1;

public class AdderReceiver<A extends Number> extends AbstractReceiver implements
		Receiver1<AddCast<A>> {
	public Long count = new Long(0);

	public void addNumber(final A num) {
		new AddCast<A>(num).send(this);
	}

	@Override
	public void handle(final AddCast<A> a) {
		count += a.content.longValue();
	}
}
