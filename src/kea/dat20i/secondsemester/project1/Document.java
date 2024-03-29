package kea.dat20i.secondsemester.project1;

import java.util.ArrayList;

public class Document {
  private final String name;
  private final ArrayList<String> columns;
  private ArrayList<String> data;

  Document(String name, ArrayList<String> columns) {
    this.name = name;
    this.columns = columns;
    this.data = new ArrayList<>();
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
  public String getName() {
    return this.name;
  }

  private ArrayList<String> formatWrap(int cellSize, int cellMargin, int documentSize, ArrayList<String> cells, Boolean vertical) {
    ArrayList<String> formattedCells = new ArrayList<>();
    int columnCount = 0;
    int rowCount = 0;
    int formattedCellsSize = 0;
    cellSize = Math.max(cellSize, 1);
    cellMargin = Math.max(cellMargin, 1);
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
        StringBuilder spacing = new StringBuilder();
        spacing.append(" ".repeat(Math.max(0, ((columnCount * cellSize) - formattedCells.get(currentRow).length() + ((cellMargin + 3) * columnCount)))));
        formattedCells.set(currentRow, formattedCells.get(currentRow) + spacing.toString());
        StringBuilder text = new StringBuilder();
        for(int i=0; i<cellSize; i++) {
          try {
            text.append(cell.charAt(0));
            cell = cell.substring(1);
          } catch(Exception e) { // no more text
            break;
          }
        }
        formattedCells.set(currentRow, formattedCells.get(currentRow) + (vertical?(columnCount==0?rowCount+1:" "):columnCount+1) + "| " + text.toString());
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
    document.add("-".repeat(document.get(0).length()));
    // text wrap the data cells
    if(data.size()>0)
      document.addAll(formatWrap(cellSize, cellMargin, columns.size(), data, true));
    // display the document
    StringBuilder result = new StringBuilder();
    result.append("Document: ").append(this.name).append("\n");
    for(String row: document) {
      result.append(row).append("\n");
    }
    System.out.println(result.toString());
  }
}
