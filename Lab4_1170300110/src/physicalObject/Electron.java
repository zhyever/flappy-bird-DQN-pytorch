package physicalObject;


public class Electron extends PhysicalObject{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	
	public Electron(int i) {
		setNum(i);
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if( ((Electron) o).getNum() == getNum()) {
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
		StringBuffer s = new StringBuffer();
		s.append(String.valueOf(this.getNum()));
		return s.toString();
	}
}
