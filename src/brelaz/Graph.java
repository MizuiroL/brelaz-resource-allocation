package brelaz;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

	private LinkedList<Device> nodes = new LinkedList<Device>();

	private LinkedList<Device> unassignedNodes = new LinkedList<Device>();

	public LinkedList<Device> getNodes() {
		return nodes;
	}

	public LinkedList<Device> getUnassignedNodes() {
		return unassignedNodes;
	}

	public void addNode(Device device) throws Exception {
		nodes.add(device);
		unassignedNodes.add(device);
	}

	public void removeNode(Device device) throws Exception {
		nodes.remove(device);
	}

	public void assignedNode(Device device) throws Exception {
		unassignedNodes.remove(device);
	}

	public void update() throws Exception {
		Iterator<Device> iterator = nodes.iterator();
		while (iterator.hasNext()) {
			iterator.next().updateTopology();
		}
	}

	public void printGraph() {
		Iterator<Device> iterator = nodes.iterator();
		while (iterator.hasNext()) {
			iterator.next().printDevice();
		}
	}

}
