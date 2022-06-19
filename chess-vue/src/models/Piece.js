export class Piece {
  constructor(name, prefix, type, x, y) {
    this.name = name;
    this.prefix = prefix;
    this.type = type;
    this.x = x;
    this.y = y;
  }

  isTileOutOfBounds(x, y) {
    return x > 7 || x < 0 || y > 7 || y < 0;
  }

  setPosition(newX, newY) {
    this.x = newX;
    this.y = newY;
  }

  getMoves(board) {
    console.log(board);
  }

  /*
   * Get the vertical and horizontal moves of a piece
   */
  getAxialMoves(board) {
    if (board.tiles[this.x][this.y].state === "EMPTY")
      throw new Error(
        `The state of the tile [${this.x}, ${this.y}] cannot be empty. {getAxialMoves()}`
      );
    let moves = [];

    // VERTICAL ^
    for (let i = 1; i <= 7; i++) {
      let newX = this.x - i;
      if (!this.isTileOutOfBounds(newX, this.y)) {
        if (board.tiles[newX][this.y].state === "EMPTY") {
          moves.push([newX, this.y]);
        } else {
          break;
        }
      }
    }

    // VERTICAL ^
    for (let i = 1; i <= 7; i++) {
      let newX = this.x + i;
      if (!this.isTileOutOfBounds(newX, this.y)) {
        if (board.tiles[newX][this.y].state === "EMPTY") {
          moves.push([newX, this.y]);
        } else {
          break;
        }
      }
    }

    // HORIZONTAL <-->
    for (let i = 1; i <= 7; i++) {
      let newY = this.y - i;
      if (!this.isTileOutOfBounds(this.x, newY)) {
        if (board.tiles[this.x][newY].state === "EMPTY") {
          moves.push([this.x, newY]);
        } else {
          break;
        }
      }
    }

    // HORIZONTAL <-->
    for (let i = 1; i <= 7; i++) {
      let newY = this.y + i;
      if (!this.isTileOutOfBounds(this.x, newY)) {
        if (board.tiles[this.x][newY].state === "EMPTY") {
          moves.push([this.x, newY]);
        } else {
          break;
        }
      }
    }

    return moves;
  }

  /*
   * Get the diagonal moves of a piece
   */
  getDiagonalMoves(board) {
    if (board.tiles[this.x][this.y].state === "EMPTY")
      throw new Error(
        `The state of the tile [${this.x}, ${this.y}] cannot be empty. {getDiagonalMoves()}`
      );
    let moves = [];

    // DIAGONAL --> BR
    for (let i = 1; i <= 7; i++) {
      let newX = this.x + i;
      let newY = this.y + i;
      if (!this.isTileOutOfBounds(newX, newY)) {
        if (board.tiles[newX][newY].state === "EMPTY") {
          moves.push([newX, newY]);
        } else {
          break;
        }
      }
    }

    // DIAGONAL --> BL
    for (let i = 1; i <= 7; i++) {
      let newX = this.x + i;
      let newY = this.y - i;
      if (!this.isTileOutOfBounds(newX, newY)) {
        if (board.tiles[newX][newY].state === "EMPTY") {
          moves.push([newX, newY]);
        } else {
          break;
        }
      }
    }

    // DIAGONAL --> TL
    for (let i = 1; i <= 7; i++) {
      let newX = this.x - i;
      let newY = this.y - i;
      if (!this.isTileOutOfBounds(newX, newY)) {
        if (board.tiles[newX][newY].state === "EMPTY") {
          moves.push([newX, newY]);
        } else {
          break;
        }
      }
    }

    // DIAGONAL --> TR
    for (let i = 1; i <= 7; i++) {
      let newX = this.x - i;
      let newY = this.y + i;
      if (!this.isTileOutOfBounds(newX, newY)) {
        if (board.tiles[newX][newY].state === "EMPTY") {
          moves.push([newX, newY]);
        } else {
          break;
        }
      }
    }

    return moves;
  }
}
