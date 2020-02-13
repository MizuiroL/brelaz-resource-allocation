package brelaz;

import java.util.LinkedList;

public class Network {

	public LinkedList<Channels> chan = new LinkedList<Channels>();

	public LinkedList<Channels> getChan() {
		return chan;
	}

	public void addChan(Channels C) {
		chan.add(C);
	}

	public void removeChan(Channels C) {
		chan.remove(C);
	}

}
