package domain.note

import kotlinx.datetime.LocalDateTime
import presentation.BabyBlueHex
import presentation.LightGreenHex
import presentation.RedOrangeHex
import presentation.RedPinkHex
import presentation.VioletHex

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created: LocalDateTime
) {
    companion object {
        private val colors = listOf(RedOrangeHex, RedPinkHex, LightGreenHex, BabyBlueHex, VioletHex)

        fun generateRandomColor() = colors.random()
    }
}
