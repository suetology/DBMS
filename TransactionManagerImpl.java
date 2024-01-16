import java.util.Map;

public class TransactionManagerImpl implements TransactionManager {
    private final Map<Integer, Transaction> transactions;

    public TransactionManagerImpl(Map<Integer, Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public void showTransactions() {
        System.out.println("Iveskite operacijos numeri:");

        transactions.forEach((n, t) -> {
            System.out.println(n + ". " + t.getDescription());
        });
    }

    @Override
    public void executeTransaction(int userChoice) throws Exception {
	    if (transactions.containsKey(userChoice)) 
            transactions.get(userChoice).execute();
        else  
            throw new Exception("Ivestas netinkamas numeris");
    }
}
