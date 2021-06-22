package me.zpp0196.mods.config.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import me.zpp0196.mods.config.ListConfig
import me.zpp0196.mods.util.command.sendConfig
import net.minecraft.command.CommandSource

/**
 * @author zpp0196
 */
class ClearAllCommand(private val list: Collection<ListConfig<*>>) : Command<CommandSource> {
    override fun run(context: CommandContext<CommandSource>): Int {
        for (config in list) {
            config.clear()
            context.sendConfig(config)
        }
        return 0
    }
}