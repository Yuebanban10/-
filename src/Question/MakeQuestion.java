package Question;

import Question.Utils.MyUtil;
import User.User.User;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MakeQuestion {
	public static MyUtil utils = new MyUtil();
	public static User loginUser;
	public static int maxWrongNum = 0;
	public static int[] maxWrongQuestionsCount = new int[100];
	public static int GRADE_ONE_KIND_ONE = 1;
	public static int GRADE_ONE_KIND_TWO = 2;
	public static int GRADE_ONE_KIND_THREE = 3;
	public static int GRADE_TWO_KIND_ONE = 4;
	public static int GRADE_TWO_KIND_TWO = 5;
	public static int GRADE_TWO_KIND_THREE = 6;
	public static int GRADE_TWO_KIND_FOUR = 7;
	public static int GRADE_TWO_KIND_FIVE = 8;
	public static int MAX_WRONG_TRAIN_COUNT = 5;
	public static final String PLUS = "+";
	public static final String SUBTRACTION = "-";
	public static final int NORMAL = 0;
	public static final int WRONG = 1;
	static int Garde;
	static int QuestionsCount;
	static List<Question> haveWrongQuestionList = new ArrayList<>();
	static Random random = new Random();

	public static void menu(User user) {
		loginUser = user;
		haveWrongQuestionList = readFile();
		if (haveWrongQuestionList != null){
			haveWrongQuestionList.sort(Comparator.comparing(Question::getWrongNum).reversed());
		}
		System.out.println("----------小学数学口算软件------------");
		System.out.println("|                                 |");
		System.out.println("|            请选择年级             |");
		System.out.println("|                                 |");
		System.out.println("|    1.小学一年级     2.小学二年级    |");
		System.out.println("|    3.错题训练      	4.退出     	  |");
		System.out.println("|                                 |");
		System.out.println("-----------------------------------");
		System.out.print("请输入你的选择:");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		switch (choice) {
			case 1:
				grade_one_menu();
				break;
			case 2:
				grade_two_menu();
				break;
			case 3:
				System.out.print("即将开始错题训练，请输入你想做的题目数量:");
				MAX_WRONG_TRAIN_COUNT = scanner.nextInt();
				System.out.println();
				if (MAX_WRONG_TRAIN_COUNT > haveWrongQuestionList.size()){
					System.out.println("错题数量不足！即将显示所有错题");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				printQuestions(createWrongQuestion(), WRONG);
				break;
			case 4:
				System.exit(0);
		}
	}

	static void grade_one_menu() {
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
		System.out.print("请输入题目数量:");
		Scanner sc = new Scanner(System.in);
		int questionsCount = sc.nextInt();
		System.out.println("正在生成,请稍后...");
		try {
			Thread.sleep(3000);
			System.out.println();
			System.out.println("题目如下:");
			makeGradeOneQuestion(choice, questionsCount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static void grade_two_menu() {
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
		System.out.print("请输入题目数量:");
		Scanner sc = new Scanner(System.in);
		int questionsCount = sc.nextInt();
		System.out.println("正在生成,请稍后...");
		try {
			Thread.sleep(3000);
			System.out.println();
			System.out.println("题目如下:");
			makeGradeTwoQuestion(choice, questionsCount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static void makeGradeTwoQuestion(int choice, int questionsCount) {
		switch (choice) {
			case 1:
				printQuestions(createGradeTwo_q1(questionsCount), NORMAL);
				break;
			case 2:
				printQuestions(createGradeTwo_q2(questionsCount), NORMAL);
				break;
			case 3:
				printQuestions(createGradeTwo_q3(questionsCount), NORMAL);
				break;
			case 4:
				printQuestions(createGradeTwo_q4(questionsCount), NORMAL);
				break;
			case 5:
				printQuestions(createGradeTwo_q5(questionsCount), NORMAL);
				break;
			case 6:
				printQuestions(createGradeTwo_q6(questionsCount), NORMAL);
				break;
			case 7:
				menu(loginUser);
				break;
		}
	}

	static void makeGradeOneQuestion(int choice, int questionsCount) {
		switch (choice) {
			case 1:
				printQuestions(createGradeOne_q1(questionsCount), NORMAL);
				break;
			case 2:
				printQuestions(createGradeOne_q2(questionsCount), NORMAL);
				break;
			case 3:
				printQuestions(createGradeOne_q3(questionsCount), NORMAL);
				break;
			case 4:
				printQuestions(createGradeOne_q4(questionsCount), NORMAL);
				break;
			case 5:
				menu(loginUser);
			default:
				System.out.println("输入错误，请重新输入");
				grade_one_menu();
				break;
		}
	}


	static List<Question> createGradeOne_q1(int questionsCount) {
		List<Question> questionList = new ArrayList<>();
		for (int i = 0; i < questionsCount; i++) {
			Question question = new Question();
			question.setQuestionsKind(GRADE_ONE_KIND_ONE);
			int operand_1 = random.nextInt(10) + 1;
			int operand_2 = random.nextInt(10) + 1;
			String operator = random.nextBoolean() ? "+" : "-";
			question.addOperand(operand_1);
			question.addOperator(operator);
			question.addOperand(operand_2);
			if (operator.equals("-") && operand_1 < operand_2) {
				i--;
			} else {
				question.setAnswer();
				questionList.add(question);
			}
		}
		return questionList;
	}

	static List<Question> createGradeOne_q2(int questionsCount) {
		List<Question> questionList = new ArrayList<>();
		for (int i = 0; i < questionsCount; i++) {
			Question question = new Question();
			question.setQuestionsKind(GRADE_ONE_KIND_TWO);
			int operand_1 = random.nextInt(10) + 1;
			int operand_2 = random.nextInt(10) + 1;
			int operand_3 = random.nextInt(10) + 1;
			String operator_1 = random.nextBoolean() ? "+" : "-";
			String operator_2 = random.nextBoolean() ? "+" : "-";
			question.addOperand(operand_1);
			question.addOperator(operator_1);
			question.addOperand(operand_2);
			question.addOperator(operator_2);
			question.addOperand(operand_3);
			question.setAnswer();
			questionList.add(question);
		}
		return questionList;
	}

	static List<Question> createGradeOne_q3(int questionsCount) {
		List<Question> questionList = new ArrayList<>();
		for (int i = 0; i < questionsCount; i++) {
			Question question = new Question();
			question.setQuestionsKind(GRADE_ONE_KIND_THREE);
			int numOperands = random.nextInt(2) + 1; // 1 or 2
			for (int j = 0; j < numOperands; j++) {
				int operand = random.nextInt(11) + 10; // 10 to 20
				String operator = random.nextBoolean() ? "+" : "-";
				question.addOperand(operand);
				question.addOperator(operator);
			}
			int lastOperand = random.nextInt(11) + 10; // 10 to 20
			question.addOperand(lastOperand);
			question.setAnswer();
			questionList.add(question);
		}
		return questionList;
	}

	static List<Question> createGradeOne_q4(int questionsCount) {
		int averQuestionCount = questionsCount / 3;
		List<Question> questionList = new ArrayList<>();
		questionList.addAll(createGradeOne_q1(averQuestionCount));
		questionList.addAll(createGradeOne_q2(averQuestionCount));
		questionList.addAll(createGradeOne_q3(questionsCount - 2 * averQuestionCount));
		return questionList;
	}


	static List<Question> createGradeTwo_q1(int questionsCount) {
		List<Question> questionList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < questionsCount; i++) {
			Question question = new Question();
			question.setQuestionsKind(GRADE_TWO_KIND_ONE);
			int operand_1 = random.nextInt(100);
			int operand_2 = random.nextInt(100);
			if ((((operand_1 / 10) + (operand_2 / 10)) < 10) && ((operand_1 % 10) + (operand_2 % 10)) < 10) {
				question.addOperand(operand_1);
				question.addOperand(operand_2);
				question.addOperator(PLUS);
			} else {
				i--;
				continue;
			}
			question.setAnswer();
			questionList.add(question);
		}
		return questionList;
	}

	static List<Question> createGradeTwo_q3(int questionsCount) {
		List<Question> questionList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < questionsCount; i++) {
			Question question = new Question();
			question.setQuestionsKind(GRADE_TWO_KIND_THREE);
			int operand_1 = random.nextInt(100);
			int operand_2 = random.nextInt(100);
			if ((((operand_1 / 10) + (operand_2 / 10)) > 10) || ((operand_1 % 10) + (operand_2 % 10)) < 10) {
				question.addOperand(operand_1);
				question.addOperand(operand_2);
				question.addOperator(PLUS);
			} else {
				i--;
				continue;
			}
			question.setAnswer();
			questionList.add(question);
		}
		return questionList;
	}

	static List<Question> createGradeTwo_q2(int questionsCount) {
		List<Question> questionList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < questionsCount; i++) {
			Question question = new Question();
			question.setQuestionsKind(GRADE_TWO_KIND_TWO);
			int operand_1 = random.nextInt(100);
			int operand_2 = random.nextInt(100);
			if ((((operand_1 / 10) > (operand_2 / 10)) && ((operand_1 % 10) > (operand_2 % 10)))) {
				question.addOperand(operand_1);
				question.addOperand(operand_2);
				question.addOperator(SUBTRACTION);
			} else {
				i--;
				continue;
			}
			question.setAnswer();
			questionList.add(question);
		}
		return questionList;
	}

	static List<Question> createGradeTwo_q4(int questionsCount) {
		List<Question> questionList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < questionsCount; i++) {
			Question question = new Question();
			question.setQuestionsKind(GRADE_TWO_KIND_FOUR);
			int operand_1 = random.nextInt(100);
			int operand_2 = random.nextInt(100);
			if ((((operand_1 / 10) < (operand_2 / 10)) || ((operand_1 % 10) < (operand_2 % 10))) && (operand_1 - operand_2) > 0) {
				question.addOperand(operand_1);
				question.addOperand(operand_2);
				question.addOperator(SUBTRACTION);
			} else {
				i--;
				continue;
			}
			question.setAnswer();
			questionList.add(question);
		}
		return questionList;
	}

	static List<Question> createGradeTwo_q5(int questionsCount) {
		List<Question> questionList = new ArrayList<>();
		int operandCount = random.nextInt(3) + 3;
		List<String> operatorList = new ArrayList<>();
		String operator = random.nextBoolean() ? "+" : "-";
		for (int i = 0; i < operandCount - 1; i++) {
			operatorList.add(operator);
		}
		for (int j = 0; j < questionsCount; j++) {
			Question question = new Question();
			question.setOperandList(getOperandList(operator, operandCount));
			question.setOperatorList(operatorList);
			question.setAnswer();
			question.setQuestionsKind(GRADE_TWO_KIND_FIVE);
			questionList.add(question);
		}
		return questionList;
	}

	static List<Question> createGradeTwo_q6(int questionsCount) {
		int averQuestionCount = questionsCount / 5;
		List<Question> questionList = new ArrayList<>();
		questionList.addAll(createGradeTwo_q1(averQuestionCount));
		questionList.addAll(createGradeTwo_q2(averQuestionCount));
		questionList.addAll(createGradeTwo_q3(averQuestionCount));
		questionList.addAll(createGradeTwo_q4(averQuestionCount));
		questionList.addAll(createGradeTwo_q5(questionsCount - averQuestionCount * 4));
		return questionList;
	}

	static List<Integer> getOperandList(String operator, int operandCount) {
		List<Integer> operandList = new ArrayList<>();
		int result = 0;
		for (int k = 0; k < operandCount; k++) {
			int operand = random.nextInt(100);
			if (operator.equals(SUBTRACTION)) {
				if (result == 0) {
					result = result + operand;
					operandList.add(operand);
				} else {
					if (result - operand < 0) {
						k--;
					} else {
						result = result - operand;
						operandList.add(operand);
					}
				}
			} else {
				operandList.add(operand);
			}
		}
		return operandList;
	}

	static void printQuestions(List<Question> questionList, int questionsKind) {
		for (Question question : questionList) {
			System.out.println(question);
		}
		getAnswer(questionList.size(), questionList, questionsKind);
	}

	static void getAnswer(int questionsCount, List<Question> questionList, int questionKind) {
		System.out.println("请输入你的答案:(每个答案之间请用空格隔开)");
		System.out.print("请作答:");
		Scanner s = new Scanner(System.in);
		List<Integer> answerList = new ArrayList<>();
		for (int i = 0; i < questionsCount; i++) {
			answerList.add(s.nextInt());
		}
		System.out.println("作答完成！正在生成答案...");
		judge(answerList, questionList, questionKind);
	}

	static void printResult(List<Question> questionList) {
		for (int i = 0; i < questionList.size(); i++) {
			System.out.print("题目" + (i + 1) + "的答案是:" + questionList.get(i).getResult() + "\t");
		}
		utils.pressToReturn();
		grade_one_menu();
	}

	static void judge(List<Integer> answerList, List<Question> questionList, int questionKind) {
		try {
			Thread.sleep(1000);
			if (questionKind == NORMAL) {
				List<Question> wrongQuestions = new ArrayList<>();
				for (int i = 0; i < answerList.size(); i++) {
					Question question = questionList.get(i);
					int answer = answerList.get(i);
					if (question.getResult() != answer) {
						System.out.print("第" + (i + 1) + "题结果错误 ");
						question.setWrongNum(1);
						question.setWrongTime(getTime());
						wrongQuestions.add(question);
					}
				}
				if (wrongQuestions.size() == 0) {
					System.out.println("恭喜你，全部题目都答对了！");
				}
				System.out.println();
				System.out.println("所有题目答案如下:");
				writeToFile(wrongQuestions, NORMAL);
				printResult(questionList);
			} else {
				//传入的是答错最多次的题目集合
				for (int j = 0; j < questionList.size(); j++) {
					Question question = questionList.get(j);
					int answer = answerList.get(j);
					//又做错一次，则错误次数加一
					if (question.getResult() != answer) {
						System.out.print("第" + (j + 1) + "题结果错误 ");
						//设置错误次数
						question.setWrongNum(question.getWrongNum() + 1);
						//设置错误时间
						question.setWrongTime(getTime());
						questionList.set(j, question);
					}
				}
				//更新错题集合
				for (int k = 0; k < questionList.size(); k++) {
					for (int l = 0; l < haveWrongQuestionList.size(); l++) {
						if (haveWrongQuestionList.get(k).equals(questionList.get(k).toString())) {
							haveWrongQuestionList.set(l, questionList.get(k));
						}
					}
				}
				writeToFile(haveWrongQuestionList, WRONG);
				printResult(questionList);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static String getTime() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return currentTime.format(formatter);
	}

	public static List<Question> readFile() {
		String fileName = "D:\\Java实验与大作业\\大作业\\用户" + loginUser.getUserID() + "的错题集合.csv";
		List<String[]> rows = new ArrayList<>();
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		List<Question> haveWrongQuestionList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] cells = line.split(",");
				rows.add(cells);
				if (cells.length > 1) {
					Question question = new Question();
					question.setWrongTime(cells[0].trim());
					question.setOperandList(getQuestionsOperand(cells[1].trim()));
					question.setOperatorList(getQuestionsOperation(cells[1].trim()));
					question.setQuestionsKind(Integer.parseInt(cells[2].trim()));
					question.setWrongNum(Integer.parseInt(cells[3].trim()));
					haveWrongQuestionList.add(question);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return haveWrongQuestionList;
	}

	static void writeToFile(List<Question> wrongList, int writeKind) {
		String fileName = "D:\\Java实验与大作业\\大作业\\用户" + loginUser.getUserID() + "的错题集合.csv";
		File file = new File(fileName);
		FileWriter writer = null;
		try {
			if (writeKind == NORMAL) {
				writer = new FileWriter(fileName, true);
			} else {
				writer = new FileWriter(fileName, false);
			}
			for (Question question : wrongList) {
				writer.append(question.getWrongTime()).append(",").append(question.toString()).append(",").append(String.valueOf(question.getQuestionsKind())).append(",").append(String.valueOf(question.getWrongNum())).append("\n");
			}
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static List<Integer> getQuestionsOperand(String question) {
		List<Integer> operatorList = new ArrayList<>();
		Pattern pattern = Pattern.compile("\\b\\d+\\b");
		Matcher matcher = pattern.matcher(question);
		while (matcher.find()) {
			int operand = Integer.parseInt(matcher.group());
			operatorList.add(operand);
		}
		return operatorList;
	}

	static List<String> getQuestionsOperation(String question) {
		List<String> operatorList = new ArrayList<>();
		Pattern pattern = Pattern.compile("[-+*/]"); // 匹配加法或减法运算符
		Matcher matcher = pattern.matcher(question);
		while (matcher.find()) {
			String operator = matcher.group().trim();
			operatorList.add(operator);
		}
		return operatorList;
	}

	static List<Question> createWrongQuestion() {
		List<Question> wrongTrainList = new ArrayList<>();
		if (haveWrongQuestionList == null) {
			System.out.println("你还没有错题，无法生成训练");
			menu(loginUser);
		} else {
			setMaxWrongNum();
			int questionCount = MAX_WRONG_TRAIN_COUNT;
			for (int i = maxWrongNum; i > 0; i--) {
				if (getMakeMistakeCountList(i).size() > questionCount) {
					wrongTrainList.addAll(getRandomWrongQuestions(getMakeMistakeCountList(i), questionCount));
					break;
				} else if (getMakeMistakeCountList(i).size() <= questionCount) {
					wrongTrainList.addAll(getMakeMistakeCountList(i));
					questionCount = questionCount - getMakeMistakeCountList(i).size();
				}
			}
		}
		return wrongTrainList;
	}

	/**
	 * 当错误次数最多的题目数量大于生产的题目数量时，在错误次数最多的题目中随机生成
	 * @param wrongQuestionList
	 * @param questionsCount
	 * @return
	 */
	static List<Question> getRandomWrongQuestions(List<Question> wrongQuestionList, int questionsCount) {
		List<Integer> randomList = new ArrayList<>();
		List<Question> randomWrongQuestionList = new ArrayList<>();
		//生成随机不重复序列
		for (int i = 0; i < questionsCount; i++) {
			Random random = new Random();
			int randomNum = random.nextInt(wrongQuestionList.size()) + 1;
			if (randomList.size() == 0) {
				randomList.add(randomNum);
			} else {
				if (randomList.contains(randomNum)) {
					i--;
				} else {
					randomList.add(randomNum);
				}
			}
		}
		for (int j = 0; j < randomList.size(); j++) {
			randomWrongQuestionList.add(wrongQuestionList.get(randomList.get(j)));
		}
		return randomWrongQuestionList;
	}

	/**
	 * @param wrongCount
	 * @return
	 */
	static List<Question> getMakeMistakeCountList(int wrongCount) {
		List<Question> wrongList = new ArrayList<>();
		for (Question question : haveWrongQuestionList) {
			if (question.getWrongNum() == wrongCount) {
				question.setAnswer();
				wrongList.add(question);
			}
		}
		return wrongList;
	}

	static void setMaxWrongNum() {
		for (Question question : haveWrongQuestionList) {
			if (question.getWrongNum() > maxWrongNum) {
				maxWrongNum = question.getWrongNum();
			} else if (question.getWrongNum() == maxWrongNum) {
				maxWrongQuestionsCount[maxWrongNum]++;
			}
		}
	}
}
