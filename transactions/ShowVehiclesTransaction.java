package transactions;

public class ShowVehiclesTransaction implements Transaction {
    private final DbContext db;

    public ShowVehiclesTransaction(DbContext db) {
        this.db = db;
    }

    @Override
    public void execute() {
        try {
            Table table = db.executeQuery("SELECT * FROM transporto_priemone;");
            System.out.println(table);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String getDescription() {
        return "Rodyti transporto priemoniu sarasa";
    }
}
