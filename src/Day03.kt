fun main() {

    fun part1(input: List<String>): Int {
        val columns = input[0].indices // get counts of columns

        val bitCounts = buildList {
            for (column in columns) {
                val pair = input.countBitsInColumn(column)
                add(pair)
            }
        }
        println("bitCounts: $bitCounts")

        // get gamma rate by binary
        val gammaByBinary = buildString {
            for (column in columns) {
                val commonBit = getCommonBit(bitCounts[column])
                append(commonBit)
            }
        }
        println("gammaByBinary: $gammaByBinary")

        // get epsilon rate by binary
        val epsilonByBinary = gammaByBinary.invertBinaryString()
        println("epsilonByBinary: $epsilonByBinary")

        val gammaRate = toDecimal(gammaByBinary)
        val epsilonRate = toDecimal(epsilonByBinary)
        println("gammaRate: $gammaRate")
        println("epsilonRate: $epsilonRate")
        
        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day03")
    println(part1(input))
    //println(part2(input))
}

// check target column's value of each inputs
private fun List<String>.countBitsInColumn(column: Int): BitCount {
    var zeroes = 0
    var ones = 0
    for (input in this) {
        if (input[column] == '0') {
            zeroes++
        } else {
            ones++
        }
    }
    return BitCount(zeroes, ones)
}

private data class BitCount(val zeroes: Int, val ones: Int)

private fun String.invertBinaryString(): String {
    return this.asIterable()
        .joinToString("") { if (it == '0') "1" else "0" }
}


// get common bit from BitCount
private fun getCommonBit(bitCount: BitCount): Int {
    if (bitCount.zeroes > bitCount.ones) {
        return 0
    }
    return 1
}

// convert binary to decimal
private fun toDecimal(binaryString: String): Int {
    val radix = 2 // 基数
    return binaryString.toInt(radix)
}
