import { Piece } from "../Piece";

export class Pawn extends Piece {
  constructor(type) {
    super("pawn", "Pa", type);
    this.firstMove = true;
  }

  getMoves(board, x, y) {
    let moves = [];

    if (this.type === "black") {
      if (this.firstMove) {
        for (let i = 1; i <= 2; i++) {
          if (board.tiles[x + i][y].state === "EMPTY") {
            moves.push([x + i, y]);
          } else {
            break;
          }
        }
      } else {
        if (board.tiles[x + 1][y].state === "EMPTY") {
          moves.push([x + 1, y]);
        }
      }
    } else {
      if (this.firstMove) {
        for (let i = 1; i <= 2; i++) {
          if (board.tiles[x - i][y].state === "EMPTY") {
            moves.push([x - i, y]);
          } else {
            break;
          }
        }
      } else {
        if (board.tiles[x - 1][y].state === "EMPTY") {
          moves.push([x - 1, y]);
        }
      }
    }

    return moves;
  }

  canMove(board, x, y) {
    console.log("can move: ", board.tiles[x][y].piece.name);
  }
}
