package chess.debug.management;

import chess.Board;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceMenu extends ContextMenu {

    public PieceMenu(Board board, int x, int y) {

        // menu items
        Menu pawnItem = new Menu("Pawn", getMenuItemImage("bp"));
        pawnItem.setId("pw");
        Menu rookItem = new Menu("Rook", getMenuItemImage("br"));
        rookItem.setId("rk");
        Menu knightItem = new Menu("Knight", getMenuItemImage("bn"));
        knightItem.setId("kn");
        Menu bishopItem = new Menu("Bishop", getMenuItemImage("bb"));
        bishopItem.setId("bi");
        Menu kingItem = new Menu("King", getMenuItemImage("bk"));
        kingItem.setId("ki");
        Menu queenItem = new Menu("Queen", getMenuItemImage("bq"));
        queenItem.setId("qu");

        // add the submenu's
        pawnItem.getItems().addAll(new PieceMenuItem("white", true, board, x, y), new PieceMenuItem("black", false, board, x, y));
        rookItem.getItems().addAll(new PieceMenuItem("white", true, board, x, y), new PieceMenuItem("black", false, board, x, y));
        knightItem.getItems().addAll(new PieceMenuItem("white", true, board, x, y), new PieceMenuItem("black", false, board, x, y));
        bishopItem.getItems().addAll(new PieceMenuItem("white", true, board, x, y), new PieceMenuItem("black", false, board, x, y));
        kingItem.getItems().addAll(new PieceMenuItem("white", true, board, x, y), new PieceMenuItem("black", false, board, x, y));
        queenItem.getItems().addAll(new PieceMenuItem("white", true, board, x, y), new PieceMenuItem("black", false, board, x, y));

        getItems().addAll(pawnItem, rookItem, knightItem, bishopItem, kingItem, queenItem);
    }

    public ImageView getMenuItemImage(String piece) {
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/chess-pieces/neo/" + piece + ".png")));
        img.setFitWidth(18);
        img.setFitHeight(18);
        return img;
    }
}
