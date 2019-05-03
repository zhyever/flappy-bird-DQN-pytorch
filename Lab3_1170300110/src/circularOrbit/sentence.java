package circularOrbit;

public class sentence {
	private String str;
	
	public sentence(String str) {
		this.str = str;
		checkRep();
	}
	
	private void checkRep() {
		boolean flag = false;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z' ||
					str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'||
					str.charAt(i) >= '0' && str.charAt(i) <= '9'||
					str.charAt(i) == ' ') {
				//do nothing
			}else {
				flag = true;
			}
		}
		System.out.println(flag);
		assert (flag == false);
	}
	
	public String getSentence() {
		return this.str;
	}
	
	@Override
	public boolean equals(Object o) {
		if( ((sentence) o).getSentence().equals(this.str)) {
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
