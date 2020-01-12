package MathQuizzer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import MathQuizzer.Loading.LoadingController;
import MathQuizzer.Quiz.QuizController;

public class Main extends Application {

    public Parent loadingScreen, quizScreen;
    public Stage primaryStage;

    public LoadingController loadingController;
    public QuizController quizController;

    public Scene loadingScene, quizScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        FXMLLoader quizLoader = new FXMLLoader(getClass().getResource("Quiz/quizScreen.fxml"));
        quizScreen = quizLoader.load();
        quizController = quizLoader.getController();
        quizController.main = this;
        quizScene = new Scene(quizScreen);

        FXMLLoader loadingLoader = new FXMLLoader(getClass().getResource("Loading/loadingScreen.fxml"));
        loadingScreen = loadingLoader.load();
        loadingController = loadingLoader.getController();
        loadingController.main = this;
        loadingScene = new Scene(loadingScreen);

        primaryStage.setScene(loadingScene);
        primaryStage.setTitle("SimpleMathQuizzer");
        primaryStage.show();
    }

    public void switchToQuizScreen() {
        primaryStage.setScene(quizScene);
    }

    public void switchToLoadingScreen() {
        primaryStage.setScene(loadingScene);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
