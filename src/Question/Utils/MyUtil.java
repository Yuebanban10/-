package Question.Utils;

import java.util.Scanner;

public class MyUtil implements IUtils {
	public void pressToReturn(){
		Scanner s = new Scanner(System.in);
		System.out.println();
		System.out.println("请按下Enter键以返回上一级..");
		s.nextLine();
	}

	public void grade_two_menu() {
		System.out.println("----------------小学二年级-----------------");
		System.out.println("|                                       |");
		System.out.println("|   选项1: 100以内不进位加法               	|");
		System.out.println("|   选项2: 100以内不退位减法               	|");
		System.out.println("|   选项3: 100以内进位加法           		|");
		System.out.println("|   选项4: 100以内退位减法					|");
		System.out.println("|   选项5: 100以内连加连减           		|");
		System.out.println("|   选项6: 综合训练					   	|");
		System.out.println("|   选项7: 返回上一级              	    |");
		System.out.println("|                                       |");
		System.out.println("-----------------------------------------");
		System.out.print("请输入你的选择:");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
	}

	@Override
	public void main_menu() {

	}

	public void grade_one_menu() {
		System.out.println("----------------小学一年级----------------");
		System.out.println("|                                      |");
		System.out.println("|   选项1: 10以内加减法(1步计算)           |");
		System.out.println("|   选项2: 10以内加减法(2步计算)           |");
		System.out.println("|   选项3: 10-20的加减法(1步计算和2步计算)  |");
		System.out.println("|   选项4: 综合训练					   |");
		System.out.println("|   选项5: 返回上一级                	   |");
		System.out.println("|                                      |");
		System.out.println("----------------------------------------");
		System.out.print("请输入你的选择:");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
	}
}
