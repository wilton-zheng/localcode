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

/******************************************************************************\
*                 _____ _____ _____ ____________________    ______             *
*                /     /     /     / ____/ ____/ __  / /   / __  /             *
*               /  /  /  /  /  /  /____ / /___/ __  / /___/ __  /              *
*              /  /  /  /  /  ___/_____/_____/_/ /_/_____/_/ /_/               *
*             /_____/_____/__/OOPSLA 2007 Tutorial support code                *
*                                                                              *
\******************************************************************************/

package com.wilton.scala.the_scells_spreadsheet_35

import swing._, swing.event._

class SpreadSheet(val height: Int, val width: Int) extends ScrollPane {

  val cellModel = new Model(height, width)
  import cellModel._

  val table = new Table(height, width) {
    rowHeight = 25
    autoResizeMode = Table.AutoResizeMode.Off
    showGrid = true
    gridColor = new java.awt.Color(150, 150, 150)
    
    def userData(row: Int, column: Int): String = {
      val v = this(row, column)
      if (v == null) "" else v.toString
    }
    
    override def rendererComponent(isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component =
      if (hasFocus) new TextField(userData(row, column))
      else new Label(cells(row)(column).toString) { xAlignment = Alignment.Right }

    reactions += {
      case TableUpdated(table, rows, column) =>
        for (row <- rows)
          cells(row)(column).formula = 
            FormulaParsers.parse(userData(row, column))
      case ValueChanged(cell) =>
        updateCell(cell.row, cell.column)
    }

    for (row <- cells; cell <- row) listenTo(cell)
  }

  val rowHeader = new ListView((0 until height) map (_.toString)) {
    fixedCellWidth = 30
    fixedCellHeight = table.rowHeight
  }
  
  viewportView = table
  rowHeaderView = rowHeader
}

