package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.LinkedList;

public class DbContext implements AutoCloseable {
    private Connection connection;

    public DbContext(String url, String user, String password) {
        loadDriver();
        connect(url, user, password);
    }

    public Table executeQuery(String query) throws SQLException {
	    Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(query);
        ResultSetMetaData metaData = set.getMetaData();
        int columnCount = metaData.getColumnCount();
        Table table = new Table();

	    int skipIndex = -1;	

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
                    
            if (columnName.equalsIgnoreCase("id"))
                skipIndex = i;
            else
                table.addColumn(columnName);
	    }

        while (set.next()) {
            List<String> row = new LinkedList<>();

            for (int i = 1; i <= columnCount; i++)
                if (i != skipIndex)
                    row.add(set.getString(i));

            table.addRow(row);
        }

        set.close();
        statement.close();

        return table;
	}


    public int executeMutation(String mutationQuery) throws SQLException {
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(mutationQuery);

        statement.close();
        
        return result;
    }

    @Override
    public void close() throws Exception {
        disconnect();
    }

    private void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Couldn't find driver class");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private void connect(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Couldn't connect to database");
            ex.printStackTrace();
            return;
        }
    }

    private void disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Can not close connection");
            ex.printStackTrace();
        }
    }   
}
