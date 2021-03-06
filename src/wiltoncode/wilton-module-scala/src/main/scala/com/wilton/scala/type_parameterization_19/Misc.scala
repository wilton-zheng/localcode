package com.wilton.scala.type_parameterization_19

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

object Misc {
  class Cell[T](init: T) {
    private[this] var current = init
    def get = current
    def set(x: T) = { current = x }
  }
  trait OutputChannel[-T] {
    def write(x: T)
  }
  trait Function1[-S, +T] {
    def apply(x: S): T
  }
}
