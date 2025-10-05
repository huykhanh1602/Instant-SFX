package com.example;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MenuController {

    @FXML
    private VBox listContainer;
    private List<Sound> sounds = new ArrayList<>();
    private int count = 0;

    private void addSoundToList(Sound sound) {
        count++;
        sounds.add(sound);
        System.out.println("Added sound " + count);

        HBox row = new HBox(10);
        Text label = new Text(sound.getName());
        Button playButton = new Button("Play");
        label.setFill(Color.WHITE);

        playButton.setOnAction(e -> {
            if (sound.getAudioClip() == null) {
                System.out.println("No audio clip available for this sound.");
            } else {
                sound.play();
            }
        });
        row.getChildren().addAll(label, playButton);
        listContainer.getChildren().add(row);
    }

    public void addFile(ActionEvent e) {
        System.out.println("Open File Chooser clicked!");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Add Sound File");
        fileChooser.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac", "*.aiff", "*.m4a"));
        File file = fileChooser.showOpenDialog(null);

        if (file == null) {
            System.out.println("No file selected");
            return;
        } else {
            System.out.println("Selected file at: " + file.getAbsolutePath());
            AudioClip audioClip = new AudioClip(file.toURI().toString());
            Sound sound = new Sound();

            sound.setName(file.getName());
            sound.setPath(file.getAbsolutePath());
            sound.setFile(file);
            sound.setAudioClip(audioClip);

            addSoundToList(sound);
        }
    }

}