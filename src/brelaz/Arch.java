package brelaz;

public class Arch {

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

}
