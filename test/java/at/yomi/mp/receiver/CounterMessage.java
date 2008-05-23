package at.yomi.mp.receiver;

import at.yomi.mp.Message;

public class CounterMessage extends Message<Integer> {
	public CounterMessage(final CounterReceiver sender, final Integer content) {
		super(sender, content);
	}
}