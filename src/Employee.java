public class Employee {
  private final String username;
  private final String password;

  // constructor
  Employee(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Boolean authenticate(String username, String password) {
    return this.username.equals(username)&&this.password.equals(password);
  }
}
