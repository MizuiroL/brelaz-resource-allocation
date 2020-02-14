package brelaz;

import java.util.Iterator;
import java.util.LinkedList;

public class Device {
	/*
	 * The device is the object of the network that uses channels to communicate.
	 * It is defined by a name, the arches that connect it to other Devices, 
	 * the list of channels that can be assigned to it and the Channel assigned to it.
	 */

	private String name;
	private LinkedList<Arch> archesList = new LinkedList<Arch>();
	private LinkedList<Channel> availableChannels = new LinkedList<Channel>();
	private Channel assignedChannel;

	public Device(String name) {
		this.name = name;
	}

	////////// Getters and Setters////////////

	public LinkedList<Channel> getChannelsList() {
		return availableChannels;
	}

	public void setChannelsList(LinkedList<Channel> channelsList) {
		this.availableChannels = channelsList;
	}

	public Channel getAssignedChannel() {
		return assignedChannel;
	}

	public void setAssignedChannel(Channel assignedChannel) {
		this.assignedChannel = assignedChannel;
	}

	public LinkedList<Arch> getArchesList() {
		return archesList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	////////////////////////////////////////

	public void addArch(Arch a) throws Exception {
		this.archesList.add(a);
	}

	public void removeArch(Arch a) throws Exception {
		this.archesList.remove(a);
	}

	public void addChannel(Channel channel) throws Exception {
		this.availableChannels.add(channel);
	}

	public void removeChannel(Channel channel) throws Exception {
		this.availableChannels.remove(channel);
	}

	public int rank() {
		/*
		 * Returns the rank of the node, simply by counting how many arches it has
		 * (and therefore how many Devices it is connected with)
		 */
		return (int) archesList.stream().count();
	}
	
	public LinkedList<Arch> updateAssistant() {
		/*
		 * 
		 */
		LinkedList<Arch> buffer = new LinkedList<Arch>();
		Iterator<Arch> iterator = archesList.iterator();
		Arch arch = null;
		while (iterator.hasNext()) {
			arch = iterator.next();
			if (arch.getB().getAssignedChannel() != null && arch.getA().getAssignedChannel() != null
					&& arch.getA().getAssignedChannel().equals(arch.getB().getAssignedChannel())) {
				buffer.add(arch);
			}
		}

		return buffer;
	}

	public void updateTopology() throws Exception {
		/*
		 * 
		 */
		if (getAssignedChannel() != null && !archesList.isEmpty()) {
			LinkedList<Arch> buffer = updateAssistant();
			Iterator<Arch> iterator = buffer.iterator();
			while (iterator.hasNext()) {
				this.removeArch(iterator.next());
			}
		}
	}

	/*public void updateTopology() throws Exception {
		/*
		 * When a Channel is assigned to a Device the topology needs to be updated.
		 * If the adjacent devices have a different assigned channel the arch connecting the two nodes has to be deleted.
		 *
		if (getAssignedChannel() != null && !archesList.isEmpty()) {
			Iterator<Arch> iterator = archesList.iterator();
			// The channel of the device is compared to each of the adjacent devices' assigned channel.
			while (iterator.hasNext()) {
				Arch archPointer = iterator.next();
				Device deviceA = archPointer.getA(), deviceB = archPointer.getB();
				if (deviceB.getAssignedChannel() != null && deviceA.getAssignedChannel() != null
						/*
						 *  The comparison of the two devices' assigned channels is only done if
						 *  both of the devices actually have assigned channels
						 *
						&& deviceA.getAssignedChannel().equals(deviceB.getAssignedChannel())) {
					this.removeArch(archPointer);
				}
			}
		}
	}*/

	public void printDevice() {
		/*
		 * Prints on console all of the device information:
		 * its name;
		 * the optional assigned channel;
		 * the arches that connect it to other devices.
		 */
		System.out.println("Node: " + name);
		
		System.out.print("Assigned Channel: ");
		if (assignedChannel != null) {
			assignedChannel.printChannel();
		} else {
			System.out.println("There is no assigned channel");
		}
		
		Iterator<Arch> iterator = archesList.iterator();
		while (iterator.hasNext()) {
			iterator.next().printArch();
		}
	}

}
