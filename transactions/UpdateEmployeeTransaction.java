package transactions;

import java.util.Scanner;

public class UpdateEmployeeTransaction implements Transaction {
    private final Scanner scanner;
    private final DbContext db;

    public UpdateEmployeeTransaction(Scanner scanner, DbContext db) {
        this.scanner = scanner;
        this.db = db;
    }

    @Override
    public void execute() {
        try {
            Table table = db.executeQuery("SELECT * FROM darbuotojas;");
            System.out.println(table);

            System.out.println("Iveskite darbuotojo asmens koda kuriuo informacija norite atnaujinti:");
            String personalCode = scanner.nextLine();	
            
            System.out.println("Iveskite nauja informacija formatu: atributas1 = 'nauja_reiksme1', atributas2 = 'nauja_reiksme2, ...'");
            String updates = scanner.nextLine();

            int result = db.executeMutation(String.format(
                    "UPDATE darbuotojas SET %s WHERE asmens_kodas = '%s'", updates, personalCode));		

            if (result == 0)
                System.out.println("Atnaujinimo klaida");
            else
                System.out.println("Atlikta");
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String getDescription() {
        return "Atnaujinti darbuotojo informacija";
    }
}
