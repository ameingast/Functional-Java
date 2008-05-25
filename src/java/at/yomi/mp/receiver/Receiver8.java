package at.yomi.mp.receiver;

public interface Receiver8<A,B,C,D,E,F,G,H> extends Receiver7<A,B,C,D,E,F,G> {
	void handle(H h);
}
