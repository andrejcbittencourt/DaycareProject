package kea.dat20i.secondsemester.project1;

import java.util.ArrayList;

public class Parent {
  private String name;
  private String phoneNumber;
  private String address;
  private ArrayList<Child> children;

  Parent(String name, String phoneNumber, String address) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.address = address;
    children = new ArrayList<>();
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void addChild(String name, int age) {
    children.add(new Child(name, age));
  }

  public void setChildName(int index, String name) {
    this.children.get(index).setName(name);
  }

  public void setChildAge(int index, int age) {
    this.children.get(index).setAge(age);
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
