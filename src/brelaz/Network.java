package brelaz;

import java.util.LinkedList;

public class Network {
	/*
	 * The Network contains the List that stores the Channels that are to be assigned to the Devices.
	 */

	public LinkedList<Channel> channelsList;
	
	public Network(LinkedList<Channel> channelsList) {
		this.channelsList = channelsList;
	}

	public LinkedList<Channel> getChannelsList() {
		return channelsList;
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
