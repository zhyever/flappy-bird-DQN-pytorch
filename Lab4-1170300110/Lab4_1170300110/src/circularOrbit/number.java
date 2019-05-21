package circularOrbit;

public class number {
	private double num;
	private String str;
	
	public number(double num) {
		this.num = num;
		if(num <= 10000) {
			str = String.valueOf(num);
		}else {
			str = String.valueOf(num);
			int length = 0;
			for(double div = num; div >= 1; div = div / 10) {
				length++;
			}
			System.out.println(length);
			StringBuffer s = new StringBuffer();
			s.append(str.charAt(0));
			s.append(".");
			String str1 = str.substring(1);
			if(str1.contains(".")) {
				String[] strList = str1.split("\\.");
				for(int i = 0; i < strList.length; i++) {
					//处理最后一位
					if(strList[i].equals("0")) {
						s.append("0");
					}else {
						s.append(strList[i]);
					}
				}
			}else {
				if(str1.isEmpty()) {
					s.append("0");
				}else {
					s.append(str1);
				}
			}
			while(true) {
				if(s.charAt(s.length() - 1) =='0') {
					s.deleteCharAt(s.length() - 1);
				}else {
					if(s.charAt(s.length() - 1) =='.') {
						s.deleteCharAt(s.length() - 1);
					}
					break;
				}
			}
			s.append("e");
			s.append(String.valueOf(length - 1));
			str = s.toString();
		}
	}
	
	public number(String s) {
		String[] strList = s.split("e");
		this.num = Double.parseDouble(strList[0]) * Math.pow(10, Integer.parseInt(strList[1]));
	}
	
	public double getNum() {
		return num;
	}
	
	public String getStr() {
		return str;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if( ((number) o).getStr().equals(this.str)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return str;
	}
}
