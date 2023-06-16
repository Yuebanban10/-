package User.User;

import User.User.User;

import java.util.List;

public interface IUsersMethod {
	void register();

	void writeFile(User user);

	void login();

	List<User> readFile(String fileName);
}
