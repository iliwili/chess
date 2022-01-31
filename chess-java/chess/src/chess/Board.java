package chess;

import chess.debug.management.PieceMenu;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Board extends Pane {
    private Game game;
    private GridPane chessboard;
    private Tile[][] tiles;
    private Tile selectedTile;
    private List<Tile> possibleMoves;

    public static final String[] LETTERS = {"A","B","C","D","E","F","G","H"};
    public static final String[] NUMBERS = {"8","7","6","5","4","3","2","1"};

    public Board(Game game) {
        this.game = game;
        this.chessboard = new GridPane();

        setPrefSize(640, 640);

        getChildren().add(this.chessboard);
        setText(this);

        onClick();
    }

    public void onClick() {
        setOnMousePressed(e -> {
            // get x and y on gridpane
            Node clickedNode = e.getPickResult().getIntersectedNode();
            int col = GridPane.getColumnIndex(clickedNode instanceof ImageView || clickedNode instanceof Circle ? clickedNode.getParent() : clickedNode);
            int row = GridPane.getRowIndex(clickedNode instanceof ImageView || clickedNode instanceof Circle ? clickedNode.getParent() : clickedNode);

            Tile tile = getTile(row, col);

            if (e.isMiddleButtonDown()) {
                System.out.println(col + ":" + row + " = " + (tile.getPiece() != null ? tile.getPiece().getClass().getName() + tile.getCoord() : "empty" + tile.getCoord()));
            } else {
                if (tile.getPiece() != null && (tile.getPiece().isWhite() == this.game.getCurrentTurn().isWhiteSide())) {
                    if (alreadySelected(tile)) {
                        unSelectTile();
                    } else {
                        selectTile(tile);
                    }
                } else {
                    if (getSelectedTile() != null) {
                        if (canMove(tile)) {
                            movePiece(getSelectedTile(), tile);
                        }
                    }
                }
            }
        });
    }

    public void resetBoard() {
        // initialize tiles
        this.tiles = new Tile[8][8];

        // initialize possible moves list
        this.possibleMoves = new LinkedList<>();

        // initialize white pieces
        tiles[0][0] = new Tile(0, 0, new Rook(true), TileState.MOVABLE);
        tiles[0][1] = new Tile(0, 1, new Knight(true), TileState.MOVABLE);
        tiles[0][2] = new Tile(0, 2, new Bishop(true), TileState.MOVABLE);
        tiles[0][3] = new Tile(0, 3, new King(true), TileState.MOVABLE);
        tiles[0][4] = new Tile(0, 4, new Queen(true), TileState.MOVABLE);
        tiles[0][5] = new Tile(0, 5, new Bishop(true), TileState.MOVABLE);
        tiles[0][6] = new Tile(0, 6, new Knight(true), TileState.MOVABLE);
        tiles[0][7] = new Tile(0, 7, new Rook(true), TileState.MOVABLE);

        for (int i = 0; i < 8; i++) {
            tiles[1][i] = new Tile(1, i, new Pawn(true), TileState.MOVABLE);
        }

        // initialize black pieces
        tiles[7][0] = new Tile(7, 0, new Rook(false), TileState.DEFAULT);
        tiles[7][1] = new Tile(7, 1, new Knight(false), TileState.DEFAULT);
        tiles[7][2] = new Tile(7, 2, new Bishop(false), TileState.DEFAULT);
        tiles[7][3] = new Tile(7, 3, new King(false), TileState.DEFAULT);
        tiles[7][4] = new Tile(7, 4, new Queen(false), TileState.DEFAULT);
        tiles[7][5] = new Tile(7, 5, new Bishop(false), TileState.DEFAULT);
        tiles[7][6] = new Tile(7, 6, new Knight(false), TileState.DEFAULT);
        tiles[7][7] = new Tile(7, 7, new Rook(false), TileState.DEFAULT);
        for (int i = 0; i < 8; i++) {
            tiles[6][i] = new Tile(6, i, new Pawn(false), TileState.DEFAULT);
        }

        // initialize remaining boxes without any piece
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(i, j, null, TileState.DEFAULT);
            }
        }

        // add all
        this.chessboard.getChildren().clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.chessboard.add(getTile(i, j), j, i);

                Tile tile = getTile(i, j);

                getTile(i, j).setOnContextMenuRequested(e -> {
                    new PieceMenu(this, tile.getX(), tile.getY()).show(tile, e.getScreenX(), e.getScreenY());
                });

                getTile(i, j).setNumber(NUMBERS[i]);
                getTile(i, j).setLetter(LETTERS[j]);
            }
        }
    }

    private void setText(Pane pane) {
        for (int i = 0; i < 8; i++) { //Set letters on bottom top side.
            Text text = new Text(LETTERS[i]);
            text.setFont(new Font(13));
            text.setX((i*80)+65);  text.setY(635);
            pane.getChildren().add(text);
        }

        for (int i = 0; i < 8; i++) { //Set numbers on the left side.
            Text text = new Text(NUMBERS[i]);
            text.setFont(new Font(15));
            text.setX(5);  text.setY((i*80)+20);
            pane.getChildren().add(text);
        }
    }

    public void refreshBoard() {
        this.chessboard.getChildren().clear();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.chessboard.add(getTile(i, j), j, i);

                if (getTile(i, j).getPiece() != null && (getTile(i, j).getPiece().isWhite() == this.game.getCurrentTurn().isWhiteSide())) {
                    getTile(i, j).setTileState(TileState.MOVABLE);
                }

                getTile(i, j).setSelected(getTile(i, j).equals(getSelectedTile()));
            }
        }
    }

    public void emptyBoard() {
        this.chessboard.getChildren().clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(i, j, null, TileState.DEFAULT);
                this.chessboard.add(getTile(i, j), j, i);

                Tile tile = getTile(i, j);

                getTile(i, j).setOnContextMenuRequested(e -> {
                    new PieceMenu(this, tile.getX(), tile.getY()).show(tile, e.getScreenX(), e.getScreenY());
                });
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public boolean alreadySelected(Tile tile) {
        return tile.equals(getSelectedTile());
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
    }

    public void selectTile(Tile tile) {
        setSelectedTile(getTile(tile.getX(), tile.getY()));

        removeMovableTiles();

        // check if king is in danger
        // if king is in daner limit moves to only kind

        clearPossibleMoves();
        this.possibleMoves.addAll(this.getSelectedTile().getPiece().calculateLegalMoves(this, getSelectedTile().getX(), getSelectedTile().getY()));
        for (Tile possibleTile : this.possibleMoves) {
            getTile(possibleTile.getX(), possibleTile.getY()).setTileState(TileState.OPEN_TILE);
        }

        refreshBoard();
    }

    public void unSelectTile() {
        setSelectedTile(null);

        removeMovableTiles();

        refreshBoard();
    }

    public boolean canMove(Tile moveTo) {
        Optional<Tile> foundTile = this.possibleMoves.stream().filter(t -> t.equals(moveTo)).findAny();
        return foundTile.isPresent();
    }

    public void movePiece(Tile start, Tile end) {
        removeMovableTiles();
        setSelectedTile(null);

        // if pawn set first turn as false for next turn
        if (start.getPiece() instanceof Pawn) {
            this.tiles[start.getX()][start.getY()].getPiece().setFirstTurn(false);
        }

        // check if match is won => is king dead or player has no choices

        this.tiles[end.getX()][end.getY()].setPiece(start.getPiece());
        this.tiles[start.getX()][start.getY()].setPiece(null);

        // TODO killedpiece is just move piece
        this.game.movePiece(start,
                end,
                start.getPiece(),
                /* KilledPiece */ end.getPiece() != null ? end.getPiece() : null);
    }

    public boolean validTile(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    public void clearPossibleMoves() {
        this.possibleMoves.clear();
    }

    public void removeMovableTiles() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                getTile(i, j).setTileState(TileState.DEFAULT);
            }
        }
    }

    public void showBoard() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (getTile(i, j).getPiece() == null) {
                    if (getTile(i, j).getTileState() == TileState.OPEN_TILE) {
                        System.out.print("MO ");
                    } else {
                        System.out.print("NN" + " ");
                    }
                } else if (getTile(i, j).getPiece().isWhite()) {
                    System.out.print(getTile(i, j).getPiece().getClass().getSimpleName().substring(0, 2).toUpperCase() + " ");
                } else {
                    System.out.print(getTile(i, j).getPiece().getClass().getSimpleName().substring(0, 2).toUpperCase() + " ");
                }
            }
            System.out.println();
        }
    }

    public void addPieceToBoard(int x, int y, Piece piece) {
        this.tiles[x][y].setPiece(piece);

        refreshBoard();
    }

    // shared legal moves
    public Tile checkNeighbourRightAhead(Tile tile) {
        if (tile.getPiece().isWhite()) {
            int newX = tile.getX() + 1;
            int newY = tile.getY() + 1;

            if (validTile(newX, newY)) {
                if (getTile(newX, newY).getPiece() != null) {
                    if (getTile(newX, newY).getPiece().isOponnent(getTile(tile.getX(), tile.getY()).getPiece())) {
                        return getTile(newX, newY);
                    }
                }
            }
        } else {
            int newX = tile.getX() - 1;
            int newY = tile.getY() + 1;

            if (validTile(newX, newY)) {
                if (getTile(newX, newY).getPiece() != null) {
                    if (getTile(newX, newY).getPiece().isOponnent(getTile(tile.getX(), tile.getY()).getPiece())) {
                        return getTile(newX, newY);
                    }
                }
            }
        }
        return null;
    }

    public Tile checkNeighbourLeftAhead(Tile tile) {
        if (tile.getPiece().isWhite()) {
            int newX = tile.getX() + 1;
            int newY = tile.getY() - 1;

            if (validTile(newX, newY)) {
                if (getTile(newX, newY).getPiece() != null) {
                    if (getTile(newX, newY).getPiece().isOponnent(getTile(tile.getX(), tile.getY()).getPiece())) {
                        return getTile(newX, newY);
                    }
                }
            }
        } else {
            int newX = tile.getX() - 1;
            int newY = tile.getY() - 1;

            if (validTile(newX, newY)) {
                if (getTile(newX, newY).getPiece() != null) {
                    if (getTile(newX, newY).getPiece().isOponnent(getTile(tile.getX(), tile.getY()).getPiece())) {
                        return getTile(newX, newY);
                    }
                }
            }
        }
        return null;
    }

    public List<Tile> getTop(int x, int y) {
        List<Tile> moves = new LinkedList<>();

        for (int i = 1; i < 8; i++) {
            if (validTile(x - i, y) && getTile(x - i, y).getPiece() == null) {
                moves.add(getTile(x - i, y));
            } else {
                if (validTile(x - i, y)) {
                    if (getTile(x - i, y).getPiece() != null) {
                        if (getTile(x - i, y).getPiece().isOponnent(getTile(x, y).getPiece())) {
                            moves.add(getTile(x - i, y));
                        }
                    }
                }
                break;
            }
        }

        return moves;
    }

    public List<Tile> getBot(int x, int y) {
        List<Tile> moves = new LinkedList<>();

        for (int i = 1; i < 8; i++) {
            if (validTile(x + i, y) && getTile(x + i, y).getPiece() == null) {
                moves.add(getTile(x + i, y));
            } else {
                if (validTile(x + i, y)) {
                    if (getTile(x + i, y).getPiece() != null) {
                        if (getTile(x + i, y).getPiece().isOponnent(getTile(x, y).getPiece())) {
                            moves.add(getTile(x + i, y));
                        }
                    }
                }
                break;
            }
        }

        return moves;
    }

    public List<Tile> getRight(int x, int y) {
        List<Tile> moves = new LinkedList<>();

        for (int i = 1; i < 8; i++) {
            if (validTile(x, y + i) && getTile(x, y + i).getPiece() == null) {
                moves.add(getTile(x, y + i));
            } else {
                if (validTile(x, y + i)) {
                    if (getTile(x, y + i).getPiece() != null) {
                        if (getTile(x, y + i).getPiece().isOponnent(getTile(x, y).getPiece())) {
                            moves.add(getTile(x, y + i));
                        }
                    }
                }
                break;
            }
        }

        return moves;
    }

    public List<Tile> getLeft(int x, int y) {
        List<Tile> moves = new LinkedList<>();

        for (int i = 1; i < 8; i++) {
            if (validTile(x, y - i) && getTile(x, y - i).getPiece() == null) {
                moves.add(getTile(x, y - i));
            } else {
                if (validTile(x, y - i)) {
                    if (getTile(x, y - i).getPiece() != null) {
                        if (getTile(x, y - i).getPiece().isOponnent(getTile(x, y).getPiece())) {
                            moves.add(getTile(x, y - i));
                        }
                    }
                }
                break;
            }
        }

        return moves;
    }

    public List<Tile> getDiagonalTopRight(int x, int y) {
        List<Tile> movesTopRight = new LinkedList<>();

        for (int i = 1; i < 8; i++) {
            if (validTile(x - i, y + i) && getTile(x - i, y + i).getPiece() == null) {
                movesTopRight.add(getTile(x - i, y + i));
            } else {
                if (validTile(x - i, y + i)) {
                    if (getTile(x - i, y + i).getPiece() != null) {
                        if (getTile(x - i, y + i).getPiece().isOponnent(getTile(x, y).getPiece())) {
                            movesTopRight.add(getTile(x - i, y + i));
                        }
                    }
                }
                break;
            }
        }

        return movesTopRight;
    }

    public List<Tile> getDiagonalTopLeft(int x, int y) {
        List<Tile> movesTopLeft = new LinkedList<>();

        for (int i = 1; i < 8; i++) {
            if (validTile(x - i, y - i) && getTile(x - i, y - i).getPiece() == null) {
                movesTopLeft.add(getTile(x - i, y - i));
            } else {
                if (validTile(x - i, y - i)) {
                    if (getTile(x - i, y - i).getPiece() != null) {
                        if (getTile(x - i, y - i).getPiece().isOponnent(getTile(x, y).getPiece())) {
                            movesTopLeft.add(getTile(x - i, y - i));
                        }
                    }
                }
                break;
            }
        }

        return movesTopLeft;
    }

    public List<Tile> getDiagonalBotRight(int x, int y) {
        List<Tile> movesBotRight = new LinkedList<>();

        for (int i = 1; i < 8; i++) {
            if (validTile(x + i, y + i) && getTile(x + i, y + i).getPiece() == null) {
                movesBotRight.add(getTile(x + i, y + i));
            } else {
                if (validTile(x + i, y + i)) {
                    if (getTile(x + i, y + i).getPiece() != null) {
                        if (getTile(x + i, y + i).getPiece().isOponnent(getTile(x, y).getPiece())) {
                            movesBotRight.add(getTile(x + i, y + i));
                        }
                    }
                }
                break;
            }
        }

        return movesBotRight;
    }

    public List<Tile> getDiagonalBotLeft(int x, int y) {
        List<Tile> movesBotLeft = new LinkedList<>();

        for (int i = 1; i < 8; i++) {
            if (validTile(x + i, y - i) && getTile(x + i, y - i).getPiece() == null) {
                movesBotLeft.add(getTile(x + i, y - i));
            } else {
                if (validTile(x + i, y - i)) {
                    if (getTile(x + i, y - i).getPiece() != null) {
                        if (getTile(x + i, y - i).getPiece().isOponnent(getTile(x, y).getPiece())) {
                            movesBotLeft.add(getTile(x + i, y - i));
                        }
                    }
                }
                break;
            }
        }

        return movesBotLeft;
    }
}
