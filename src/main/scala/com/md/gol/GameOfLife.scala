package com.md.gol

import com.md.gol.GameOfLife.{Cell, Grid}

class GameOfLife(input:String) {

  val lines = Option(input).getOrElse("")
    .split("\n")
    .filterNot(_.isEmpty).toList

  val grid = parseRows(lines.tail)

  def parseRows(rows:List[String]): Grid = {

    def parseRowsInt(rows:List[String], index: Int, grid: List[List[Cell]]): List[List[Cell]] = {
      rows match {
        case x :: xs => parseRowsInt(xs, index + 1, parseRow(x, index) :: grid)
        case List() => grid
      }
    }
    new Grid(parseRowsInt(rows, 0, List[List[Cell]]()).reverse)
  }

  def parseRow(row:String, rowIndex: Int): List[Cell] = {

    def parseRowInt(restOfRow: String, rowIndex: Int, colIndex: Int, currentCells: List[Cell]): List[Cell] = {
      restOfRow match {
        case "" => currentCells
        case _ =>
          val cell = restOfRow.head match {
            case '*' => Cell(rowIndex, colIndex, alive = true)
            case '.' => Cell(rowIndex, colIndex, alive = false)
            case _ => throw new IllegalArgumentException("invalid character")
          }
          parseRowInt(restOfRow.tail, rowIndex, colIndex + 1, cell :: currentCells)
      }
    }
    parseRowInt(row, rowIndex, 0,  List[Cell]()).reverse
  }
}

object GameOfLife {
  case class Cell(row:Int, col:Int, alive:Boolean)
  case class Grid(rows:List[List[Cell]]) {
    val width = rows.head.size
    val height = rows.size

    def getCell(row:Int, col:Int): Cell = rows(row)(col)

    def getAliveNeighbours(cell: Cell): List[Cell] = {
      val rowNeighbourRange = Math.max(cell.row - 1, 0) to Math.min(cell.row + 1, height)
      val colNeighbourRange = Math.max(cell.col - 1, 0) to Math.min(cell.col + 1, width)
      rows.flatMap(r => r)
        .filter(_.alive)
        .filter(!_.equals(cell))
        .filter(c => rowNeighbourRange.contains(c.row) && colNeighbourRange.contains(c.col))
    }

    override def toString: String = {
      val dimensionLine = s"$height $width\n"
      dimensionLine + rows.map(r => r.map(_.alive match {
        case true => "*"
        case false => "."
      }).mkString + "\n").mkString.stripSuffix("\n")
    }

    def nextGeneration(): Grid = {
      new Grid(rows.map(_.map(cell => this.getAliveNeighbours(cell).size match {
        case 2 => cell //Any live cell with two or three live neighbours lives on to the next generation.
        case 3 => cell.copy(alive = true) //Any dead cell with exactly three live neighbours becomes a live cell
        case _ => cell.copy(alive = false)
      })))
    }
  }
}

