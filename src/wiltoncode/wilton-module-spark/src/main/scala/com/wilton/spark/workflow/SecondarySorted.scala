package com.wilton.spark.workflow

import com.wilton.spark.common.utils.SecondarySortedkey
import org.apache.spark.{SparkConf, SparkContext}

object SecondarySorted {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("SecondarySort")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val lines = sc.textFile("C://Users//Administrator//Desktop//sort.txt")

    val pairs = lines.map(line => {
      val lineSplit = line.split(" ")
      val key = new SecondarySortedkey(lineSplit(0).toInt, lineSplit(1).toInt)
      (key, line)
    }
    )

    val sortPairs = pairs.sortByKey()
    val sortLines = sortPairs.map(line=> line._2)
    sortLines.foreach(println(_))

    sc.stop()

  }

}
