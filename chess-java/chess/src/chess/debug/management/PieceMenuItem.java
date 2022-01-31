package chess.debug.management;

import chess.*;
import javafx.scene.control.MenuItem;

public class PieceMenuItem extends MenuItem {
    private Board board;

    public PieceMenuItem(String s, boolean isWhite, Board board, int x, int y) {
        super(s);
        this.board = board;

        setOnAction(e -> {
            switch (this.getParentMenu().getId()) {
                case "pw":
                    this.board.addPieceToBoard(x,y, new Pawn(isWhite));
                    break;
                case "rk":
                    this.board.addPieceToBoard(x,y, new Rook(isWhite));
                    break;
                case "kn":
                    this.board.addPieceToBoard(x, y, new Knight(isWhite));
                    break;
                case "bi":
                    this.board.addPieceToBoard(x, y, new Bishop(isWhite));
                    break;
                case "ki":
                    this.board.addPieceToBoard(x, y, new King(isWhite));
                    break;
                case "qu":
                    this.board.addPieceToBoard(x, y, new Queen(isWhite));
                    break;
                default:
            }
        });
    }
}
