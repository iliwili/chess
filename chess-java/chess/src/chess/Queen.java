package chess;

import java.util.LinkedList;
import java.util.List;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "wq.png" : "bq.png");
    }

    @Override
    public void setFirstTurn(boolean firstTurn) {

    }

    @Override
    public boolean isFirstTurn() {
        return false;
    }

    @Override
    public List<Tile> calculateLegalMoves(Board board, int x, int y) {
        List<Tile> possibleMoves = new LinkedList<>();

        // top diagonal right
        possibleMoves.addAll(board.getDiagonalTopRight(x, y));

        // top diagonal left
        possibleMoves.addAll(board.getDiagonalTopLeft(x, y));

        // bot diagonal right
        possibleMoves.addAll(board.getDiagonalBotRight(x, y));

        // bot diagonal left
        possibleMoves.addAll(board.getDiagonalBotLeft(x, y));

        // right
        possibleMoves.addAll(board.getRight(x, y));

        // left
        possibleMoves.addAll(board.getLeft(x, y));

        // top
        possibleMoves.addAll(board.getTop(x, y));

        // bot
        possibleMoves.addAll(board.getBot(x, y));

        return possibleMoves;
    }
}
