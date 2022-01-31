package chess;

import chess.debug.DebugToolBar;
import chess.debug.Mode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Game extends Application {
    private Board board;
    private Player currentTurn = new Player();
    private int turn;
    private List<Move> moves;

    private final Player[] players = new Player[2];
    private final PlayerCard[] playerCards = new PlayerCard[2];


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {
        Parameters params = getParameters();
        List<String> listParams = params.getRaw();
        if (listParams.isEmpty()) {
            throw new IOException("no params were given");
        }
        Mode mode = Mode.valueOf(listParams.get(0).split("=")[1]);

        initializeGame();

        BorderPane debugPane = new BorderPane();
        BorderPane gamePane = new BorderPane();

        if (mode.equals(Mode.DEBUG)) {
            DebugToolBar debugToolBar = new DebugToolBar();
            debugToolBar.getResetGame().setOnMouseClicked(e -> resetGame());
            debugToolBar.getNextTurn().setOnMouseClicked(e -> nextTurn());
            debugToolBar.getEmptyBoard().setOnMouseClicked(e -> emptyTheBoard());

            debugPane.setTop(debugToolBar);
            debugPane.setCenter(gamePane);
        }

        gamePane.setCenter(board);

        gamePane.setTop(playerCards[0]);
        gamePane.setBottom(playerCards[1]);

        Scene debugScene = new Scene(debugPane);

        stage.setTitle("CHESS");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/chess-logo.png")));
        stage.setResizable(false);

        if (mode.equals(Mode.DEBUG)) {
            stage.setScene(debugScene);
        } else {
            Scene gameScene = new Scene(gamePane);
            stage.setScene(gameScene);
        }

        stage.show();
    }

    public void resetGame() {
        this.board.resetBoard();

        this.players[0].getKilledPieces().clear();
        this.players[1].getKilledPieces().clear();

        this.currentTurn = players[0];

        this.playerCards[0].setCurrentTurn(!this.playerCards[0].isCurrentTurn());
        this.playerCards[1].setCurrentTurn(!this.playerCards[1].isCurrentTurn());

        this.turn = 0;

        this.moves.clear();
        refreshPlayerCards();
    }

    public void initializeGame() {
        this.board = new Board(this);
        this.players[0] = new Player("iliwili", true, "");
        this.players[1] = new Player("enemy", false, "");

        this.playerCards[0] = new PlayerCard(this.players[0], true);
        this.playerCards[1] = new PlayerCard(this.players[1], false);

        this.currentTurn = players[0];
        this.turn = 0;

        // initialize moves list
        this.moves = new LinkedList<>();
        this.board.resetBoard();
    }

    public void refreshGame() {
        this.board.refreshBoard();
        refreshPlayerCards();
    }

    public void refreshPlayerCards() {
        for (PlayerCard playerCard : playerCards) {
            playerCard.refreshPlayerCard();
        }
    }

    public Player getCurrentTurn() {
        return this.currentTurn;
    }

    public void nextTurn() {
        if (this.currentTurn.equals(players[0])) {
            this.currentTurn = players[1];
        } else {
            this.currentTurn = players[0];
        }
        this.playerCards[0].setCurrentTurn(!this.playerCards[0].isCurrentTurn());
        this.playerCards[1].setCurrentTurn(!this.playerCards[1].isCurrentTurn());
        this.turn++;

        refreshGame();
    }

    public void emptyTheBoard() {
        this.board.emptyBoard();
    }

    public void movePiece(Tile start, Tile end, Piece movedPiece, Piece killedPiece) {

        if (killedPiece != null)
            Arrays.stream(getPlayers()).filter(p -> p.equals(getCurrentTurn())).findFirst().get().addToKilledPieces(killedPiece);

        moves.add(new Move(Arrays.stream(getPlayers()).filter(p -> p.equals(getCurrentTurn())).findFirst().get(),start, end, movedPiece, killedPiece));

        nextTurn();
    }

    public void addToMove(Move move) {
        if (move != null) {
            this.moves.add(move);
        } else {
            System.err.println("GEEM MOVE");
        }
    }

    public int getTurn() {
        return turn;
    }

    public Player[] getPlayers() {
        return players;
    }
}
