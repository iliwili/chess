package chess;

import java.util.LinkedList;
import java.util.List;

public class Knight extends Piece {
    private final int[] offsetsX = {-2, -2, -1, 1, 2, 2, 1, -1};
    private final int[] offsetsY = {-1, 1, 2, 2, -1, 1, -2, -2};

    public Knight(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "wn.png" : "bn.png");
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
        List<Tile> moves = new LinkedList<>();

        for (int i = 0; i < 8; i++) {
            int newX = x + offsetsX[i];
            int newY = y + offsetsY[i];

            if (board.validTile(newX, newY) && board.getTile(newX, newY).getPiece() == null) {
                moves.add(board.getTile(newX, newY));
            } else {
                if (board.validTile(newX, newY)) {
                    if (board.getTile(newX, newY).getPiece() != null) {
                        if (board.getTile(newX, newY).getPiece().isOponnent(board.getTile(x, y).getPiece())) {
                            moves.add(board.getTile(x + offsetsX[i], y + offsetsY[i]));
                        }
                    }
                }
            }
        }
        return moves;
    }
}
