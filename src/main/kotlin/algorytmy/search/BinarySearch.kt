package org.example.algorytmy.search


fun main() {
    /*
    Example 1:

    Input: nums = [-1,0,3,5,9,12], target = 9
    Output: 4
    Explanation: 9 exists in nums and its index is 4
    Example 2:

    Input: nums = [-1,0,3,5,9,12], target = 2
    Output: -1
    Explanation: 2 does not exist in nums so return -1
    */
    val nums = intArrayOf(-1, 0, 3, 5, 9, 12)
    val target = 9
    println(BinarySearch.search(nums, target))
}

object BinarySearch {
    fun search(nums: IntArray, target: Int): Int {
        var output = -1
        if (nums.size == 0) return output
        var begin: Int = 0
        var end: Int = nums.size - 1
        var mid: Int = 0

        while (end - begin >= 0) {
            mid = begin + (end - begin + 1) / 2
            when {
                nums[mid] == target -> return mid
                nums[mid] < target -> begin = mid + 1
                nums[mid] > target -> end = mid - 1
            }

        }
        return output
    }
}