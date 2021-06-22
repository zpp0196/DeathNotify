package me.zpp0196.mods.util.config

import net.minecraftforge.common.ForgeConfigSpec

/**
 * @author zpp0196
 */

typealias BooleanValue = ForgeConfigSpec.BooleanValue
typealias ListConfigValue<T> = ForgeConfigSpec.ConfigValue<List<T>>
typealias StringListConfigValue = ListConfigValue<String>

fun List<String>.toTomlString() = map { "\"$it\"" }.toString()
