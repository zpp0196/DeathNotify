package me.zpp0196.mods.config.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import me.zpp0196.mods.config.Config
import me.zpp0196.mods.util.command.runWithArgument
import me.zpp0196.mods.util.command.sendConfig
import net.minecraft.command.CommandSource

/**
 * @author zpp0196
 */
class SetBooleanCommand(private val config: Config<Boolean, Boolean>) : Command<CommandSource> {
    override fun run(context: CommandContext<CommandSource>): Int {
        return context.runWithArgument<Boolean> {
            config.set(it)
            context.sendConfig(config)
        }
    }
}