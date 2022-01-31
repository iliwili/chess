import { Rook } from "./pieces/Rook";
import { Knight } from "./pieces/Knight";
import { Bishop } from "./pieces/Bishop";
import { Queen } from "./pieces/Queen";
import { King } from "./pieces/King";
import { Pawn } from "./pieces/Pawn";
import { Tile } from "./Tile";

export class Board {
  constructor() {
    this.tiles = [];
    this.initialize();
  }

  initialize() {
    for (let i = 0; i < 8; i++) {
      this.tiles.push([]);
    }

    this.tiles[0][0] = new Tile(new Rook("black"), "FILL");
    this.tiles[0][1] = new Tile(new Knight("black"), "FILL");
    this.tiles[0][2] = new Tile(new Bishop("black"), "FILL");
    this.tiles[0][3] = new Tile(new Queen("black"), "FILL");
    this.tiles[0][4] = new Tile(new King("black"), "FILL");
    this.tiles[0][5] = new Tile(new Bishop("black"), "FILL");
    this.tiles[0][6] = new Tile(new Knight("black"), "FILL");
    this.tiles[0][7] = new Tile(new Rook("black"), "FILL");

    this.tiles[3][0] = new Tile(new Rook("white"), "FILL");
    this.tiles[7][1] = new Tile(new Knight("white"), "FILL");
    this.tiles[7][2] = new Tile(new Bishop("white"), "FILL");
    this.tiles[7][3] = new Tile(new Queen("white"), "FILL");
    this.tiles[7][4] = new Tile(new King("white"), "FILL");
    this.tiles[7][5] = new Tile(new Bishop("white"), "FILL");
    this.tiles[7][6] = new Tile(new Knight("white"), "FILL");
    this.tiles[7][7] = new Tile(new Rook("white"), "FILL");

    for (let i = 1; i <= 6; i++) {
      for (let j = 0; j < 8; j++) {
        this.tiles[i][j] = new Tile(null, "EMPTY");
      }
    }

    for (let i = 0; i < 8; i++) {
      this.tiles[1][i] = new Tile(new Pawn("black"), "FILL");
    }
    for (let i = 0; i < 8; i++) {
      this.tiles[6][i] = new Tile(new Pawn("white"), "FILL");
    }
  }

  getBoard() {
    return this.tiles;
  }

  showBoardState() {
    let str = "";
    this.tiles.forEach((tile) => {
      tile.forEach((t) => {
        if (t.state !== "EMPTY") {
          str += `${t.piece.prefix} `;
        } else {
          str += "XX ";
        }
      });
      str += "\n";
    });
    console.log(str);
  }
}
