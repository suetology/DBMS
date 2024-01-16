package transactions;

public class ShowEmployeesTransaction implements Transaction {
    private final DbContext db;

    public ShowEmployeesTransaction(DbContext db) {
        this.db = db;
    }

    @Override
    public void execute() {
        try {
            Table table = db.executeQuery("SELECT * FROM darbuotojas;");
            System.out.println(table);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String getDescription() {
        return "Rodyti darbuotoju sarasa";
    }
}
