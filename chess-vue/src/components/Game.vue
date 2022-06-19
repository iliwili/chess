<template>
  <div class="game">
    <div class="title">Chess</div>
    <chess-board :board="board"></chess-board>
  </div>
</template>

<script>
import { Board } from "../models/Boards";
import { Game } from "../models/Game";
import ChessBoard from "./ChessBoard.vue";

export default {
  components: { ChessBoard: ChessBoard },
  name: "Game",
  data: () => {
    return {
      game: Game,
      board: Board,
    };
  },
  created() {
    this.initGame();
  },
  methods: {
    initGame() {
      this.board = new Board();
      this.game = new Game(this.board);

      this.game.board.showBoardState();
      this.game.start();
    },
  },
};
</script>

<style lang="scss">
.game {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100%;

  .title {
    font-size: 64px;
    margin-top: 2rem;
  }
}
</style>
