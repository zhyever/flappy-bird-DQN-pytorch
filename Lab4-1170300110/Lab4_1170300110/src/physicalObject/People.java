package physicalObject;

import circularOrbit.label;

public class People extends PhysicalObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private label name;
	private int age;
	private char gender;
	
	public People(label name, int age, char gender) {
		this.setName(name);
		this.setAge(age);
		this.setGender(gender);
	}

	public label getName() {
		return name;
	}

	private void setName(label name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	private void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	private void setGender(char gender) {
		this.gender = gender;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(((People) o).name.equals(this.name)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append(name.toString() + " ");
		s.append(String.valueOf(age) + " ");
		s.append(gender);
		return s.toString();
	}
}
