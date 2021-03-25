package kea.dat20i.secondsemester.project1;

import java.util.ArrayList;

public class Group {
  private String name;
  ArrayList<AccessControl> accessControlList;
  ArrayList<String> employees;

  Group(String name) {
    this.name = name;
    this.accessControlList = new ArrayList<>();
    this.employees = new ArrayList<>();
  }
  public void addEmployee(String employeeUsername) {
    this.employees.add(employeeUsername);
  }
  public void addAccessControl(String document, Permission permission) {
    this.accessControlList.add(new AccessControl(document, permission));
  }
  public Permission getPermission(String document) {
    for(AccessControl accessControl: accessControlList) {
      if(accessControl.getDocument().equals(document))
        return accessControl.getPermission();
    }
    return Permission.NONE;
  }
  public Boolean isInGroup(String employeeUsername) {
    for(String employee: employees) {
      if(employee.equals(employeeUsername))
        return true;
    }
    return false;
  }
}
