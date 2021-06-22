package me.zpp0196.mods.config.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import me.zpp0196.mods.config.StringListConfig
import me.zpp0196.mods.util.command.runWithArgument
import me.zpp0196.mods.util.command.sendConfig
import net.minecraft.command.CommandSource

/**
 * @author zpp0196
 */
class RemoveStringCommand(private val config: StringListConfig, private val value: String? = null) :
    Command<CommandSource> {
    override fun run(context: CommandContext<CommandSource>): Int {
        value?.let {
            config.remove(it)
        } ?: context.runWithArgument<String> { value ->
            config.remove(value)
        }
        return context.sendConfig(config)
    }
}