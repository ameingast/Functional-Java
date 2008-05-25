package at.yomi.mp.message;

import at.yomi.mp.receiver.Receiver;

@SuppressWarnings("unchecked")
public class Info extends AbstractMessage {
	public Info() {
		super(null);
	}

	public void send(final Receiver receiver) {
		receiver.receive(this);
	}
}
