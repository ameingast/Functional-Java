package at.yomi.mp.arith;

import at.yomi.mp.AbstractReceiver;
import at.yomi.mp.arith.Messages.AddCast;
import at.yomi.mp.arith.Messages.MulCast;
import at.yomi.mp.arith.Messages.SubCast;
import at.yomi.mp.receiver.Receiver3;

public class ArithReceiver extends AbstractReceiver implements Receiver3<AddCast,SubCast,MulCast> {
	public Integer count = 0;

	public void addNumber(final Number num) {
		new AddCast(num).send(this);
	}

	public void subNumber(final Long num) {
		new SubCast(num).send(this);
	}

	public void mulNumber(final Long num) {
		new MulCast(num).send(this);
	}

	@Override
	public void handle1(final AddCast a) {
		count += a.content.intValue();
	}

	@Override
	public void handle2(final SubCast b) {
		count -= b.content.intValue();
	}

	@Override
	public void handle3(final MulCast a) {
		count += a.content.intValue();
	}

	public Number getResult() throws InterruptedException {
		shutDown();
		waitForShutDown();
		return count;
	}
}
