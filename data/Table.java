package data;

import java.util.List;
import java.util.LinkedList;

public class Table {
    List<String> columns = new LinkedList<>();
    List<List<String>> rows = new LinkedList<>();

    public void addColumn(String columnName) {
        columns.add(columnName);
    }

    public void addRow(List<String> row) {
        rows.add(row);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String column : columns) {
		    String finalStr = column.length() >= 19  ? (column.substring(0, 15) + "...") : column;
		    sb.append(String.format("%-19s", finalStr));
	    }

        for (List<String> row : rows) {
            sb.append("\n");
            
            for (String value : row) {
		        String finalStr = value.length() >= 19  ? (value.substring(0, 15) + "...") : value;
                sb.append(String.format("%-19s", finalStr));
		    }
        }

        return sb.toString();
    }
}
