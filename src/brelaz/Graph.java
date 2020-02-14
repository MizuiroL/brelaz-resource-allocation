package brelaz;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
	/* 
	 * This class stores the nodes (Device) and links (Arch) that connects them.
	 * It has a list of devices without assigned channels to simplify the execution of the Brélaz algorithm.
	 */

	private LinkedList<Device> nodes = new LinkedList<Device>();

	private LinkedList<Device> unassignedNodes = new LinkedList<Device>();
	
	private Network network;
	
	public Graph(Network network) {
		this.network = network;
	}

	public LinkedList<Device> getNodes() {
		return nodes;
	}

	public LinkedList<Device> getUnassignedNodes() {
		return unassignedNodes;
	}
	
	public Network getNetwork() {
		return network;
	}

	public void addNode(Device device) throws Exception {
		nodes.add(device);
		unassignedNodes.add(device);
		uploadChannels(device);
	}

	public void removeNode(Device device) throws Exception {
		nodes.remove(device);
	}

	public void assignedNode(Device device) throws Exception {
		unassignedNodes.remove(device);
	}
	
	public void update() throws Exception {
		/*
		 * Every time a channel is assigned to a device
		 * the link between two devices has to be removed
		 * if those two devices have been assigned the same channel.
		 */
		Iterator<Device> iterator = nodes.iterator();
		while (iterator.hasNext()) {
			iterator.next().updateTopology();
		}
	}
	
	public void uploadChannels(Device device) throws Exception {
		/*
		 * 
		 */
		Iterator<Channel> iterator = network.getChannelsList().iterator();
		while (iterator.hasNext()) {
			device.addChannel(iterator.next());
		}
	}

	public void printGraph() {
		Iterator<Device> iterator = nodes.iterator();
		while (iterator.hasNext()) {
			iterator.next().printDevice();
		}
	}
	
	public Device selectNode() {
		/*
		 * The algorithm will choose the device with the highest rank (highest number of adjacent nodes).
		 * Only devices without an assigned channel are considered.
		 */
		Iterator<Device> iterator = getUnassignedNodes().iterator();
		Device chosenDevice = iterator.next();
		int maxRank = chosenDevice.rank();
		Device deviceToCompare = null;
		
		while (iterator.hasNext()) {
			deviceToCompare = iterator.next();
			/*
			 * We iterate every node storing the temporary 'best'.
			 * If a node has an higher rank it becomes the temporary best.
			 * At the end of the iteration the node with the highest rank is returned.
			 */
			if (maxRank < deviceToCompare.rank()) {
				chosenDevice = deviceToCompare;
				maxRank = deviceToCompare.rank();
			}
		}
		return chosenDevice;
	}

}
