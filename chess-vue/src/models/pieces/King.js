import { Piece } from "../Piece";

export class King extends Piece {
  constructor(type, x, y) {
    super("king", "Ki", type, x, y);
  }
}
