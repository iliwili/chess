package chess;

import java.util.LinkedList;
import java.util.List;

public class Rook extends Piece {

    private final int[] offsets = {
            1, 2
    };

    public Rook(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "wr.png" : "br.png");
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
