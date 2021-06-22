package me.zpp0196.mods.util.command

import com.mojang.brigadier.context.CommandContext
import me.zpp0196.mods.config.Config
import me.zpp0196.mods.util.text.component
import net.minecraft.command.CommandSource

/**
 * @author zpp0196
 */

fun CommandContext<CommandSource>.sendConfig(config: Config<*, *>, broadcastToAdmins: Boolean = false): Int {
    source.sendSuccess(config.toTextComponent(), broadcastToAdmins)
    return 0
}

fun CommandContext<CommandSource>.sendSuccess(message: String, broadcastToAdmins: Boolean = false): Int {
    source.sendSuccess(message.component(), broadcastToAdmins)
    return 0
}

fun CommandContext<CommandSource>.sendFailure(message: String): Int {
    source.sendFailure(message.component())
    return -1
}

inline fun <reified T> CommandContext<CommandSource>.runWithArgument(
    name: String = ArgumentBuilder.NAME_VALUE,
    per: (value: T) -> Unit
): Int {
    return try {
        per.invoke(getArgument(name, T::class.java))
        0
    } catch (e: Exception) {
        sendFailure(e.message!!)
    }
}
