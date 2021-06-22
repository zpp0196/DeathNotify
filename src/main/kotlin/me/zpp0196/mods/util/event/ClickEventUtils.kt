package me.zpp0196.mods.util.event

import net.minecraft.util.text.event.ClickEvent

/**
 * @author zpp0196
 */
object ClickEventUtils {
    fun tp(dimension: String, x: String, y: String, z: String?): ClickEvent {
        return ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/execute in $dimension run tp $x $y $z")
    }
}