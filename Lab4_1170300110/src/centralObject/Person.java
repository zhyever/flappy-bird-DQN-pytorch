package centralObject;
import circularOrbit.label;

public class Person {
	private label name;
	private int age;
	private char gender;
	
	//RI:
	//合法的name age gender
	//AF:
	//轨道系统中的中心物体类 for app5
	//含有名字、年龄、性别属性
	//防止REP暴露:
	//使用private属性
	//set方法设置为private
	
	public Person(label name, int age, char gender) {
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
	
}
