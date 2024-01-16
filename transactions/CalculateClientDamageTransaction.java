package transactions;

import java.util.Scanner;

public class CalculateClientDamageTransaction implements Transaction {
    private final Scanner scanner; 
	private final DbContext db;

    public CalculateClientDamageTransaction(Scanner scanner, DbContext db) {
        this.scanner = scanner;
        this.db = db;
    }

    @Override
    public void execute() {
	    try {
            Table table = db.executeQuery("SELECT * FROM klientas;");
            System.out.println(table);

            System.out.println("Iveskite kliento asmens koda kuriuo padaryta zala norite suskaiciuoti:");
            String personalCode = scanner.nextLine();

            Table result = db.executeQuery(String.format(
                "SELECT SUM(zala) AS \"Bendra zala\" 
                 FROM klientas, transporto_priemone, eismo_ivykis 
                 WHERE klientas.asmens_kodas = '%s' 
                    AND klientas.id = transporto_priemone.savininkas 
                    AND transporto_priemone.id = eismo_ivykis.automobilis 
                    AND eismo_ivykis.kaltas_vairuotojas is true;", personalCode));	
            
            System.out.println(result);
        } catch (Exception ex) {
                System.out.println(ex);
        }
    }

    @Override
    public String getDescription() {
        return "Skaiciuoti kliento padaryta zala";
    }
}
