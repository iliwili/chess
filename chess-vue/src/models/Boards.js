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

    this.tiles[0][0] = new Tile(new Rook("black", 0, 0), "FILL", 0, 0);
    this.tiles[0][1] = new Tile(new Knight("black", 0, 1), "FILL", 0, 1);
    this.tiles[0][2] = new Tile(new Bishop("black", 0, 2), "FILL", 0, 2);
    this.tiles[0][3] = new Tile(new Queen("black", 0, 3), "FILL", 0, 3);
    this.tiles[0][4] = new Tile(new King("black", 0, 4), "FILL", 0, 4);
    this.tiles[0][5] = new Tile(new Bishop("black", 0, 5), "FILL", 0, 5);
    this.tiles[0][6] = new Tile(new Knight("black", 0, 6), "FILL", 0, 6);
    this.tiles[0][7] = new Tile(new Rook("black", 0, 7), "FILL", 0, 7);

    this.tiles[7][0] = new Tile(new Rook("white", 7, 0), "FILL", 7, 0);
    this.tiles[7][1] = new Tile(new Knight("white", 7, 1), "FILL", 7, 1);
    this.tiles[7][2] = new Tile(new Bishop("white", 7, 2), "FILL", 7, 2);
    this.tiles[7][3] = new Tile(new Queen("white", 7, 3), "FILL", 7, 3);
    this.tiles[7][4] = new Tile(new King("white", 7, 4), "FILL", 7, 4);
    this.tiles[7][5] = new Tile(new Bishop("white", 7, 5), "FILL", 7, 5);
    this.tiles[7][6] = new Tile(new Knight("white", 7, 6), "FILL", 7, 6);
    this.tiles[7][7] = new Tile(new Rook("white", 7, 7), "FILL", 7, 7);

    for (let i = 1; i <= 6; i++) {
      for (let j = 0; j < 8; j++) {
        this.tiles[i][j] = new Tile(null, "EMPTY", i, j);
      }
    }

    for (let i = 0; i < 8; i++) {
      this.tiles[1][i] = new Tile(new Pawn("black", 1, i), "FILL", 1, i);
    }

    for (let i = 0; i < 8; i++) {
      this.tiles[6][i] = new Tile(new Pawn("white", 6, i), "FILL", 6, i);
    }

    // this.tiles[3][3] = new Tile(new Knight("white", 3, 3), "FILL");
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
