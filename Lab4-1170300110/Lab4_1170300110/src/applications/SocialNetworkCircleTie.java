package applications;

import circularOrbit.label;

public class SocialNetworkCircleTie {
	private label name1;
	private label name2;
	private double close;
	public SocialNetworkCircleTie(label name1, label name2, double close) {
		this.name1 = name1;
		this.name2 = name2;
		this.close = close;
	}
	
	public label getName1() {
		return name1;
	}
	public void setName1(label name1) {
		this.name1 = name1;
	}
	public label getName2() {
		return name2;
	}
	public void setName2(label name2) {
		this.name2 = name2;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if( ((SocialNetworkCircleTie) o).name1.equals(this.name1) &&
				((SocialNetworkCircleTie) o).name2.equals(this.name2)) {
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
}
