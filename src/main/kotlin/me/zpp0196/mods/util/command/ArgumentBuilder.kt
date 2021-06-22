package me.zpp0196.mods.util.command

import com.mojang.brigadier.arguments.BoolArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import net.minecraft.command.CommandSource
import net.minecraft.command.Commands
import net.minecraft.command.arguments.DimensionArgument
import net.minecraft.util.ResourceLocation

/**
 * @author zpp0196
 */

object ArgumentBuilder {

    const val NAME_VALUE = "value"

    fun booleanArgument(name: String = NAME_VALUE): RequiredArgumentBuilder<CommandSource, Boolean> {
        return Commands.argument(name, BoolArgumentType.bool())
    }

    fun stringArgument(name: String = NAME_VALUE): RequiredArgumentBuilder<CommandSource, String> {
        return Commands.argument(name, StringArgumentType.string())
    }

    fun dimensionArgument(name: String = NAME_VALUE): RequiredArgumentBuilder<CommandSource, ResourceLocation> {
        return Commands.argument(name, DimensionArgument.dimension())
    }
}