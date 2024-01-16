import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username = Configuration.getProperty("username");
        String password = Configuration.getProperty("password");

        try (DbContext db = new DbContext("jdbc:postgresql://pgsql3.mif/studentu", username, password)) {
            Map<Integer, Transaction> transactions = new TreeMap<>(Map.ofEntries(
                Map.entry(1, new ShowEmployeesTransaction(db)),
                Map.entry(2, new ShowClientsTransaction(db)),
                Map.entry(3, new ShowVehiclesTransaction(db)),
                Map.entry(4, new ShowAccidentsTransaction(db)),
                Map.entry(5, new RegisterEmployeeTransaction(scanner, db)),
                Map.entry(6, new UpdateEmployeeTransaction(scanner, db)),
                Map.entry(7, new DeleteEmployeeTransaction(scanner, db)),
                Map.entry(8, new CalculateClientDamageTransaction(scanner, db))));
            TransactionManager transactionManager = new TransactionManagerImpl(transactions);
            Application app = new Application(scanner, transactionManager);
            app.run();
        } catch (Exception ex) {
            System.out.println(ex);
	    } finally {
            if (scanner != null)
                scanner.close();
        }
    }
}
