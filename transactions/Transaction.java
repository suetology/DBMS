package transactions;

public interface Transaction {
    void execute();
    String getDescription();
}
