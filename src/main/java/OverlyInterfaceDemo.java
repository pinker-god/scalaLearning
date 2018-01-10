/**
 * @author pinker on 2018/1/9
 */
public class OverlyInterfaceDemo {
    public static void main(String[] args) {

        SavingsAccount account = new SavingsAccount();
        account.deposit(1000.0);
        account.withDraw(300.0);
        account.withDraw(800.0);
    }
}
