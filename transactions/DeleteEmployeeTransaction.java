package transactions;

import java.util.Scanner;

public class DeleteEmployeeTransaction implements Transaction {
    private final Scanner scanner; 
    private final DbContext db;

    public DeleteEmployeeTransaction(Scanner scanner, DbContext db) {
        this.scanner = scanner;
        this.db = db;
    }

    @Override
    public void execute() {
        try {
            Table table = db.executeQuery("SELECT * FROM darbuotojas;");		
            System.out.println(table);
            System.out.println("Iveskite darbuotojo asmens koda kuriuo duomenis norite istrinti:");
            String personalCode = scanner.nextLine();

            db.executeMutation(String.format(
		        "DELETE FROM darbuotojas WHERE asmens_kodas = '%s';", personalCode));
            System.out.println("Atlikta");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String getDescription() {
        return "Istrinti darbuotojo duomenis";
    }
}
