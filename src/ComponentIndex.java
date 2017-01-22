
public class ComponentIndex {
	public ComponentIndex(int index) {
		this.index = index;
	}
	
	public ComponentIndex(int index, int count) {
		this.index = index;
		this.count = count;
	}
	
	public ComponentIndex(int index, int count, double value) {
		this.index = index;
		this.count = count;
		this.value = value;
	}
	
	public ComponentIndex(int index, double value) {
		this.index = index;
		this.value = value;
	}
	
	public int index = -1;
	public int count = 0; 
	public double value = 0;
}
