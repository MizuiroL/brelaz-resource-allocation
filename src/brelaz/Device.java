package brelaz;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Device {

	private LinkedList<Channels> chan = new LinkedList<Channels>();

	private LinkedList<Arch> arch = new LinkedList<Arch>();

	private Channels assignedChan;

	private String name;

	public Device(String name) {
		this.name = name;
	}

	////////// Getters and Setters////////////

	public LinkedList<Channels> getChan() {
		return chan;
	}

	public void setChan(LinkedList<Channels> C) {
		chan = C;
	}

	public Channels getAssignedChan() {
		return assignedChan;
	}

	public void setAssignedChan(Channels assignedChan) {
		this.assignedChan = assignedChan;
	}

	public LinkedList<Arch> getArch() {
		return arch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	////////////////////////////////////////

	public void addArch(Arch A) throws Exception {
		arch.add(A);
	}

	public void removeArch(Arch A) throws Exception {
		arch.remove(A);
	}

	public void addChan(Channels C) throws Exception {
		chan.add(C);
	}

	public void removeChan(Channels C) throws Exception {
		chan.remove(C);
	}

	public int rank() {
		int n = 0;
		Stream<Arch> stream = arch.stream();
		n = (int) stream.count();
		return n;
	}

	public void updateTopology() throws Exception {
		if (getAssignedChan() != null) {
			Iterator<Arch> iterator = arch.iterator();
			while (iterator.hasNext()) {
				Arch arch = iterator.next();
				if (arch.getB().getAssignedChan() != null && arch.getA().getAssignedChan() != null
						&& arch.getA().getAssignedChan().equals(arch.getB().getAssignedChan())) {
					this.removeArch(arch);
				}
			}

		}
	}

	public void printDevice() {
		System.out.println("Node: " + name);
		System.out.print("Assigned Channel: ");
		if (assignedChan != null) {
			assignedChan.printChannels();
		} else {
			System.out.println("There is no assigned channel");
		}
		Iterator<Arch> iterator = arch.iterator();
		while (iterator.hasNext()) {
			iterator.next().printArch();
		}
	}

}
