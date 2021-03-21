package kea.dat20i.secondsemester.project1;

import java.util.ArrayList;

public class Document {
  private final String name;
  private final ArrayList<String> columns;
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

  private ArrayList<String> formatWrap(int cellSize, int cellMargin, int documentSize, ArrayList<String> cells, Boolean vertical) {
    ArrayList<String> formattedCells = new ArrayList<>();
    int columnCount = 0;
    int rowCount = 0;
    int formattedCellsSize = formattedCells.size();
    for(String cell: cells) {
      if(columnCount >= documentSize) { // if vertical
        columnCount = 0;
        rowCount++;
        formattedCellsSize = formattedCells.size();
      }
      int currentRow = formattedCellsSize;
      // wrap the text
      while(cell.length()>0) {
        if (formattedCells.size() < currentRow + 1) // add new row if doesn't exit
          formattedCells.add("");
        // add spacing accordingly
        String spacing = "";
        for (int i = 0; i < ((columnCount * cellSize) - formattedCells.get(currentRow).length() + (cellMargin * columnCount)); i++)
          spacing += " ";
        formattedCells.set(currentRow, formattedCells.get(currentRow) + spacing);
        String text = "";
        for(int i=0; i<cellSize; i++) {
          try {
            text += cell.charAt(0);
            cell = cell.substring(1);
          } catch(Exception e) { // no more text
            break;
          }
        }
        formattedCells.set(currentRow, formattedCells.get(currentRow) + (vertical?(columnCount==0?rowCount+1:" "):columnCount+1) + "| " + text);
        currentRow++;
      }
      columnCount++;
    }
    return formattedCells;
  }

  public void display(int cellSize, int cellMargin) {
    ArrayList<String> document = new ArrayList<>();
    ArrayList<String> columns = getColumns();
    ArrayList<String> data = getData();
    // text wrap the columns
    document.addAll(formatWrap(cellSize, cellMargin, columns.size(), columns, false));
    // add horizontal line
    document.add("");

    // text wrap the data cells
    document.addAll(formatWrap(cellSize, cellMargin, columns.size(), data, true));
    // display the document
    StringBuilder result = new StringBuilder();
    result.append("Document: ").append(this.name).append("\n");
    for(String row: document) {
      result.append(row).append("\n");
    }
    System.out.println(result);
  }
}
