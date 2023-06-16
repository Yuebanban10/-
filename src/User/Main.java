package User;

import User.User.IUsersMethod;
import User.User.UsersMethod;
import User.UserUtils.IUserUtils;
import User.UserUtils.UserUtils;

public class Main {
	static IUserUtils userUtils = new UserUtils();
	static IUsersMethod usersMethod = new UsersMethod();
	public static void main(String[] args) {
		userUtils.userMenu();
	}
}
