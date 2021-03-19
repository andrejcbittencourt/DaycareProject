package kea.dat20i.secondsemester.project1;

import java.util.ArrayList;

public class Document {
  private String name;
  private ArrayList<String> columns;
  private ArrayList<String> data;

  Document(String name, ArrayList<String> columns) {
    this.name = name;
    this.columns = columns;
  }
  Document(String name, ArrayList<String> columns, ArrayList<String> data) {
    this.name = name;
    this.columns = columns;
    this.data = data;
  }
  public ArrayList<String> getData() {
    return this.data;
  }
  public void setData(ArrayList<String> data) {
    this.data = data;
  }
  public ArrayList<String> getColumns() {
    return this.columns;
  }
  public void display() {
    System.out.println("document");
  }
}
