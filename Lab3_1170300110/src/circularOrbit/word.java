package circularOrbit;

public class word {
	private String str;
	
	public word(String str) {
		this.str = str;
		checkRep();
	}
	
	private void checkRep() {
		boolean flag = false;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z' ||
					str.charAt(i) >= 'A' && str.charAt(i) <='Z') {
				//do nothing
			}else {
				flag = true;
			}
		}
		assert (flag == false);
	}
	
	public String getWord() {
		return this.str;
	}
	
	@Override
	public boolean equals(Object o) {
		if( ((word) o).getWord().equals(this.str)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return str;
	}
	
}
