package circularOrbit;


public class label {
	private String str;
	
	
	public label(String str) {
		this.str = str;
		checkRep();
	}
	
	private void checkRep() {
		boolean flag = false;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z' ||
					str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'||
					str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				//do nothing
			}else {
				flag = true;
			}
		}
		assert (flag == false);
	}
	
	public String getLabel() {
		return this.str;
	}
	
	@Override
	public boolean equals(Object o) {
		if( ((label) o).getLabel().equals(this.str)) {
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
