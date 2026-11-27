package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CharacterController {

    @FXML private TextField nameField;
    @FXML private ComboBox<CharacterType> typeCombo;
    @FXML private Spinner<Integer> levelSpinner;
    @FXML private Label messageLabel;
    @FXML private VBox cardsBox;
    @FXML private Label countLabel;
    @FXML private Label avgLevelLabel;
    @FXML private Label statusLabel;

    private CharacterManager manager;

    @FXML
    public void initialize() {
        typeCombo.setItems(FXCollections.observableArrayList(CharacterType.values()));
        levelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1));
        levelSpinner.setEditable(true);
        refreshList();
    }

    public void setManager(CharacterManager manager) {
        this.manager = manager;
        refreshList();
    }

    @FXML
    public void handleSave() {
        if (manager == null) {
            messageLabel.setText("Manager belum siap");
            return;
        }

        String name = nameField.getText() == null ? "" : nameField.getText().trim();
        if (name.isEmpty()) {
            messageLabel.setText("Nama wajib diisi");
            return;
        }

        CharacterType type = typeCombo.getValue();
        if (type == null) {
            messageLabel.setText("Role wajib dipilih");
            return;
        }

        Integer level = levelSpinner.getValue();
        if (level == null) {
            messageLabel.setText("Level harus 1-99");
            return;
        }

        if (manager.isNameTaken(name)) {
            messageLabel.setText("Nama sudah digunakan");
            return;
        }

        try {
            Character character = new Character(name, type, level);
            if (manager.addCharacter(character)) {
                messageLabel.setText("Karakter berhasil disimpan");
                refreshList();
            } else {
                messageLabel.setText("Nama sudah digunakan");
            }
        } catch (IllegalArgumentException ex) {
            messageLabel.setText(ex.getMessage());
        }
    }

    @FXML
    public void handleReset() {
        nameField.clear();
        typeCombo.getSelectionModel().clearSelection();
        levelSpinner.getValueFactory().setValue(1);
        messageLabel.setText("");
    }

    public void refreshList() {
        cardsBox.getChildren().clear();

        if (manager != null) {
            for (Character character : manager.getAllCharacters()) {
                Label card = new Label(String.format("%s  •  %s  •  Level %d  •  HP %d  •  ATK %d",
                        character.getName(), character.getType(), character.getLevel(),
                        character.getHitPoints(), character.getAttackPower()));
                card.setStyle("-fx-background-color: white; -fx-border-color: #d5d8dc; "
                        + "-fx-border-radius: 6; -fx-background-radius: 6;");
                card.setPadding(new Insets(10));
                card.setMaxWidth(Double.MAX_VALUE);
                cardsBox.getChildren().add(card);
            }

            int count = manager.getTotalCharacters();
            countLabel.setText("Jumlah: " + count);
            avgLevelLabel.setText(String.format("Rata-rata level: %.2f", manager.getAverageLevel()));
            statusLabel.setText(count + " karakter tersimpan");
        } else {
            countLabel.setText("Jumlah: 0");
            avgLevelLabel.setText("Rata-rata level: 0.00");
            statusLabel.setText("Siap");
        }
    }
}
