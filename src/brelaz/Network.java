package brelaz;

import java.util.LinkedList;

public class Network {
	/*
	 * The Network contains the List that stores the Channels that are to be assigned to the Devices.
	 */

	public LinkedList<Channel> channelsList = new LinkedList<Channel>();

	public LinkedList<Channel> getChannelsList() {
		return channelsList;
	}

	public void addChannel(Channel channel) {
		channelsList.add(channel);
	}

	public void removeChannel(Channel channel) {
		channelsList.remove(channel);
	}

	public Channel getRandomChannel() {
		/*
		 * This method allows the selection of a random Channel
		 * to be assigned to a Device that hasn't any available.
		 * It simply returns the Channel which index is a random number between zero and N-1.
		 */
		return channelsList.get((int) (Math.random() * channelsList.size()));
	}

}
