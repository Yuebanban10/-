package User.UserUtils;

import User.User.IUsersMethod;
import User.User.User;
import User.User.UsersMethod;

import java.util.Scanner;

public class UserUtils implements IUserUtils{
	static UsersMethod usersMethod = new UsersMethod();

	@Override
	public void userMenu() {
		System.out.println("-------小学口算软件--------");
		System.out.println("|                       |");
		System.out.println("|         1.注册        	|");
		System.out.println("|         2.登录        	|");
		System.out.println("|         3.退出			|");
		System.out.println("|                       |");
		System.out.println("-------------------------");
		System.out.print("请输入你的选择:");
		Scanner s = new Scanner(System.in);
		int choice = s.nextInt();
		switch (choice){
			case 1:
				usersMethod.register();
				break;
			case 2:
				usersMethod.login();
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("输入错误！系统自动退出");
				System.exit(0);
		}
	}
}
