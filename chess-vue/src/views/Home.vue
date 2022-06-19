<template>
  <Game></Game>
</template>

<script>
import Game from "../components/Game.vue";
// @ is an alias to /src

export default {
  components: { Game },
  name: "Home",
  data: () => {
    return {
      // numbers: [1, 2, 3, 4, 5, 6, 7, 8],
      // letters: ["A", "B", "C", "D", "E", "F", "G", "H"],
    };
  },
  created() {
    // this.numbers.forEach((n, i) => {
    //   this.board.push([]);
    //   this.letters.forEach((l) => {
    //     this.board[i].push(`${n}${l}`);
    //   });
    // });
  },
};
</script>

<style lang="scss"></style>
