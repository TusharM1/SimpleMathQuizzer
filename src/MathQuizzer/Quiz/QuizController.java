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
					if (left < right)
						return (right / left) + " Remainder " + (right % left);
					else if (right < left)
						return (left / right) + " Remainder " + (left % right);
					else
						return String.valueOf(left / right);
				};
				operator = '/';
				break;
		}
		newProblem();
	}

	public void showAnswer() {
		answer.setVisible(true);
	}

	@FXML
	public void newProblem() {
		int a = (int) (Math.random() * 100 + 1);
		int b = (int) (Math.random() * 100 + 1);
		if (choice == Choices.DIVISION && b > a)
			expression.setText(b + " " + operator + " " + a);
		else
			expression.setText(a + " " + operator + " " + b);
		answer.setText(operation.apply(a, b));
		answer.setVisible(false);
	}

	@FXML
	public void back() {
		main.switchToLoadingScreen();
	}

	public void onKeyReleased(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.RIGHT)
			newProblem();
		else if (keyEvent.getCode() == KeyCode.SHIFT)
			showAnswer();
	}
}
