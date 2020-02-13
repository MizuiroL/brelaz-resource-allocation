package brelaz;

import java.util.Iterator;

public class BrelazAlgorithm {

	private Graph graph;
	private Network network;

	public BrelazAlgorithm(Graph graph, Network network) {
		this.graph = graph;
		this.network = network;
	}

	public Device selectNode() {
		Iterator<Device> iterator = graph.getUnassignedNodes().iterator();
		Device dev1 = iterator.next();
		int max = dev1.rank();
		Device dev2 = null;
		while (iterator.hasNext()) {
			dev2 = iterator.next();
			if (max < dev2.rank()) {
				dev1 = dev2;
				max = dev2.rank();
			}
		}
		return dev1;
	}

	public void assignChannel() throws Exception {
		Device device = selectNode();
		if (!device.getChan().isEmpty()) {
			Channel channel = device.getChan().pop();
			device.setAssignedChan(channel);
			removeChannel(device, channel);
			graph.assignedNode(device);
			graph.update();
		} else {
			device.setAssignedChan(network.getChannel().pop()); // Random
			graph.assignedNode(device);
			graph.update();
		}

	}

	public void removeChannel(Device device, Channel channel) throws Exception {
		Iterator<Arch> iterator = device.getArch().iterator();
		while (iterator.hasNext()) {
			Arch arch = iterator.next();
			arch.getA().removeChan(channel);
			arch.getB().removeChan(channel);
		}
	}

	public boolean endAlgorithm() {
		return graph.getUnassignedNodes().isEmpty();
	}

	public void startAlgorithm() throws Exception {
		while (!endAlgorithm()) {
			assignChannel();
		}
	}
}
