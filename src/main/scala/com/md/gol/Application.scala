package com.md.gol

import com.md.gol.GameOfLife.Grid

import scala.io.Source

object Application {
  def main(args: Array[String]): Unit = {
    val input = Source.fromFile("src/main/resources/input.txt").mkString
    val gol = new GameOfLife(input)

    generations(gol.grid)

    def generations(grid:Grid, index:Int=1): Unit = {
      println(s"Generation $index")
      grid.nextGeneration() match {
        case x if x.equals(grid) => println("Stable")
        case nextGrid =>
          println(nextGrid)
          generations(nextGrid, index + 1)
      }
    }
  }
}
