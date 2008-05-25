package at.yomi.mp.receiver;

public interface Receiver7<A,B,C,D,E,F,G> extends Receiver6<A,B,C,D,E,F> {
	void handle(G g);
}
