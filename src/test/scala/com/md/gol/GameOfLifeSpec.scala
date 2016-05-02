package com.md.gol

import com.md.gol.GameOfLife.Cell
import org.scalatest._

import scala.language.postfixOps

class GameOfLifeSpec extends FlatSpec with Matchers {
  val input = "4 8\n" +
    "........\n" +
    "....*...\n" +
    "...**...\n" +
    "........"
  val gol = new GameOfLife(input)

  val allLiveInput = "4 8\n" +
    "********\n" +
    "********\n" +
    "********\n" +
    "********"
  val allLiveGol = new GameOfLife(allLiveInput)

  "A grid" should "be parsed correctly" in {

    gol.grid.getCell(0,0) should equal (Cell(0, 0, alive = false))
    gol.grid.getCell(0,7) should equal (Cell(0, 7, alive = false))
    gol.grid.getCell(1,4) should equal (Cell(1, 4, alive = true))
    gol.grid.getCell(2,4) should equal (Cell(2, 4, alive = true))
    gol.grid.getCell(2,3) should equal (Cell(2, 3, alive = true))
    gol.grid.getCell(3,5) should equal (Cell(3, 5, alive = false))
  }

  it should "return input on toString" in {

    gol.grid.toString should equal (input)
  }

  it should "give correct neighbours in upper left corner" in {

    allLiveGol.grid.getAliveNeighbours(allLiveGol.grid.getCell(0,0)) should contain only(
      allLiveGol.grid.getCell(0, 1),
      allLiveGol.grid.getCell(1, 1),
      allLiveGol.grid.getCell(1, 0))
  }

  it should "give correct neighbours in upper right corner" in {

    allLiveGol.grid.getAliveNeighbours(allLiveGol.grid.getCell(0, 7)) should contain only(
      allLiveGol.grid.getCell(0, 6),
      allLiveGol.grid.getCell(1, 6),
      allLiveGol.grid.getCell(1, 7))
  }

  it should "give correct neighbours in lower right corner" in {

    allLiveGol.grid.getAliveNeighbours(allLiveGol.grid.getCell(3, 7)) should contain only (
      allLiveGol.grid.getCell(3, 6),
      allLiveGol.grid.getCell(2, 7),
      allLiveGol.grid.getCell(2, 6))
  }

  it should "give correct neighbours in lower left corner" in {

    allLiveGol.grid.getAliveNeighbours(allLiveGol.grid.getCell(3, 0)) should contain only (
      allLiveGol.grid.getCell(3, 1),
      allLiveGol.grid.getCell(2, 0),
      allLiveGol.grid.getCell(2, 1)
      )
  }

  it should "give correct neighbours in first row" in {

    allLiveGol.grid.getAliveNeighbours(allLiveGol.grid.getCell(0, 3)) should contain only (
      allLiveGol.grid.getCell(0, 2),
      allLiveGol.grid.getCell(0, 4),
      allLiveGol.grid.getCell(1, 2),
      allLiveGol.grid.getCell(1, 3),
      allLiveGol.grid.getCell(1, 4)
      )
  }

  it should "give correct neighbours in middle" in {

    allLiveGol.grid.getAliveNeighbours(allLiveGol.grid.getCell(1, 3)) should contain only (
      allLiveGol.grid.getCell(0, 2),
      allLiveGol.grid.getCell(0, 3),
      allLiveGol.grid.getCell(0, 4),
      allLiveGol.grid.getCell(1, 2),
      allLiveGol.grid.getCell(1, 4),
      allLiveGol.grid.getCell(2, 2),
      allLiveGol.grid.getCell(2, 3),
      allLiveGol.grid.getCell(2, 4)
      )
  }

  it should "give correct live neighbours when empty" in {

    gol.grid.getAliveNeighbours(Cell(0, 0, alive = false)) shouldBe empty
  }

  it should "give correct live neighbours when some found" in {

    gol.grid.getAliveNeighbours(gol.grid.getCell(1, 4)) should contain only (
      gol.grid.getCell(2, 4),
      gol.grid.getCell(2, 3)
      )
  }

  it should "give correct correct next generation" in {

    gol.grid.nextGeneration().toString should equal("4 8\n........\n...**...\n...**...\n........")
  }
}
