package me.zpp0196.mods.config.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import me.zpp0196.mods.config.ListConfig
import me.zpp0196.mods.util.command.sendConfig
import net.minecraft.command.CommandSource

/**
 * @author zpp0196
 */
class ClearCommand(private val config: ListConfig<*>) : Command<CommandSource> {
    override fun run(context: CommandContext<CommandSource>): Int {
        config.clear()
        return context.sendConfig(config)
    }
}