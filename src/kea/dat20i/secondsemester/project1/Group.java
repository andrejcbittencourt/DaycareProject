package kea.dat20i.secondsemester.project1;

import java.util.ArrayList;

public class Group {
  private String name;
  private ArrayList<AccessControl> accessControlList;
  private ArrayList<String> employees;

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
  public Permission getPermission(String document, String employeeUsername) {
    for(AccessControl accessControl: this.accessControlList) {
      if(accessControl.getDocument().equals(document))
        for(String employee: this.employees) {
          if(employee.equals(employeeUsername))
            return accessControl.getPermission();
        }
    }
    return Permission.NONE;
  }
  public Boolean isInGroup(String employeeUsername) {
    for(String employee: this.employees) {
      if(employee.equals(employeeUsername))
        return true;
    }
    return false;
  }
}
