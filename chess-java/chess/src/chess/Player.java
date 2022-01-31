package chess;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private String username;
    private boolean whiteSide;
    private String profileImg;
    private List<Piece> killedPieces;

    public Player(String username, boolean whiteSide, String profileImg) {
        this.username = username;
        this.whiteSide = whiteSide;
        setProfileImg(profileImg);
        this.killedPieces = new LinkedList<>();
    }

    public Player() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isWhiteSide() {
        return whiteSide;
    }

    public void setWhiteSide(boolean whiteSide) {
        this.whiteSide = whiteSide;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        if (profileImg.isEmpty()) {
            if (isWhiteSide()) {
                this.profileImg = "user-pic-1.png";
            } else {
                this.profileImg = "user-pic-2.png";
            }
        } else {
            this.profileImg = profileImg;
        }
    }

    public List<Piece> getKilledPieces() {
        return killedPieces;
    }

    public void addToKilledPieces(Piece killedPiece) {
        killedPieces.add(killedPiece);
    }
}
