package me.zpp0196.mods.config

import me.zpp0196.mods.util.config.BooleanValue
import net.minecraftforge.common.ForgeConfigSpec

/**
 * @author zpp0196
 */
abstract class BooleanConfig(path: String, defaultValue: Boolean) : Config<Boolean, Boolean>(path, defaultValue) {

    override fun buildImpl(builder: ForgeConfigSpec.Builder): BooleanValue =
        builder.comment(
            " $commendHeader",
            " \tDefault value: $defaultValue"
        ).define(path, defaultValue)

    override fun test(value: Boolean?): Boolean {
        return get()
    }
}