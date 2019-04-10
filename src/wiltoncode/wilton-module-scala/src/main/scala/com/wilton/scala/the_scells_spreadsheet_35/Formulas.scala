/*
 * Copyright (C) 2007-2016 Artima, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Example code from:
 *
 * Programming in Scala, Third Edition
 * by Martin Odersky, Lex Spoon, Bill Venners
 *
 * http://booksites.artima.com/programming_in_scala_3ed
 */

package com.wilton.scala.the_scells_spreadsheet_35

trait Formula

case class Coord(row: Int, column: Int) extends Formula {
  override def toString = ('A' + column).toChar.toString + row
}

case class Range(c1: Coord, c2: Coord) extends Formula {
  override def toString = c1.toString + ":" + c2.toString
}

case class Number(value: Double) extends Formula {
  override def toString = value.toString 
}

case class Textual(value: String) extends Formula { 
  override def toString = value.toString 
}

case class Application(function: String, arguments: List[Formula]) extends Formula { 
  override def toString = function+arguments.mkString("(", ",", ")")
}

case class FormulaError(msg: String) extends Formula

object Empty extends Textual("")

