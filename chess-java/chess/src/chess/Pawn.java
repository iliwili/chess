package chess;

import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece {
    private boolean firstTurn;

    public Pawn(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "wp.png" : "bp.png");
        this.firstTurn = true;
    }

    @Override
    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }

    @Override
    public boolean isFirstTurn() {
        return firstTurn;
    }

    @Override
    public List<Tile> calculateLegalMoves(Board board, int x, int y) {
        List<Tile> possibleMoves = new LinkedList<>();

        if (isWhite()) {
            // check neigbours
            if (board.checkNeighbourRightAhead(board.getTile(x, y)) != null) {
                possibleMoves.add(board.checkNeighbourRightAhead(board.getTile(x, y)));
            }

            if (board.checkNeighbourLeftAhead(board.getTile(x, y)) != null) {
                possibleMoves.add(board.checkNeighbourLeftAhead(board.getTile(x, y)));
            }

            if (firstTurn) {
                for (int i = 1; i <= 2; i++) {
                    if (board.validTile(x + i, y) && board.getTile(x + i, y).getPiece() == null) {
                        possibleMoves.add(board.getTile(x + i, y));
                    } else {
                        break;
                    }
                }
            } else {
                if (board.validTile(x + 1, y) && board.getTile(x + 1, y).getPiece() == null) {
                    possibleMoves.add(board.getTile(x + 1, y));
                }
            }
        } else {
            // check neigbours
            if (board.checkNeighbourRightAhead(board.getTile(x, y)) != null) {
                possibleMoves.add(board.checkNeighbourRightAhead(board.getTile(x, y)));
            }

            if (board.checkNeighbourLeftAhead(board.getTile(x, y)) != null) {
                possibleMoves.add(board.checkNeighbourLeftAhead(board.getTile(x, y)));
            }

            if (firstTurn) {
                for (int i = 1; i <= 2; i++) {
                    if (board.validTile(x - i, y) && board.getTile(x - i, y).getPiece() == null) {
                        possibleMoves.add(board.getTile(x - i, y));
                    } else {
                        break;
                    }
                }
            } else {
                if (board.validTile(x  - 1, y) && board.getTile(x - 1, y).getPiece() == null) {
                    possibleMoves.add(board.getTile(x - 1, y));
                }
            }
        }
        return possibleMoves;
    }
}
