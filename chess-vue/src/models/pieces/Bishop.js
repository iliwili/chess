import { Piece } from "../Piece";

export class Bishop extends Piece {
  constructor(type, x, y) {
    super("bishop", "Bi", type, x, y);
  }

  getMoves(board) {
    let moves = this.getDiagonalMoves(board);

    return moves;
  }
}
