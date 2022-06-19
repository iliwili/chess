import { Piece } from "../Piece";

export class Rook extends Piece {
  constructor(type, x, y) {
    super("rook", "Ro", type, x, y);
  }

  getMoves(board) {
    let moves = this.getVerticalMoves(board);

    return moves;
  }
}
