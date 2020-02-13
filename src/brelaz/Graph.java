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

	public void addNode(Device D) throws Exception {
		nodes.add(D);
		unassignedNodes.add(D);
	}

	public void removeNode(Device D) throws Exception {
		nodes.remove(D);
	}

	public void assignedNode(Device D) throws Exception {
		unassignedNodes.remove(D);
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
