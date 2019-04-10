package com.wilton.scala.classes_and_objects_4

/**
  * @author 郑金伟
  * @description:
  * @time 8:53 2018/8/29
  * @className:
  * @Modified By:
  */

object Summer {
  def main(args: Array[String]) = {
    for (arg <- args)
      println(arg + ": " + ChecksumAccumulator.calculate(arg))
  }
}
