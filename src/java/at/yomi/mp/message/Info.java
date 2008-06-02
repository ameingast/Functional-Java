package at.yomi.mp.message;

import at.yomi.mp.receiver.Receiver;

public abstract class Info {
	public <T extends Receiver> void send(final T receiver) {
		receiver.receive(this);
	}
}
