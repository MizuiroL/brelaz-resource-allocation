package brelaz;

import java.util.Iterator;

public class BrelazAlgorithm {
	/* 
	 * The core of the Br�laz algorithm is written in this class.
	 * The class stores the graph and network to which the algorithm has to be applied.
	 */

	private Graph graph;
	private Network network;

	public BrelazAlgorithm(Graph graph, Network network) {
		this.graph = graph;
		this.network = network;
	}
	
	public Device selectNode() {
		/*
		 * The algorithm will choose the device with the highest rank (highest number of adjacent nodes).
		 * Only devices without an assigned channel are considered.
		 */
		Iterator<Device> iterator = graph.getUnassignedNodes().iterator();
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

	public void assignChannel() throws Exception {
		/* 
		 * Assign a channel to a compatible device.
		 * If the device has any available channel the one assigned is the first of the list
		 * otherwise a random channel is assigned.
		 */
		Device device = selectNode();
		if (!device.getChannelsList().isEmpty()) {
			Channel channel = device.getChannelsList().pop();
			device.setAssignedChannel(channel);
			removeChannel(device, channel);
			/*
			 *  The assigned channel has to be removed from the device's available channel list
			 *  and from every adjacent device's list.
			 */
		} else {
			device.setAssignedChannel(network.getChannelsList().pop()); // Random
		}

		graph.assignedNode(device);
		/* 
		 * The device will be removed from the 'devices with no channel assigned' list.
		 */
		graph.update();
		/*
		 * If two adjacent nodes has the same channel assigned
		 * the link between the two has to be removed.
		 */

	}

	public void removeChannel(Device device, Channel channel) throws Exception {
		/*
		 * Remove a channel from the list of available channels of all adjacent devices.
		 */
		Iterator<Arch> iterator = device.getArchesList().iterator();
		while (iterator.hasNext()) {
			Arch arch = iterator.next();
			arch.getA().removeChannel(channel);
			arch.getB().removeChannel(channel);
		}
	}

	public boolean endAlgorithm() {
		/*
		 * The list is empty when every device has a channel assigned,
		 * so the algorithm may end.
		 */
		return graph.getUnassignedNodes().isEmpty();
	}

	public void startAlgorithm() throws Exception {
		/*
		 * If there are no more nodes without an assigned channel
		 * the algorithm has terminated.
		 */
		while (!endAlgorithm()) {
			assignChannel();
		}
	}
}
