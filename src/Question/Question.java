package Question;

import java.util.ArrayList;
import java.util.List;

public class Question {
	private List<String> operatorList = new ArrayList<>();
	private List<Integer> operandList = new ArrayList<>();
	private int result;
	private int questionsKind;
	private String wrongTime;
	private int wrongNum;

	public int getWrongNum() {
		return wrongNum;
	}

	public void setWrongNum(int wrongNum) {
		this.wrongNum = wrongNum;
	}

	public String getWrongTime() {
		return wrongTime;
	}

	public void setWrongTime(String wrongTime) {
		this.wrongTime = wrongTime;
	}


	public int getQuestionsKind() {
		return questionsKind;
	}

	public void setQuestionsKind(int questionsKind) {
		this.questionsKind = questionsKind;
	}

	public int getResult() {
		return result;
	}

	public void setAnswer() {
		int result = operandList.get(0);
		for (int i = 0; i < operatorList.size(); i++) {
			String operator = operatorList.get(i);
			int operand = operandList.get(i + 1);
			if (operator.equals("+")) {
				result += operand;
			} else if (operator.equals("-")) {
				result -= operand;
			}
		}
		this.result = result;
	}

	public Question() {
		this.operatorList = new ArrayList<>();
		this.operandList = new ArrayList<>();
	}

	public List<String> getOperatorList() {
		return operatorList;
	}

	public void setOperatorList(List<String> operatorList) {
		this.operatorList = operatorList;
	}

	public List<Integer> getOperandList() {
		return operandList;
	}

	public void setOperandList(List<Integer> operandList) {
		this.operandList = operandList;
	}

	public void addOperator(String operator) {
		operatorList.add(operator);
	}

	public void addOperand(int operand) {
		operandList.add(operand);
	}

	//输出题目内容
	@Override
	public String toString() {
		StringBuilder equation = new StringBuilder();
		for (int i = 0; i < operandList.size(); i++) {
			equation.append(operandList.get(i));
			if (i < operatorList.size()) {
				equation.append(" ");
				equation.append(operatorList.get(i));
				equation.append(" ");
			}
		}
		equation.append(" =");
		return equation.toString();
	}


}
