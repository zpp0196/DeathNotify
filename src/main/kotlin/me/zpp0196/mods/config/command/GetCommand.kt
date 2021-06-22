package me.zpp0196.mods.config.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import me.zpp0196.mods.config.Config
import me.zpp0196.mods.util.command.sendConfig
import net.minecraft.command.CommandSource

/**
 * @author zpp0196
 */
class GetCommand(private val config: Config<*, *>) : Command<CommandSource> {
    override fun run(context: CommandContext<CommandSource>): Int {
        return context.sendConfig(config)
    }
}