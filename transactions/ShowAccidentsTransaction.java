package transactions;

public class ShowAccidentsTransaction implements Transaction {
    private final DbContext db;

    public ShowAccidentsTransaction(DbContext db) {
        this.db = db;
    }

    @Override
    public void execute() {
        try {
            Table table = db.executeQuery("SELECT * FROM eismo_ivykis;");
            System.out.println(table);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String getDescription() {
        return "Rodyti eismo ivykiu sarasa";
    }
}

