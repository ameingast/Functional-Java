package at.yomi.mp.receiver;

import at.yomi.mp.AbstractReceiver;

public class CounterReceiver extends AbstractReceiver<Integer> {
	private Integer result = -1;

	public void handle(final CounterMessage msg) {
		if (msg.content == 1) {
			result = msg.content - 1;
			new CounterMessage(this, msg.content - 1).send(msg.sender);
			shutDown();
		} else if (msg.content > 0) {
			new CounterMessage(this, msg.content - 1).send(msg.sender);
		} else {
			result = msg.content;
			shutDown();
		}
	}

	public Integer getResult() {
		return result;
	}

}
