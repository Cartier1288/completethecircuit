
//Class which acts as an index for a particular component; holding the component's array index, count, and value.
public class ComponentIndex {
	public ComponentIndex(int index) { //Constructor taking just the index
		this.index = index;
	}
	
	public ComponentIndex(int index, int count) { //Constructor taking the index and count
		this.index = index;
		this.count = count;
	}
	
	public ComponentIndex(int index, int count, double value) { //Constructor taking the index, count, and value.
		this.index = index;
		this.count = count;
		this.value = value;
	}
	
	public ComponentIndex(int index, double value) { //Constructor taking the index and value
		this.index = index;
		this.value = value;
	}
	
	//Default component values
	public int index = -1;
	public int count = -1; 
	public double value = -1;
}
