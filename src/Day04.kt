import java.io.File

fun main() {
    fun part1(fileName: String): Int {
        val data = parse(fileName)

        val mostDrawnBoards = data.wins().first()

        return mostDrawnBoards.score()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    println(part1("Day04.txt"))
    //println(part2(input))
}

// get win boards order by drawn size
private fun Input.wins(): List<Win> {
    return boards
        .mapNotNull { it.winOrNull(answers) }
        .sortedBy { it.drawn.size }
}

// check target line is win or not
private fun Board.winOrNull(answers: List<Int>): Win? {
    return answers.indices.asSequence()
        .map { answers.slice(0..it) }
        .firstOrNull { (rows + cols).any { line -> it.containsAll(line) } }
        ?.let { Win(this, it) }
}

// calculate score
private fun Win.score(): Int {
    return board.rows.flatten()
        .filterNot { it in drawn }
        .sum()
        .let { it * drawn.last() }
}

// create Input data
private fun parse(fileName: String): Input {
    return File("src/input", fileName)
        .readText()
        .trim()
        .split(Regex("(?m)[\n\r]*^\\s*$[\n\r]+"))
        .createInput()
}

private fun List<String>.createInput(): Input {
    val answers = first().split(",").map { it.toInt() }
    val boards = drop(1).map { it.createBoard() }

    return Input(answers, boards)
}

private fun String.createBoard(): Board {
    val rows = lines()
        .map { it ->
            it.trim().split(Regex("[,\\s]\\s*"))
                .map { it.toInt() }
        }
    return Board(rows)
}

data class Input(val answers: List<Int>, val boards: List<Board>)

data class Board(
    val rows: List<List<Int>>,
    val cols: List<List<Int>> = rows.indices.map { i -> rows.map { it[i] } }
)

data class Win(val board: Board, val drawn: List<Int>)