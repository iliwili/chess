export class Tile {
  constructor(piece, state, x, y) {
    this.piece = piece;
    // STATES => EMPTY, FILL, MOVABLE
    this.state = state;
    this.x = x;
    this.y = y;
  }

  setPiece(piece) {
    this.piece = piece;
    this.state = "FILL";
  }

  setState(state) {
    this.state = state;
  }
}
