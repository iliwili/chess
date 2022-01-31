<template>
  <router-view />
</template>

<script>
import { Game } from "./models/Game";
import { Board } from "./models/Boards";

export default {
  data: () => {
    return {};
  },
  created() {
    let board = new Board();
    let game = new Game(board);

    game.board.showBoardState();
    game.start();
  },
};
</script>

<style lang="scss">
#app {
  width: 100%;
  height: 100%;
}
</style>
