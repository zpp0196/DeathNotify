package me.zpp0196.mods.util.command

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import me.zpp0196.mods.util.entity.CommandPermissionLevel
import net.minecraft.command.CommandSource
import net.minecraft.command.Commands

typealias CommandArgumentBuilder = LiteralArgumentBuilder<CommandSource>

/**
 * @author zpp0106
 */
object CommandBuilder {
    fun everyone(literal: String): CommandArgumentBuilder {
        return Commands.literal(literal).requires { it.hasPermission(CommandPermissionLevel.EVERYONE) }
    }

    fun admin(literal: String): CommandArgumentBuilder {
        return Commands.literal(literal).requires { it.hasPermission(CommandPermissionLevel.ADMIN) }
    }
}