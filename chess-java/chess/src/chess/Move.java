package chess;

public class Move {
    private Player player;
    private Tile start;
    private Tile end;
    private Piece movedPiece;
    private Piece killedPiece;

    public Move(Player player, Tile start, Tile end, Piece movedPiece, Piece killedPiece) {
        this.player = player;
        this.start = start;
        this.end = end;
        this.movedPiece = movedPiece;
        this.killedPiece = killedPiece;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tile getStart() {
        return start;
    }

    public void setStart(Tile start) {
        this.start = start;
    }

    public Tile getEnd() {
        return end;
    }

    public void setEnd(Tile end) {
        this.end = end;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public void setMovedPiece(Piece movedPiece) {
        this.movedPiece = movedPiece;
    }

    public Piece getKilledPiece() {
        return killedPiece;
    }

    public void setKilledPiece(Piece killedPiece) {
        this.killedPiece = killedPiece;
    }
}
