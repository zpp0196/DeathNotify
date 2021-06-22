package me.zpp0196.mods.config.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import me.zpp0196.mods.config.StringListConfig
import me.zpp0196.mods.util.command.runWithArgument
import me.zpp0196.mods.util.command.sendConfig
import net.minecraft.command.CommandSource
import net.minecraft.util.ResourceLocation

/**
 * @author zpp0196
 */
class RemoveResourceLocationCommand(private val config: StringListConfig) : Command<CommandSource> {
    override fun run(context: CommandContext<CommandSource>): Int {
        context.runWithArgument<ResourceLocation> { value ->
            config.remove(value.toString())
        }
        return context.sendConfig(config)
    }
}