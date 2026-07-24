import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<String> users = new ArrayList<>();

    public void addUser(String user) {
        users.add(user);
    }

    public int getUserCount() {
        return users.size();
    }

    public void clearUsers() {
        users.clear();
    }
}