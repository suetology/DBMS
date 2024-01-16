package transactions;

import java.util.Scanner;

public class RegisterEmployeeTransaction implements Transaction {
    private final Scanner scanner;
    private final DbContext db;

    public RegisterEmployeeTransaction(Scanner scanner, DbContext db) {
        this.scanner = scanner;
        this.db = db;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Iveskite darbuotojo asmens koda:");
            String personalCode = scanner.nextLine();

            System.out.println("Iveskite darbuotojo varda:");
            String name = scanner.nextLine();

            System.out.println("Iveskite darbuotojo pavarde:");
            String surname = scanner.nextLine();

            System.out.println("Iveskite darbuotojo isidarbinimo data formatu YYYY-MM-DD:");
            String employmentDate = scanner.nextLine();

            db.executeMutation(String.format(
                "INSERT INTO darbuotojas (asmens_kodas, vardas, pavarde, isidarbinimo_data) VALUES ('%s', '%s', '%s', '%s');", personalCode, name, surname, employmentDate));
        
		    System.out.println("Atlikta");
	    } catch (Exception ex) {
            System.out.println(ex);
        }
    }

	@Override
    public String getDescription() {
        return "Registruoti nauja darbuotoja";
    }
}
