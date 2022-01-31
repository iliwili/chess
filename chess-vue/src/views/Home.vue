<template>
  <div class="game">
    <div class="board">
      <div
        v-for="(tiles, index) in board.slice().reverse()"
        v-bind:key="index"
        class="row"
      >
        <div v-for="(tile, index) in tiles" v-bind:key="index" class="tile">
          {{ tile }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "Home",
  data: () => {
    return {
      board: [],
      numbers: [1, 2, 3, 4, 5, 6, 7, 8],
      letters: ["A", "B", "C", "D", "E", "F", "G", "H"],
    };
  },
  created() {
    this.numbers.forEach((n, i) => {
      this.board.push([]);
      this.letters.forEach((l) => {
        this.board[i].push(`${n}${l}`);
      });
    });
  },
};
</script>

<style lang="scss">
.game {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.row {
  display: flex;
}

.tile {
  width: 68.5px;
  height: 72px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
