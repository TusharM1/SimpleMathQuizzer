package MathQuizzer;

import MathQuizzer.Loading.LoadingController;
import MathQuizzer.Quiz.QuizController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Main extends Application {

    public Parent loadingScreen, quizScreen;
    public Stage primaryStage;

    public LoadingController loadingController;
    public QuizController quizController;

    public Scene loadingScene, quizScene;

    public HashMap<String, String> settings;

    /*
    TODO:
    - Add input box
    - Add timing
    - Implement settings button and save file
    - Add load problem sheet
    - Add vertical numbers
    - Add complex expression mode (with all addition, subtraction, multiplication, and division)
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        settings = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/MathQuizzer/settings.config"));
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null)
                if (!(currentLine.length() == 0) && !(currentLine.charAt(0) == '#'))
                    settings.put(currentLine.substring(0, currentLine.indexOf("=")),
                            currentLine.substring(currentLine.indexOf("=") + 1));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(settings);

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
