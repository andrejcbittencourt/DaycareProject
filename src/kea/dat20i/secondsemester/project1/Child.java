package kea.dat20i.secondsemester.project1;

public class Child {
  private String name;
  private int age;

  Child(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getAge() {
    return this.age;
  }
  public void setAge(int age) {
    this.age = age;
  }
}
