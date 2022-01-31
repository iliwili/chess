package chess;

import java.util.List;

public abstract class Piece {
    private boolean isWhite;
    private boolean isKilled;
    private String image;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
        this.isKilled = false;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public boolean isOponnent(Piece piece) {
        if (this.isWhite != piece.isWhite) {
            return true;
        }
        return false;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public abstract void setFirstTurn(boolean firstTurn);

    public abstract boolean isFirstTurn();

    public abstract List<Tile> calculateLegalMoves(Board board, int x, int y);
}
