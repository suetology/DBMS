import java.util.Scanner;

public class Application {
    private final Scanner scanner; 
    private final TransactionManager transactionManager;

    public Application(Scanner scanner, TransactionManager transactionManager) {
        this.scanner = scanner;
        this.transactionManager = transactionManager;
    }

    public void run() {
	    boolean appRunning = true;

        while (appRunning) {
            transactionManager.showTransactions();

            if (!scanner.hasNextInt()) {
                System.out.println("Ivestas ne numeris");
                continue;
            }

            try {
                int n = scanner.nextInt();
                scanner.nextLine();
                transactionManager.executeTransaction(n);
            } catch (Exception ex) {
                System.out.println(ex);
            }

            System.out.println("Testi darba?");
            String choice = scanner.nextLine();

            if (!choice.equalsIgnoreCase("y"))
                appRunning = false;
        }
    }
}
