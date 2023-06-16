package User.User;

import Question.MakeQuestion;
import User.User.User;
import User.UserUtils.IUserUtils;
import User.UserUtils.UserUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersMethod implements IUsersMethod {
	static UserUtils userUtils = new UserUtils();
	static MakeQuestion makeQuestion = new MakeQuestion();
	Scanner s = new Scanner(System.in);

	@Override
	public void register() {
		User user = new User();
		System.out.print("请输入账号:");
		user.setUserID(s.next());
		System.out.println();
		System.out.print("请输入密码:");
		user.setUserPassword(s.next());
		System.out.println("注册成功！");
		writeFile(user);
	}

	@Override
	public void writeFile(User user) {
		String fileName = "D:\\Java实验与大作业\\大作业\\用户数据集合.csv";
		try {
			File file = new File(fileName);
			boolean hasColumnNames = !file.exists();
			FileWriter writer = new FileWriter(fileName, true);
			if (hasColumnNames) {
				writer.append("账号,密码\n");
			}
			writer.append(user.getUserID() + "," + user.getUserPassword() + "\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void login() {
		List<User> userList = readFile("用户数据集合.csv");
		User loginUser = null;
		System.out.print("请输入账号:");
		String userID = s.next();
		System.out.print("请输入密码:");
		String password = s.next();
		System.out.println();
		boolean login = false;
		for (User user : userList) {
			if (user.getUserID().equals(userID) && user.getUserPassword().equals(password)) {
				System.out.println("登录成功！即将为你跳转主菜单");
				try {
					Thread.sleep(500);
					login = true;
					loginUser = user;
					break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		if (!login){
			System.out.println("账号或密码错误！重新输入或返回上一页？(1.重新输入 2.返回上一页)");
			System.out.print("请输入:");
			int choice = s.nextInt();
			System.out.println();
			if (choice == 1) {
				login();
			} else if (choice == 2) {
				userUtils.userMenu();
			}
		}else if(login){
			MakeQuestion.menu(loginUser);
		}
	}



	@Override
	public List<User> readFile(String fileName) {
		List<String[]> rows = new ArrayList<>();
		List<User> userList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			int lineNumber = 0;
			while ((line = br.readLine()) != null) {
				lineNumber++;
				if (lineNumber > 1){
					String[] cells = line.split(",");
					rows.add(cells);
					if (cells.length >= 2) {
						String data1 = cells[0].trim();
						String data2 = cells[1].trim();
						User user = new User();
						user.setUserID(data1);
						user.setUserPassword(data2);
						userList.add(user);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}


}
