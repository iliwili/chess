package chess;

import java.util.LinkedList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "wb.png" : "bb.png");
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

        possibleMoves.addAll(board.getDiagonalTopRight(x, y));
        possibleMoves.addAll(board.getDiagonalTopLeft(x, y));
        possibleMoves.addAll(board.getDiagonalBotRight(x, y));
        possibleMoves.addAll(board.getDiagonalBotLeft(x, y));



        return possibleMoves;
    }
}
