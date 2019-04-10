package com.wilton.scala.object_equality_30

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

/** These equals methods are not symmetric.
 *  Sometimes p.equals(cp) but not cp.equals(p).
 */
object Points5 {
  class Point(var x: Int, var y: Int) { // Problematic
    override def hashCode = (x, y).##
    override def equals(other: Any) = other match { 
      case that: Point => this.x == that.x && this.y == that.y 
      case _ => false 
    }
  }

  object Color extends Enumeration {
    val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value
  }

  class ColoredPoint(x: Int, y: Int, val color: Color.Value) 
      extends Point(x, y) { // Problem: equals not symmetric
  
    override def equals(other: Any) = other match {
      case that: ColoredPoint => 
        this.color == that.color && super.equals(that)
      case _ => false
    }
  }
}
