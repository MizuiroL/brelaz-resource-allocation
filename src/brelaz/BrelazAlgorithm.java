package brelaz;

import java.util.Iterator;

public class BrelazAlgorithm {

	private Graph G;
	private Network N;

	public BrelazAlgorithm(Graph G, Network N) {
		this.G = G;
		this.N = N;
	}

	public Device selectNode() {
		Iterator<Device> iterator = G.getUnassignedNodes().iterator();
		Device D = iterator.next();
		int max = D.rank();
		Device E = null;
		while (iterator.hasNext()) {
			E = iterator.next();
			if (max < E.rank()) {
				D = E;
				max = E.rank();
			}
		}
		return D;
	}

	public void assignChannel() throws Exception {
		Device D = selectNode();
		if (!D.getChan().isEmpty()) {
			Channels C = D.getChan().pop();
			D.setAssignedChan(C);
			removeChan(D, C);
			G.assignedNode(D);
			G.update();
		} else {
			D.setAssignedChan(N.getChan().pop()); // Random
			G.assignedNode(D);
			G.update();
		}

	}

	public void removeChan(Device D, Channels C) throws Exception {
		Iterator<Arch> iterator = D.getArch().iterator();
		while (iterator.hasNext()) {
			Arch A = iterator.next();
			A.getA().removeChan(C);
			A.getB().removeChan(C);
		}
	}

	public boolean endAlgorithm() {
		boolean b = false;
		if (G.getUnassignedNodes().isEmpty()) {
			b = true;
		}
		return b;
	}

	public void startAlgorithm() throws Exception {
		while (!endAlgorithm()) {
			assignChannel();
		}
	}
}
