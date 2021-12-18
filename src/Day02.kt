fun main() {
    fun part1(input: List<String>): Int {
        var horizontal = 0
        var depth = 0
        input.forEach() {
            val ope = it.split(" ")
            when (ope[0]) {
                "forward" -> horizontal += ope[1].toInt()
                "down" -> depth += ope[1].toInt()
                "up" -> depth -= ope[1].toInt()
            }
        }
        return horizontal * depth
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day02")
    println(part1(input))
    //println(part2(input))
}
