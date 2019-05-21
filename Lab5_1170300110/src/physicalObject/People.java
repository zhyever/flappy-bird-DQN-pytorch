package physicalobject;

import circularorbits.Label;

public class People extends PhysicalObject {
  /**Serializable.
   * 
   */
  private static final long serialVersionUID = 1L;
  private Label name;
  private int age;
  private char gender;

  public People(Label name, int age, char gender) {
    this.setName(name);
    this.setAge(age);
    this.setGender(gender);
  }

  public Label getName() {
    return name;
  }

  private void setName(Label name) {
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
    if (o == null) {
      return false;
    }
    if (((People) o).name.equals(this.name)) {
      return true;
    } else {
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
