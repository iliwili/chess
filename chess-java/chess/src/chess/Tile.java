package chess;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Tile extends StackPane {
    private Piece piece;
    private int x;
    private int y;
    private TileState tileState;
    private String letter;
    private int number;

    public Tile(int x, int y, Piece piece, TileState tileState) {
        this.piece = piece;
        this.x = x;
        this.y = y;
        this.tileState = tileState;

        refreshTile();

        setMinSize(80, 80);
        setMaxSize(80, 80);

        setBackground(new Background(new BackgroundFill(getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void refreshTile() {
        getChildren().clear();

        if (this.tileState.equals(TileState.MOVABLE)) {
            setStyle("-fx-cursor: hand;");
        } else {
            setStyle("-fx-cursor: default;");
        }

        ImageView pieceImage = new ImageView();
        pieceImage.setFitHeight(64);
        pieceImage.setFitWidth(64);

        if (this.piece != null) {
            pieceImage.setImage(new Image(getClass().getResourceAsStream("/chess-pieces/neo/" + this.piece.getImage())));
        }

        Circle movableCircle = new Circle();
        Circle killablePieceCircle = new Circle();

        if (this.tileState.equals(TileState.OPEN_TILE)) {
            movableCircle.setRadius(15);
            movableCircle.setFill(Color.rgb(0,0,0, 0.1));

            killablePieceCircle.setRadius(32);
            killablePieceCircle.setFill(Color.TRANSPARENT);
            killablePieceCircle.setStroke(Color.rgb(0,0,0, 0.1));
            killablePieceCircle.setStrokeWidth(6);

            setStyle("-fx-cursor: hand;");
        }

        if (this.piece != null && this.tileState.equals(TileState.OPEN_TILE)) {
            getChildren().addAll(pieceImage, killablePieceCircle);
        } else if (this.piece != null) {
            getChildren().add(pieceImage);
        } else {
            getChildren().add(movableCircle);
        }

        setBackground(new Background(new BackgroundFill(getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private Color getColor() {
        return x % 2 == 1 && y % 2 == 1 || x % 2 == 0 && y % 2 == 0 ? Color.valueOf("#EAE9D2") : Color.valueOf("#4B7399");
    }

    public void setSelected(boolean selected) {
        if (selected) {
            setBackground(new Background(new BackgroundFill(Color.rgb(0, 165, 255, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            setBackground(new Background(new BackgroundFill(getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCoord() {
        return "[" + this.x + ", " + this.y + "]";
    }

    public TileState getTileState() {
        return tileState;
    }

    public void setTileState(TileState tileState) {
        this.tileState = tileState;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = Integer.parseInt(number);
    }
}
