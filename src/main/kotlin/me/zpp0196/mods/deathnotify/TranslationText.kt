package me.zpp0196.mods.deathnotify

import net.minecraft.util.text.TranslationTextComponent

/**
 * @author zpp0196
 */
enum class TranslationText(private val key: String) {
    DEATH_DIMENSION("chat.death_dimension"),
    DEATH_POSITION("chat.death_position");

    fun component(vararg args: Any = emptyArray()) = TranslationTextComponent(key, *args)
}