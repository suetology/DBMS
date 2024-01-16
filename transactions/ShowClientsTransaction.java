package transactions;

public class ShowClientsTransaction implements Transaction {
    private final DbContext db;

    public ShowClientsTransaction(DbContext db) {
        this.db = db;
    }

    @Override
    public void execute() {
        try {
            Table table = db.executeQuery("SELECT * FROM klientas;");
            System.out.println(table);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String getDescription() {
        return "Rodyti klientu sarasa";
    }
}
