package com.wilton.scala.collections_in_depth_24

object MapTools {

  def f(x:String) = {
    println("taking my time.")
    Thread.sleep(100)
    x.reverse
  }

  val cache = collection.mutable.Map[String,String]()

  def cachedFTemp(s:String) = cache.getOrElseUpdate(s,f(s))


  def cachedF(str:String) = cache get str match {
    case Some(result) =>  result
    case None =>
      val result = f(str)
      cache(str) = result
      result
  }


}
