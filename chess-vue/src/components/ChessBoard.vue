<template>
  <div class="board">
    <div
      v-for="(tiles, index) in this.board.tiles.slice().reverse()"
      v-bind:key="index"
      class="row"
    >
      <Tile
        v-for="(tile, index) in tiles"
        v-bind:key="index"
        v-bind:tile="tile"
        v-on:click="clickTile(tile)"
      >
      </Tile>
    </div>
  </div>
</template>

<script>
import { Board } from "../models/Boards";
import Tile from "./Tile.vue";

export default {
  name: "Board",
  components: { Tile },
  props: {
    board: {
      type: Board,
    },
  },
  created() {
    console.log(this.board.tiles);
  },
  watch: {
    board: function (val) {
      console.log("BOARD");

      console.log(val);
    },
  },
  methods: {
    clickTile(tile) {
      if (tile.piece != null) {
        console.log(tile.piece);
        tile.state = "MOVABLE-FILL";
      } else {
        tile.state = "MOVABLE";
      }
    },
  },
};
</script>

<style lang="scss">
.board {
  margin-top: 4rem;
  padding: 8px;
  border-radius: 5px;
  background-color: #fff;

  box-shadow: 0 0 5px #ccc;
}

.row {
  display: flex;
}
</style>
