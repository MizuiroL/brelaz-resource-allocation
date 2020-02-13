package brelaz;

class Channel {

	private String name;

	public Channel(String name) {
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

	public void printChannel() {
		System.out.println(name);
	}
	
	public boolean equals(Channel C) {
		return name.equalsIgnoreCase(C.getName());
	}
}
