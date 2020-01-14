package MathQuizzer.Loading;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import MathQuizzer.Choices;
import MathQuizzer.Main;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoadingController {

	@FXML
	public Button addition, subtraction, multiplication, division;
	
	public Main main;

	@FXML
	private void additionAction() {
		main.switchToQuizScreen();
		main.quizController.setOperation(Choices.ADDITION);
	}

	@FXML
	private void subtractionAction() {
		main.switchToQuizScreen();
		main.quizController.setOperation(Choices.SUBTRACTION);
	}

	@FXML
	private void multiplicationAction() {
		main.switchToQuizScreen();
		main.quizController.setOperation(Choices.MULTIPLICATION);
	}

	@FXML
	private void divisionAction() {
		main.switchToQuizScreen();
		main.quizController.setOperation(Choices.DIVISION);
	}

	public void onKeyReleased(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.DIGIT1)
			additionAction();
		else if (keyEvent.getCode() == KeyCode.DIGIT2)
			subtractionAction();
		else if (keyEvent.getCode() == KeyCode.DIGIT3)
			multiplicationAction();
		else if (keyEvent.getCode() == KeyCode.DIGIT4)
			divisionAction();
	}

}
