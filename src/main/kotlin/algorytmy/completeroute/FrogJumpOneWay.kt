package org.example.algorytmy.completeroute

fun main() {
    /*
    Frog wants to get from stone indexed 0 to last stone defined in int array. Values in array specifies length of jump that frog needs to make.
    Return number of jumps to complete the journey. Return -1 if journey cannot be completed.

    Input: stones = [3, 2, 3, 1, 0, 5]
    Output: 3
     */
    println(completeJourney(intArrayOf(3, 2, 3, 1, 0, 5)))
}

fun completeJourney(stones: IntArray): Int {
    val begin = 0
    val end = stones.size - 1
    // Queue stores position, steps to current stone
    val queue: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
    queue.add(begin to 0)
    val visited = mutableSetOf<Int>()
    while (queue.isNotEmpty()) {
        val (pos, steps) = queue.removeFirst()
        if (pos == end) return steps
        if (visited.contains(pos)) continue
        visited.add(pos)
        val jump = stones[pos]
        val forward = pos + jump
        val backward = pos - jump
        if (forward < stones.size && !visited.contains(forward)) {
            queue.add(forward to steps + 1)
        }
        if (backward >= 0 && !visited.contains(backward)){
            queue.add(backward to steps + 1)
        }
    }
    return -1
}