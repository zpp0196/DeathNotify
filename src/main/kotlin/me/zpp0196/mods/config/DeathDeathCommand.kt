package me.zpp0196.mods.config

import com.mojang.brigadier.CommandDispatcher
import me.zpp0196.mods.config.command.*
import me.zpp0196.mods.deathnotify.DeathNotifyConfig
import me.zpp0196.mods.util.command.ArgumentBuilder
import me.zpp0196.mods.util.command.CommandBuilder
import net.minecraft.command.CommandSource
import net.minecraft.world.Difficulty
import net.minecraft.world.GameType
import me.zpp0196.mods.util.command.CommandArgumentBuilder as Builder

/**
 * @author zpp0196
 */
object DeathDeathCommand {

    fun register(dispatcher: CommandDispatcher<CommandSource>, command: Builder) {
        val serverConfig = DeathNotifyConfig.serverConfig
        val regexConfigList = mapOf(
            serverConfig.gamemodes to this::buildGamemodesCommand,
            serverConfig.difficulties to this::buildDifficultiesCommand,
            serverConfig.dimensions to this::buildDimensionsCommand
        )
        val commonConfigList = arrayListOf<Config<*, *>>()
        commonConfigList.addAll(regexConfigList.keys)
        commonConfigList.add(serverConfig.enabled)

        command.executes(GetAllCommand(commonConfigList))

        serverConfig.enabled.let {
            command.then(
                CommandBuilder.admin(it.path).executes(GetCommand(it))
                    .then(ArgumentBuilder.booleanArgument().executes(SetBooleanCommand(it)))
            )
        }
        for ((config, build) in regexConfigList) {
            val builder = CommandBuilder.everyone(config.path).executes(GetCommand(config))
            builder.then(CommandBuilder.everyone("get").executes(GetCommand(config)))
            builder.then(CommandBuilder.admin("clear").executes(ClearCommand(config)))
            builder.then(CommandBuilder.admin("restore").executes(RestoreCommand(config)))
            val addBuilder = CommandBuilder.admin("add").executes(AddStringCommand(config))
            val removeBuilder = CommandBuilder.admin("remove").executes(RemoveStringCommand(config))
            build(config, addBuilder, removeBuilder)
            addBuilder.then(ArgumentBuilder.stringArgument().executes(AddStringCommand(config)))
            removeBuilder.then(ArgumentBuilder.stringArgument().executes(RemoveStringCommand(config)))
            command.then(builder.then(addBuilder).then(removeBuilder))
        }

        command.then(CommandBuilder.admin("clear").executes(ClearAllCommand(regexConfigList.keys)))
        command.then(CommandBuilder.admin("restore").executes(RestoreAllCommand(commonConfigList)))

        dispatcher.register(command)
    }

    private fun buildGamemodesCommand(config: StringListConfig, addBuilder: Builder, removeBuilder: Builder) {
        for (gameType in GameType.values()) {
            if (gameType == GameType.NOT_SET) {
                continue
            }
            val name = gameType.getName()
            addBuilder.then(CommandBuilder.admin(name).executes(AddStringCommand(config, name)))
            removeBuilder.then(CommandBuilder.admin(name).executes(RemoveStringCommand(config, name)))
        }
    }

    private fun buildDifficultiesCommand(config: StringListConfig, addBuilder: Builder, removeBuilder: Builder) {
        for (difficulty in Difficulty.values()) {
            val key = difficulty.key
            addBuilder.then(CommandBuilder.admin(key).executes(AddStringCommand(config, key)))
            removeBuilder.then(CommandBuilder.admin(key).executes(RemoveStringCommand(config, key)))
        }
    }

    private fun buildDimensionsCommand(config: StringListConfig, addBuilder: Builder, removeBuilder: Builder) {
        addBuilder.then(ArgumentBuilder.dimensionArgument().executes(AddResourceLocationCommand(config)))
        removeBuilder.then(ArgumentBuilder.dimensionArgument().executes(RemoveResourceLocationCommand(config)))
    }
}