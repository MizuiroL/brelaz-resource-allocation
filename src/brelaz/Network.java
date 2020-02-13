package brelaz;

import java.util.LinkedList;

public class Network {

	public LinkedList<Channel> channel = new LinkedList<Channel>();

	public LinkedList<Channel> getChannel() {
		return channel;
	}

	public void addChan(Channel ch) {
		channel.add(ch);
	}

	public void removeChan(Channel chC) {
		channel.remove(chC);
	}

	public Channel getRandomChannel() {
		return channel.get((int) (Math.random() * channel.size()));
	}

}
