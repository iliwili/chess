package chess.debug;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class DebugToolBar extends ToolBar {
    private final Button resetGame;
    private final Button nextTurn;
    private final Button emptyBoard;

    public DebugToolBar() {
        resetGame = new Button("Reset");
        nextTurn = new Button("Next");
        emptyBoard = new Button("Empty");

        getItems().addAll(resetGame, nextTurn, emptyBoard);
    }

    public Button getResetGame() {
        return resetGame;
    }

    public Button getNextTurn() {
        return nextTurn;
    }

    public Button getEmptyBoard() {
        return emptyBoard;
    }
}
