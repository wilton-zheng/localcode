package com.wilton.spark.common.utils

class SecondarySortedkey(val first:Int, val second:Int)
  extends Ordered[SecondarySortedkey] with Serializable {

  override def compare(that: SecondarySortedkey): Int = {
    if (this.first - that.first != 0){
      this.first - that.first
    }else{
      this.second - that.second
    }
  }
}
