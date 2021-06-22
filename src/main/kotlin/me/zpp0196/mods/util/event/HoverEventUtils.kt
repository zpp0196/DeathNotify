package me.zpp0196.mods.util.event

import net.minecraft.util.text.TranslationTextComponent
import net.minecraft.util.text.event.HoverEvent

/**
 * @author zpp0196
 */
object HoverEventUtils {
    fun chatCoordinatesTooltip(): HoverEvent {
        return HoverEvent(HoverEvent.Action.SHOW_TEXT, TranslationTextComponent("chat.coordinates.tooltip"))
    }
}