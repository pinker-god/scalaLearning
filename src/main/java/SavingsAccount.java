public class SavingsAccount extends Acount implements MyLogger {
    public void withDraw(Double amount) {
        if (amount > balance) {
            err("Insufficient funds");
        } else {
            balance -= amount;
            info("withDraw money" + amount);
            warn("remained money" + balance);
        }
    }
    @Override
    public void log(String msg) {
        System.out.println(msg);
    }
}