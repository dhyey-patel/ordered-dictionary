
public class Record {
	Pair key;
	String data;
	
	public Record(Pair key, String data) {
		this.key = key;
		this.data = data;
	}
	
	public Pair getKey() {
		return key;
	}
	
	public String getData() {
		return data;
	}
}
