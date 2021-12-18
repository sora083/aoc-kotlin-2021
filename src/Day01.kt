fun main() {
    fun part1(input: List<String>): Int {
        var current = 9999
        var count = 0
        input.forEach() {
            if(it.toInt() > current) {
                count++
            }
            current = it.toInt()
        }
        return count
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day01")
    println(part1(input))
    //println(part2(input))
}
