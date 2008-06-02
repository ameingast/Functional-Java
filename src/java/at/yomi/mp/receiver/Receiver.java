package at.yomi.mp.receiver;

import at.yomi.mp.message.Info;
import at.yomi.mp.message.ShutdownMessage;

public interface Receiver {
	<T extends Info> void receive(T msg);

	void handle(ShutdownMessage msg);
}
