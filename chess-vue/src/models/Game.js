import { Player } from "./Player";

export class Game {
  constructor(board) {
    this.board = board;
    this.player1 = new Player("iliwili");
    this.player2 = new Player("Siroco");
  }

  start() {
    // console.log(this.board.tiles[3][3].piece.getMoves(this.board));
  }

  movePiece(x1, y1, x2, y2) {
    console.log(x1, y1, x2, y2);
  }
}
