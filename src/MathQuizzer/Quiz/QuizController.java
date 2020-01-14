package MathQuizzer.Quiz;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import MathQuizzer.Choices;
import MathQuizzer.Main;

import java.util.function.BiFunction;

public class QuizController {

	@FXML
	public Label expression, answer;

	@FXML
	public Button showAnswer, next, back;

	public char operator;

	public Choices choice;
	public BiFunction<Integer, Integer, String> operation;

	public Main main;

	public void setOperation(Choices choice) {
		this.choice = choice;
		switch (choice) {
			case ADDITION: operation = (left, right) -> String.valueOf(left + right); operator = '+'; break;
			case SUBTRACTION: operation = (left, right) -> String.valueOf(left - right); operator = '-'; break;
			case MULTIPLICATION: operation = (left, right) -> String.valueOf(left * right); operator = '*'; break;
			case DIVISION:
				operation = (left, right) -> {
					switch (main.settings.get("remainder")) {
						case "remainder":
							return (left / right) + " R " + (left % right);
						case "decimal":
							return String.valueOf((double) left / right);
						case "mixed_fraction":
							return left / right + " + " + reduceFraction(left % right, right);
						case "reduced_fraction":
							return reduceFraction(left, right);
						case "no_fraction":
							return String.valueOf(left / right);
					}
					return null;
				};
				operator = '/';
				break;
		}
		newProblem();
	}

	private String reduceFraction(Integer left, Integer right) {
		System.out.println(left + " " + right);
		int remainder, firstNumber = left, secondNumber = right;
		do {
			remainder = firstNumber % secondNumber;
			firstNumber = secondNumber;
			secondNumber = remainder;
		} while (remainder != 0);
		double a = (double) left / firstNumber;
		double b = (double) right / firstNumber;
		System.out.println(a + " " + b);
		return (int) a + " / " + (int) b;
	}

	public void showAnswer() {
		answer.setVisible(!answer.isVisible());
	}

	@FXML
	public void newProblem() {
		int difficulty = Integer.parseInt(main.settings.get("difficulty"));
		int a = (int) (Math.random() * difficulty + 1);
		int b = (int) (Math.random() * difficulty + 1);
		if (choice == Choices.DIVISION) {
			if (b > a) {
				int temp = a;
				a = b;
				b = temp;
			}
			if (a / b / 10 == 0)
				b /= (int) Math.pow(10, (Math.random() * .5 + .5) * (int) Math.min(Math.max(1, Math.log10(b)), Math.log10(b) - 1));
			if (main.settings.get("remainder").equals("no_remainder"))
				a -= a % b;
		}
		expression.setText(a + " " + operator + " " + b);
		answer.setText(operation.apply(a, b));
		answer.setVisible(false);
	}

	@FXML
	public void back() {
		main.switchToLoadingScreen();
	}

	public void onKeyReleased(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.BACK_SLASH)
			newProblem();
		else if (keyEvent.getCode() == KeyCode.CLOSE_BRACKET)
			showAnswer();
		else if (keyEvent.getCode() == KeyCode.BACK_SPACE)
			back();
	}

}
