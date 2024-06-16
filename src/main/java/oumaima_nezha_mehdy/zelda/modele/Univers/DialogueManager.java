package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.ArrayList;

public class DialogueManager {
    private Pane dialoguePane;
    private Label dialogueLabel;
    private List<String> dialogues;
    private int currentDialogueIndex;

    public DialogueManager(Pane dialoguePane, Label dialogueLabel) {
        this.dialoguePane = dialoguePane;
        this.dialogueLabel = dialogueLabel;
        this.dialogues = new ArrayList<>();
        this.currentDialogueIndex = 0;

        // Initialize the dialogue pane
        this.dialoguePane.setVisible(false);
    }

    public void startDialogue(List<String> dialogues) {
        this.dialogues = dialogues;
        this.currentDialogueIndex = 0;
        showNextDialogue();
    }

    public void showNextDialogue() {
        if (currentDialogueIndex < dialogues.size()) {
            dialogueLabel.setText(dialogues.get(currentDialogueIndex));
            dialoguePane.setVisible(true);
            currentDialogueIndex++;
        } else {
            endDialogue();
        }
    }

    public void endDialogue() {
        dialoguePane.setVisible(false);
    }
}
