package brelaz;

class Channels {

	private String name;

	public Channels(String name) {
		this.name = name;
	}

///////////Getters and Setters///////////////
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/////////////////////////////////////////////

	public void printChannels() {
		System.out.println(name);
	}

	public boolean equals(Channels C) {
		boolean b = false;
		if (name.equalsIgnoreCase(C.getName())) {
			b = true;
		}
		return b;
	}
}
