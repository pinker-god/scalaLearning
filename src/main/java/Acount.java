import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acount {
    private int id;
    private Double initialBalance = 0.0;
    double balance = initialBalance;

    public void deposit(Double amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        return String.format("Acount\tid=%d\tbalance=%f", id, balance);
    }
}