export class Tile {
  constructor(piece, state) {
    this.piece = piece;
    // STATES => EMPTY, FILL, MOVABLE
    this.state = state;
  }

  setPiece(piece) {
    this.piece = piece;
    this.state = "FILL";
  }

  setState(state) {
    this.state = state;
  }
}
