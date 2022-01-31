package chess;

import java.util.Collections;
import java.util.List;

public class King extends Piece {

    public King(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "wk.png" : "bk.png");
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
        return Collections.emptyList();
    }
}
