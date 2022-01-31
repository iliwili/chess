package chess;

import javafx.geometry.Insets;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PlayerCard extends VBox {
    private Player player;
    private boolean isCurrentTurn;

    private final Effect glow = new Glow(0.5);
    private final Text usernameText = new Text();
    private final ImageView userImage = new ImageView();
    private final Rectangle clip = new Rectangle();


    public PlayerCard(Player player, boolean isCurrentTurn) {
        this.player = player;
        this.isCurrentTurn = isCurrentTurn;

        initializePlayerCard();
    }

    public void initializePlayerCard() {
        // player card defaults
        setStyle("-fx-background-color: #605d5a");
        setPadding(new Insets(5, 0, 5, 5));

        // username text default
        usernameText.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        usernameText.setFill(Color.WHITE);

        // user image default
        userImage.setFitWidth(50);
        userImage.setFitHeight(50);
        userImage.setStyle("-fx-border-width: 20;-fx-border-color: green;");


        // rectangle default
        clip.setWidth(userImage.getFitWidth());
        clip.setHeight(userImage.getFitHeight());
        clip.setArcWidth(10);
        clip.setArcHeight(10);

        refreshPlayerCard();
    }

    public void refreshPlayerCard() {
        getChildren().clear();

        HBox userBox = new HBox();

        // user image
        Image image = new Image(getClass().getResourceAsStream("/user-profile/" + this.player.getProfileImg()));
        userImage.setImage(image);
        userImage.setClip(clip);

        userBox.getChildren().add(userImage);

        VBox usernameKilledPiecesBox = new VBox();
        usernameText.setText(this.player.getUsername());

        if (isCurrentTurn) {
            clip.setEffect(glow);
            usernameText.setEffect(glow);
        } else {
            clip.setEffect(null);
            usernameText.setEffect(null);
        }
        usernameKilledPiecesBox.getChildren().add(usernameText);

        HBox killedPiecesBox = new HBox();
        killedPiecesBox.setPrefHeight(30);

//        Map<String, Integer> killedPieces = getKilledImages(this.player.getKilledPieces());
//        for (String key : killedPieces.keySet()) {
//
//            if (killedPieces.get(key) != 0) {
//                System.out.println((this.player.isWhiteSide() ? "white" : "black") + "/" + key + "-" + killedPieces.get(key));
//
//                Image image = new Image(getClass().getResourceAsStream("/chess-pieces/killed/" + (this.player.isWhiteSide() ? "white" : "black") + "/" + key + "-" + killedPieces.get(key) + ".png"));
//                ImageView pieceImage = new ImageView(image);
//                pieceImage.setFitHeight(30);
//                pieceImage.setStyle("-fx-padding: 5 5 5 5;");
//                killedPiecesBox.getChildren().add(pieceImage);
//            }
//        }

        for (Piece killedPiece : this.player.getKilledPieces()) {
            ImageView pieceImage = new ImageView(new Image(getClass().getResourceAsStream("/chess-pieces/neo/" + killedPiece.getImage())));
            
            // piece image default
            pieceImage.setFitWidth(25);
            pieceImage.setFitHeight(25);

            killedPiecesBox.getChildren().add(pieceImage);
        }

        usernameKilledPiecesBox.getChildren().add(killedPiecesBox);

        usernameKilledPiecesBox.setPadding(new Insets(0, 0, 0, 10));
        userBox.getChildren().add(usernameKilledPiecesBox);
        getChildren().add(userBox);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isCurrentTurn() {
        return isCurrentTurn;
    }

    public void setCurrentTurn(boolean currentTurn) {
        isCurrentTurn = currentTurn;
    }

//    public Map<String, Integer> getKilledImages(List<chess.Piece> killedPieces) {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("pawn", 0);
//        map.put("rook", 0);
//        map.put("knight", 0);
//        map.put("bishop", 0);
//        map.put("queen", 0);
//
//        for (chess.Piece killedPiece : killedPieces) {
//            String key = killedPiece.getClass().getName().toLowerCase();
//            map.put(key, map.get(key) + 1);
//        }
//
//        return map;
//    }
}
