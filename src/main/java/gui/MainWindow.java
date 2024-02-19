package gui;

import ezra.Ezra;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow, providing the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ezra ezra;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image ezraImage = new Image(this.getClass().getResourceAsStream("/images/Ezra.png"));

    /**
     * Displays greeting message and scrolls down to the end every time dialogContainer's height changes.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getEzraDialog("Hello! I'm Ezra.\nWhat can I do for you?", ezraImage)
        );
    }

    /**
     * Sets the Ezra instance for the controller.
     *
     * @param e The Ezra instance to set.
     */
    public void setEzra(Ezra e) {
        ezra = e;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ezra.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEzraDialog(response, ezraImage)
        );
        userInput.clear();
    }
}

