package at.yomi.mp;

import at.yomi.mp.message.IMessage;
import at.yomi.mp.receiver.Receiver1;

public class LongAdder extends AbstractReceiver implements Receiver1<Long> {
	public Long count = new Long(0);

	public void addNumber(final Long num) {
		new AddCast<Long>(num).send(this);
	}

	public void handle(final AddCast<Long> a) {

	}

	@Override
	public void handle(final IMessage<Long> a) {
	// TODO Auto-generated method stub

	}
}
