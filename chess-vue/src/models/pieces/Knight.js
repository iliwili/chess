import { Piece } from "../Piece";

export class Knight extends Piece {
  constructor(type, x, y) {
    super("knight", "Kn", type, x, y);
  }

  getMoves(board) {
    let moves = [];
    let knightMoves = [
      [-2, 1],
      [-1, 2],
      [1, 2],
      [2, 1],
      [2, -1],
      [1, -2],
      [-1, -2],
      [-2, -1],
    ];

    console.log(this.x, this.y);

    for (let i = 0; i < knightMoves.length; i++) {
      let newX = this.x + knightMoves[i][0];
      let newY = this.y + knightMoves[i][1];

      if (!this.isTileOutOfBounds(newX, newY)) {
        if (board.tiles[newX][newY].state === "EMPTY") {
          moves.push([newX, newY]);
        }
      }
    }

    return moves;
  }
}
