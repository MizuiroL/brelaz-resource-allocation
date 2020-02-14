package brelaz;

public class Arch {
	/*
	 * Class representing the undirected link in the graph.
	 * It is defined by the two nodes (or Devices) that the arch links.
	 */

	private Device A;
	private Device B;

	public Arch(Device A, Device B) {
		this.A = A;
		this.B = B;
	}

	public Device getA() {
		return A;
	}

	public Device getB() {
		return B;
	}

	public void printArch() {
		System.out.println("The arch connects the following nodes: " + A.getName() + "->" + B.getName());
	}
	
	/*public String toString() {
		return ("The arch connects the following nodes: " + A.getName() + "->" + B.getName());
	}*/

}
