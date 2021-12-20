fun main() {

    fun part1(input: List<String>): Int {
        val targetLines = input.parse().filterNot { (x1, x2, y1, y2) -> x1 != x2 && y1 != y2 }
        println("targetLines: $targetLines")
        return solve(targetLines)
    }
    
    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day05")
    println(part1(input))
    //println(part2(input))
}

private fun List<String>.parse(): List<List<Int>>  {
    val intLines: List<List<Int>> = map { line ->
        line.replace(" -> ", ",").split(",").map { it.toInt() }
    }
    
    return intLines.map { (x1, y1, x2, y2) ->
        listOf(x1, x2, y1, y2)
    }
}

private fun solve(lines: List<List<Int>>): Int {
    val array = Array(1000) { IntArray(1000) { 0 } }

    lines.forEach { (x1, x2, y1, y2) ->
        var x = x1
        var y = y1

        array[x][y]++

        while (x != x2 || y != y2) {
            if (x != x2) {
                if (x1 < x2) {
                    x++
                } else {
                    x--
                }
            }
            if (y != y2) {
                if (y1 < y2) {
                    y++
                } else {
                    y--
                }
            }
            array[x][y]++
        }
    }

    return array.sumOf { row -> row.count { it > 1 } }
}