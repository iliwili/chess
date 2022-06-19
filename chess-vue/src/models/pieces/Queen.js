import { Piece } from "../Piece";

export class Queen extends Piece {
  constructor(type, x, y) {
    super("queen", "Qu", type, x, y);
  }

  getMoves(board) {
    let moves = this.getAxialMoves(board);

    moves = moves.concat(this.getDiagonalMoves(board));

    return moves;
  }
}
