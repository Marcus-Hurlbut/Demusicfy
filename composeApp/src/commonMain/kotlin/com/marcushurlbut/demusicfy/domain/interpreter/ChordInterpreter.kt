package com.marcushurlbut.demusicfy.domain.interpreter

import com.marcushurlbut.demusicfy.domain.model.Note

enum class ChordType {
    Undefined,
    Major,
    Minor,
    PowerChord,
    Suspended,
    Augmented,
    FullDiminished,
    HalfDiminished,
    DominantSeventh,
    MajorSeventh,
    MinorSeventh,
    MinorNinth,
    MajorNinth,
    MajorSixth,
    MinorSixth,

    MajorEleventh,
    MinorEleventh,
    MajorAddNine,
    MinorAddNine,
}

class ChordInterpreter {
    var type : ChordType = ChordType.Undefined

    val ROOT = 0
    val FLAT_SECOND = 1
    val SECOND = 2
    val FLAT_THIRD = 3
    val THIRD = 4
    val FOURTH = 5
    val FLAT_FIFTH = 6
    val FIFTH = 7
    val FLAT_SIXTH = 8
    val SIXTH = 9
    val FLAT_SEVENTH = 10
    val SEVENTH = 11

    fun chord(root: Note, extensions: List<Note>): ChordType {
        val intervals = fetchIntervals(root.getIndex(), extensions)
        val chordType = fetchChordType(intervals)

        return chordType
    }

    fun interval(root: Note, extensions: List<Note>): Set<Int> {
        return fetchIntervals(root.getIndex(), extensions)
    }


    private fun fetchIntervals(start: Int, extensions: List<Note>) : Set<Int> {
        val intervals : MutableSet<Int> = mutableSetOf(0,)

        for (note in extensions) {
            val end = note.getIndex()
            // Interval index is on right side & greater than current
            if (start <= end) {
                intervals.add(end - start)
            }
            // Interval index is on left side & wrapped around
            else {
                intervals.add((12 - start) + end)
            }
        }

        return intervals
    }

    private fun fetchChordType(intervals: Set<Int>) : ChordType {
        when (intervals) {
            setOf(ROOT, THIRD, FIFTH) -> { type = ChordType.Major }
            setOf(ROOT, FLAT_THIRD, FIFTH) -> {type = ChordType.Minor}
            setOf(ROOT, FIFTH) -> {type = ChordType.PowerChord}
            setOf(ROOT, FOURTH, FIFTH) -> {type = ChordType.Suspended}
            setOf(ROOT, SECOND, FIFTH) -> {type = ChordType.Suspended}
            setOf(ROOT, THIRD, FLAT_SIXTH) -> {type = ChordType.Augmented}
            setOf(ROOT, FLAT_THIRD, FLAT_FIFTH, SIXTH) -> {type = ChordType.FullDiminished}
            setOf(ROOT, FLAT_THIRD, FLAT_FIFTH, FLAT_SEVENTH) -> {type = ChordType.HalfDiminished}
            setOf(ROOT, THIRD, FIFTH, FLAT_SEVENTH) -> {type = ChordType.DominantSeventh}
            setOf(ROOT, THIRD, FIFTH, SEVENTH) -> {type = ChordType.MajorSeventh}
            setOf(ROOT, FLAT_THIRD, FIFTH, FLAT_SEVENTH) -> {type = ChordType.MinorSeventh}
            setOf(ROOT, SECOND, FLAT_THIRD, FIFTH, FLAT_SEVENTH) -> {type = ChordType.MinorNinth}
            setOf(ROOT, SECOND, THIRD, FIFTH, SEVENTH) -> {type = ChordType.MajorNinth}
            setOf(ROOT, THIRD, FIFTH, SIXTH) -> {type = ChordType.MajorSixth}
            setOf(ROOT, FLAT_THIRD, FIFTH, FLAT_SIXTH) -> {type = ChordType.MinorSixth}
            else -> {type = ChordType.Undefined}
        }
        return type
    }
}