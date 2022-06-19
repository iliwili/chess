<template>
  <div class="tile" :class="backgroundClass + ' ' + tile.state">
    <span>{{
      tile.piece
        ? tile.piece.type[0].toUpperCase() +
          "-" +
          tile.piece.name.substring(0, 2).toUpperCase()
        : "EM"
    }}</span>
    <span>{{ tile.x }} / {{ tile.y }}</span>

    <span class="move"></span>

    <div
      v-if="tile.piece != null && tile.state === 'MOVABLE-FILL'"
      class="move-overlay"
    ></div>

    <span class="number" :class="numberVisible">{{ tile.x + 1 }}</span>
    <span class="letter" :class="characterVisible">{{
      this.characters[tile.y]
    }}</span>
  </div>
</template>

<script>
import { Tile } from "../models/Tile";

export default {
  name: "Tile",
  props: {
    tile: {
      type: Tile,
    },
  },
  data: () => {
    return {
      characters: ["A", "B", "C", "D", "E", "F", "G", "H"],
    };
  },
  computed: {
    backgroundClass: function () {
      if (this.tile === undefined) return;

      if (this.tile.x % 2 !== 0) {
        return this.tile.y % 2 === 0 ? "light" : "dark";
      } else {
        return this.tile.y % 2 !== 0 ? "light" : "dark";
      }
    },
    numberVisible: function () {
      if (this.tile === undefined) return;
      return this.tile.y === 0 ? "visible" : "";
    },
    characterVisible: function () {
      if (this.tile === undefined) return;
      return this.tile.x === 0 ? "visible" : "";
    },
  },
};
</script>

<style lang="scss">
.tile {
  width: 68.5px;
  height: 72px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;

  &.light {
    background-color: #e8edf9;

    .number {
      color: #b7c0d8;
    }

    .letter {
      color: #b7c0d8;
    }
  }

  &.dark {
    background-color: #b7c0d8;

    .number {
      color: #e8edf9;
    }

    .letter {
      color: #e8edf9;
    }
  }

  .number {
    visibility: hidden;
    position: absolute;
    top: 3px;
    left: 3px;

    &.visible {
      visibility: visible;
    }
  }

  .letter {
    visibility: hidden;
    color: green;
    position: absolute;
    bottom: 3px;
    right: 3px;

    &.visible {
      visibility: visible;
    }
  }

  .move {
    display: none;
    width: 24px;
    height: 24px;
    position: absolute;
    border-radius: 50%;
    background-color: rgba(var(--active), 1);
    opacity: 0.5;
  }

  .move-overlay {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: rgba(var(--active), 0.5);
  }

  //   &.MOVABLE-FILL {
  //     background-color: rgba(var(--active), 1);
  //     opacity: 0.5;
  //   }

  &.MOVABLE {
    .move {
      display: block;
    }
  }
}
</style>
