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
		if( ((Electron) o).getNum() == getNum()) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append(String.valueOf(this.getNum()));
		return s.toString();
	}
}
