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

	public void printGraph() {
		Iterator<Device> iterator = nodes.iterator();
		while (iterator.hasNext()) {
			iterator.next().printDevice();
		}
	}

}
