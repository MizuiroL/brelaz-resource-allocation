package brelaz;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Device {

	private LinkedList<Channel> channel = new LinkedList<Channel>();

	private LinkedList<Arch> arch = new LinkedList<Arch>();

	private Channel assignedChannel;

	private String name;

	public Device(String name) {
		this.name = name;
	}

	////////// Getters and Setters////////////

	public LinkedList<Channel> getChan() {
		return channel;
	}

	public void setChan(LinkedList<Channel> C) {
		channel = C;
	}

	public Channel getAssignedChannel() {
		return assignedChannel;
	}

	public void setAssignedChan(Channel assignedChannel) {
		this.assignedChannel = assignedChannel;
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

	public void addArch(Arch a) throws Exception {
		this.arch.add(a);
	}

	public void removeArch(Arch a) throws Exception {
		this.arch.remove(a);
	}

	public void addChan(Channel ch) throws Exception {
		this.channel.add(ch);
	}

	public void removeChan(Channel ch) throws Exception {
		this.channel.remove(ch);
	}

	public int rank() {
		int n = 0;
		Stream<Arch> stream = arch.stream();
		n = (int) stream.count();
		return n;
	}

	public void updateTopology() throws Exception {
		if (getAssignedChannel() != null) {
			Iterator<Arch> iterator = arch.iterator();
			while (iterator.hasNext()) {
				Arch arch = iterator.next();
				if (arch.getB().getAssignedChannel() != null && arch.getA().getAssignedChannel() != null
						&& arch.getA().getAssignedChannel().equals(arch.getB().getAssignedChannel())) {
					this.removeArch(arch);
				}
			}

		}
	}

	public void printDevice() {
		System.out.println("Node: " + name);
		System.out.print("Assigned Channel: ");
		if (assignedChannel != null) {
			assignedChannel.printChannel();
		} else {
			System.out.println("There is no assigned channel");
		}
		Iterator<Arch> iterator = arch.iterator();
		while (iterator.hasNext()) {
			iterator.next().printArch();
		}
	}

}
