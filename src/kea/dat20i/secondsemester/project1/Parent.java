package kea.dat20i.secondsemester.project1;

import java.util.ArrayList;

public class Parent {
  private String name;
  private String phoneNumber;
  private String address;
  ArrayList<Child> children = null;

  Parent(String name, String phoneNumber, String address) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.address = address;
    children = new ArrayList<>();
  }

  public void addChild(String name, int age) {
    children.add(new Child(name, age));
  }

  public ArrayList<String> getChildrenName() {
    ArrayList<String> result = new ArrayList<>();
    for(Child child: children) {
      result.add(child.getName());
    }
    return result;
  }

  public int getChildAge(String name) {
    for(Child child: children) {
      if(child.getName().equals(name))
        return child.getAge();
    }
    return -1;
  }
}
