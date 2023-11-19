package com.garif.core.util

import java.util.Random

fun Random.nextInt(range: IntRange): Int {
    return range.first + nextInt(range.last - range.start + 1)
}