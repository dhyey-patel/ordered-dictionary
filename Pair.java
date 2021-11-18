
public class Pair {
	private String word, type;
	public Pair(String word, String type) {
		this.word = word;
		this.type = type;
	}
	public String getWord() {
		return word;
	}
	public String getType() {
		return type;
	}
	public int compareTo (Pair k) {
		int compareWord, compareType;
		compareWord = word.compareTo(k.getWord());
		compareType = type.compareTo(k.getType());
		if (compareWord == 0) {
			if (compareType == 0) {
				return 0;
			}
			else if (compareType < 0) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else if (compareWord < 0) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
