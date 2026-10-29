package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class PrimaryController {

    @FXML
    private RadioButton dateFormatLong;
    @FXML
    private RadioButton dateFormatShort;
    @FXML
    private RadioButton dateFormatRoman;
    @FXML
    private RadioButton dateFormatNumber;

    Date today = new Date(29, 10, 2026);

    @FXML
    private Label dateLabel;

    @FXML
    public void initialize() {
        dateFormatLong.setOnAction(event -> changeFormat(Date.LONG_FORMAT));
        dateFormatShort.setOnAction(event -> changeFormat(Date.SHORT_FORMAT));
        dateFormatRoman.setOnAction(event -> changeFormat(Date.ROMAN_FORMAT));
        dateFormatNumber.setOnAction(event -> changeFormat(Date.NUMBER_FORMAT));

        dateLabel.setText(today.getDate(Date.LONG_FORMAT));
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void changeFormat(final int format) {
        dateLabel.setText(today.getDate(format));
    }
}
