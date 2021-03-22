package kea.dat20i.secondsemester.project1;

public class AccessControl {
  private String document;
  private Permission permission;

  AccessControl(String document, Permission permission) {
    this.document = document;
    this.permission = permission;
  }

  public Permission getPermission() {
    return this.permission;
  }
  public String getDocument() {
    return this.document;
  }
}
