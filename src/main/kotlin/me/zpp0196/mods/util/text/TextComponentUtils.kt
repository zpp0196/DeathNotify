package me.zpp0196.mods.util.text

import net.minecraft.util.text.*
import net.minecraft.util.text.TextComponentUtils

/**
 * @author zpp0196
 */
object TextComponentUtils {
    fun coordinates(x: String, y: String, z: String): IFormattableTextComponent {
        return TextComponentUtils.wrapInSquareBrackets(TranslationTextComponent("chat.coordinates", x, y, z))
    }
}

fun String.component(color: TextFormatting? = null) = StringTextComponent(this).apply {
    color?.let {
        withStyle(color)
    }
}
