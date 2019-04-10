package com.wilton.scala.composition_and_inheritance_10

import Element.elem

object LayoutElement {
  def main(args: Array[String]) {

    println("example [\n" + example + "\n]")
  }

  def example = {
    val column1 = elem("hello") above elem("***")
    val column2 = elem("***") above elem("world")
    column1 beside column2
  }
}