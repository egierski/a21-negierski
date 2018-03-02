package edu.bsu.hci;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class GUI extends Application {
    private final TextField numberOfAssignmentsField = new TextField();
    private final TextField numberOfAssignmentsCompletedField = new TextField();
    private Stage primaryStage = new Stage();
    private Gradebook gradebook = new Gradebook();
    private ArrayList<TextField> listOfAssignmentScoredField = new ArrayList<>();


    @Override
    public void start(Stage stage) {
        primaryStage.setScene(introPageScene());
        primaryStage.setTitle("Triage Grading Tool");
        primaryStage.show();
    }

    private void changeScene(Scene scene) {
        primaryStage.setScene(scene);
    }

    private Scene introPageScene() {
        GridPane gridPane = new GridPane();
        Button button = new Button("Done");
        numberOfAssignmentsCompletedField.setMaxWidth(50);
        numberOfAssignmentsField.setMaxWidth(50);
        button.setOnAction(event ->
                changeScene(insertGradePage()));
        gridPane.add(new Label("Please enter your answers in the " +
                        "textfield beside each question."),
                0, 0);
        gridPane.add(new Label("Enter how many assignments " +
                        "there are total in your class:"),
                0, 1);
        gridPane.add(new Label("Enter how many assignments " +
                        "have you received a grade for:"),
                0, 2);
        gridPane.add(numberOfAssignmentsField,
                1, 1);
        gridPane.add(numberOfAssignmentsCompletedField,
                1, 2);
        gridPane.add(button,
                1, 3);
        return new Scene(gridPane);
    }

    private void retrieveNumberAssignmentFields() {
        GradebookParser gparser = new GradebookParser();
        gradebook.setNumberOfAssignments(
                gparser.parseNumberOnly(
                        numberOfAssignmentsField.getText()));
        gradebook.setNumberOfAssignmentsCompleted(
                gparser.parseNumberOnly(
                        numberOfAssignmentsCompletedField.getText()
                )
        );
    }

    private Scene insertGradePage() {
        retrieveNumberAssignmentFields();
        ScrollPane scrollPane = new ScrollPane();
        VBox parent = new VBox();
        GridPane gridPane = new GridPane();
        scrollPane.setMinWidth(390);
        Button button = new Button("Done");
        button.setOnAction(event -> retrieveAssignmentScoresFromField());
        ScrollPane sp = new ScrollPane();
        for (int i = 1; i <= gradebook.getNumberOfAssignmentsCompleted(); i++) {
            TextField scoreTextfield = new TextField();
            listOfAssignmentScoredField.add(scoreTextfield);
            scoreTextfield.setMaxWidth(50);
            gridPane.add(new Label("Assignment " + i + " => "),
                    0, i);
            gridPane.add(scoreTextfield,
                    1, i);
            gridPane.add(new Label(" /3  "),
                    2, i);
        }
        parent.getChildren().addAll(
                new Label("Enter the scores you have recieved" +
                        " and click 'Done'"),
                gridPane,
                button
        );
        scrollPane.setContent(parent);
        return new Scene(scrollPane);
    }

    private void retrieveAssignmentScoresFromField() {
        GradebookParser gparser = new GradebookParser();
        for (TextField assignment : listOfAssignmentScoredField) {
            gradebook.addToCompletedAssignmentGrade(
                    gparser.parseNumberOnly(assignment.getText())
            );
        }
        gradebook.setPointsEarned();
        changeScene(modifiedGradeBookView());
    }

    private Scene modifiedGradeBookView() {
        VBox vBox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        scrollPane.setMinWidth(225);
        Button button = new Button("See Short-term Grade");
        button.setOnAction(event -> changeScene(fullGradebookView()));
        vBox.getChildren().addAll(
                new Label(gradebook.reportAllAssinments()),
                new Label("Long Term Grade: => " + gradebook.calculateLongTermGrade() + "%"),
                button);
        return new Scene(scrollPane);
    }

    private void showWarningDialogue() {
        Alert errorAlert = new Alert(Alert.AlertType.WARNING);
        errorAlert.setHeaderText("Please Read!");
        errorAlert.setContentText(gradebook.getShortTermGradeWarningMessage());
        errorAlert.showAndWait();
    }


    private Scene fullGradebookView() {
        showWarningDialogue();
        VBox parent = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinWidth(225);
        scrollPane.setContent(parent);
        parent.getChildren().addAll(
                new Label(gradebook.reportAllAssinments()),
                new Label("Long Term Grade: => " + gradebook.calculateLongTermGrade() + "%"),
                new Label("Short Term Grade: => " + gradebook.calculateShortTermGrade() + "%")

        );
        // buttoon to exit/open
        return new Scene(scrollPane);
    }


}
