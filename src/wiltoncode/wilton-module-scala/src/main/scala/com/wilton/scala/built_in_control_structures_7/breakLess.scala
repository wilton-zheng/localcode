package com.wilton.scala.built_in_control_structures_7

/**
  * @author 郑金伟
  * @description:
  * @time 14:15 2018/8/29
  * @className:
  * @Modified By:
  */
object breakLess {
  def main(args: Array[String]): Unit = {

    def searchFrom(i: Int): Int =
      if (i >= args.length) -1
      else if (args(i).startsWith("-")) searchFrom(i + 1)
      else if (args(i).endsWith(".scala")) i
      else searchFrom(i + 1)

    val i = searchFrom(0)
    println("i = " + i)


    def printMultiTable() = {

      var i = 1
      // only i in scope here

      while (i <= 10) {

        var j = 1
        // both i and j in scope here

        while (j <= 10) {

          val prod = (i * j).toString
          // i, j, and prod in scope here

          var k = prod.length
          // i, j, prod, and k in scope here

          while (k < 4) {
            print(" ")
            k += 1
          }

          print(prod)
          j += 1
        }

        // i and j still in scope; prod and k out of scope

        println()
        i += 1
      }

      // i still in scope; j, prod, and k out of scope
    }

    printMultiTable()


    // Returns a row as a sequence
    def makeRowSeq(row: Int) =
      for (col <- 1 to 10) yield {
        val prod = (row * col).toString
        val padding = " " * (4 - prod.length)
        padding + prod
      }

    // Returns a row as a string
    def makeRow(row: Int) = makeRowSeq(row).mkString

    // Returns table as a string with one row per line
    def multiTable() = {
      val tableSeq = // a sequence of row strings
        for (row <- 1 to 10)
          yield makeRow(row)
      tableSeq.mkString("\n")
    }

    println("multiTable [ \n" + multiTable + "]")
  }
}
