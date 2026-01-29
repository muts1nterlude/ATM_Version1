package persistence;

import interfaces.Persistence;
import core.*;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler implements Persistence {

    private static final String ACCOUNT_FILE = "account.json";
    private static final String ATM_FILE = "atm.json";

    @Override
    public Account loadAccount(String card) {
        try {
            JSONObject obj = new JSONObject(Files.readString(Paths.get(ACCOUNT_FILE)));
            return new Account(
                    obj.getString("card"),
                    obj.getString("pin"),
                    obj.getDouble("balance")
            );
        } catch (Exception e) {
            return new Account(card, "1234", 500);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("card", account.getCardNumber());
            obj.put("pin", account.getPin());
            obj.put("balance", account.getBalance());
            Files.writeString(Paths.get(ACCOUNT_FILE), obj.toString(2));
        } catch (Exception ignored) {}
    }

    @Override
    public ATMState loadATMState() {
        try {
            JSONObject obj = new JSONObject(Files.readString(Paths.get(ATM_FILE)));
            // REMOVED: Loading firmware version and paper tank (V2 features)
            return new ATMState(obj.getDouble("cash"));
        } catch (Exception e) {
            // REMOVED: Paper tank initialization (V2 feature)
            return new ATMState(2000);
        }
    }

    @Override
    public void saveATMState(ATMState state) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("cash", state.getCashAvailable());
            // REMOVED: Saving firmware version and paper tank (V2 features)
            Files.writeString(Paths.get(ATM_FILE), obj.toString(2));
        } catch (Exception ignored) {}
    }
}
