package brelaz;

import java.util.Iterator;

public class BrelazAlgorithm {
	/* 
	 * The core of the Brélaz algorithm is written in this class.
	 * The class stores the graph and network to which the algorithm has to be applied.
	 */

	private Graph graph;

	public BrelazAlgorithm(Graph graph) {
		this.graph = graph;
	}

	public void assignChannel() throws Exception {
		/* 
		 * Assign a channel to a compatible device.
		 * If the device has any available channel the one assigned is the first of the list
		 * otherwise a random channel is assigned.
		 */
		Device device = graph.selectNode();
		if (!device.getChannelsList().isEmpty()) {
			Channel channel = device.getChannelsList().pop();
			device.setAssignedChannel(channel);
			removeChannel(device, channel);
			/*
			 *  The assigned channel has to be removed from the device's available channel list
			 *  and from every adjacent device's list.
			 */
		} else {
			device.setAssignedChannel(graph.getNetwork().getRandomChannel());
		}
		graph.assignedNode(device);
		/* 
		 * The device will be removed from the 'devices with no channel assigned' list.
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
		graph.update();
		/*
		 * If two adjacent nodes has the same channel assigned
		 * the link between the two has to be removed.
		 */
	}
}
