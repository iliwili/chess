import { Piece } from "../Piece";

export class Pawn extends Piece {
  constructor(type, x, y) {
    super("pawn", "Pa", type, x, y);
    this.firstMove = true;
  }

  getMoves(board) {
    let moves = [];

    if (this.type === "black") {
      if (this.firstMove) {
        for (let i = 1; i <= 2; i++) {
          if (board.tiles[this.x + i][this.y].state === "EMPTY") {
            moves.push([this.x + i, this.y]);
          } else {
            break;
          }
        }
      } else {
        if (board.tiles[this.x + 1][this.y].state === "EMPTY") {
          moves.push([this.x + 1, this.y]);
        }
      }
    } else {
      if (this.firstMove) {
        for (let i = 1; i <= 2; i++) {
          if (board.tiles[this.x - i][this.y].state === "EMPTY") {
            moves.push([this.x - i, this.y]);
          } else {
            break;
          }
        }
      } else {
        if (board.tiles[this.x - 1][this.y].state === "EMPTY") {
          moves.push([this.x - 1, this.y]);
        }
      }
    }

    return moves;
  }

  canMove(board, x, y) {
    console.log("can move: ", board.tiles[x][y].piece.name);
  }
}
