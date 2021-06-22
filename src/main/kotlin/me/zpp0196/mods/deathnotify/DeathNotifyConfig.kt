package me.zpp0196.mods.deathnotify

import me.zpp0196.mods.config.BooleanConfig
import me.zpp0196.mods.config.RegexListConfig
import net.minecraftforge.common.ForgeConfigSpec
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.config.ModConfig

/**
 * @author zpp0196
 */
object DeathNotifyConfig {

    private const val GAMEMODE_ALl = ".+"
    private const val DIFFICULTY_ALL = ".+"
    private const val DIMENSION_ALL = ".+:.+"

    private val serverBuilder = ForgeConfigSpec.Builder().configure { ServerConfig(it) }
    val serverConfig: ServerConfig = serverBuilder.left

    fun register() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, serverBuilder.right)
    }

    class ServerConfig(builder: ForgeConfigSpec.Builder) {
        val enabled: BooleanConfig
        val gamemodes: RegexListConfig
        val difficulties: RegexListConfig
        val dimensions: RegexListConfig

        init {
            builder.push("death_notify")
            enabled = DeathSwitchConfig.build(builder, "enabled", true)
            gamemodes = DeathRegexListConfig.build(builder, "gamemodes", listOf(GAMEMODE_ALl))
            difficulties = DeathRegexListConfig.build(builder, "difficulties", listOf(DIFFICULTY_ALL))
            dimensions = DeathRegexListConfig.build(builder, "dimensions", listOf(DIMENSION_ALL))
            builder.pop()
        }
    }

    private class DeathSwitchConfig private constructor(path: String, defaultValue: Boolean) :
        BooleanConfig(path, defaultValue) {
        override val commendHeader: String
            get() = "The switch of notify death"

        companion object {
            fun build(builder: ForgeConfigSpec.Builder, path: String, defaultValue: Boolean) =
                DeathSwitchConfig(path, defaultValue).apply { build(builder) }
        }
    }

    private class DeathRegexListConfig private constructor(
        path: String, defaultValue: List<String>
    ) : RegexListConfig(path, defaultValue) {
        override val commendHeader: String
            get() = "Enable notification of position of death for $path"

        companion object {
            fun build(builder: ForgeConfigSpec.Builder, path: String, defaultValue: List<String>) =
                DeathRegexListConfig(path, defaultValue).apply { build(builder) }
        }
    }
}