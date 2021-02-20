package com.labs1904

object NextBiggestNumber {

  def main(args: Array[String]) = {

    val input = args(0).toInt
    val nextBiggestNumber = getNextBiggestNumber(input)
    println(s"Input: $input")
    println(s"Next biggest number: $nextBiggestNumber")
  }

  def getNextBiggestNumber(num: Integer) : Integer = {
    var num_s: String = num.toString
    var digits: Array[Int] = num.toString.split("").map(_.toInt)
    var sm_from_right: Integer = -1

    // (1) split left and right at left number being 
    // smaller than right number
    var idx = digits.size - 1
    while (idx > 0) {
      if(digits(idx-1) < digits(idx)){
            sm_from_right = idx - 1
            idx = -1
      }
      idx -= 1
    }
    
    // (2) if we traversed the whole number, we're donezo
    if(sm_from_right == -1){
        return -1
    }
            
    // (3) find new number on right less than the splitting number
    var sm_idx = sm_from_right + 1
    for(r_idx <- sm_idx to (digits.size - 1)){
      if(digits(r_idx) > digits(sm_from_right) && digits(r_idx) < digits(sm_idx)){
            sm_idx = r_idx
        }
    }

    // (4) swap lowest higher number on right with final left number 
    // which is the splitting number
    var tmp: Integer = digits(sm_from_right)
    digits(sm_from_right) = digits(sm_idx)
    digits(sm_idx) = tmp

    // (5) sort the right side and BAM! we're done
    java.util.Arrays.sort(digits, sm_from_right + 1, digits.length)
    
    var int_response = digits.map(_.toString).mkString("").toInt
    return int_response
  }
}
