package kea.dat20i.secondsemester.project1;

public class Employee {
  private final String username;
  private final String password;
  private final Boolean admin;
  private final String name;

  // constructor
  Employee(String username, String password, Boolean admin, String name) {
    this.username = username;
    this.password = password;
    this.admin = admin;
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public String getUsername() {
    return this.username;
  }

  public Boolean is_admin() {
    return this.admin;
  }

  public Boolean authenticate(String username, String password) {
    return this.username.equals(username)&&this.password.equals(password);
  }
}
