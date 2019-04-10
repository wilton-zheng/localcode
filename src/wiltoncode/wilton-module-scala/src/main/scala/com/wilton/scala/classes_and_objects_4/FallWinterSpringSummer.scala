package com.wilton.scala.classes_and_objects_4

/**
  * @author 郑金伟
  * @description:
  * @time 8:55 2018/8/29
  * @className:
  * @Modified By:
  */
import com.wilton.scala.classes_and_objects_4.ChecksumAccumulator.calculate

object FallWinterSpringSummer extends App {

  for (season <- List("fall", "winter", "spring"))
    println(season + ": " + calculate(season))
}
