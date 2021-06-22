package me.zpp0196.mods.deathnotify

import me.zpp0196.mods.config.DeathDeathCommand
import me.zpp0196.mods.util.command.CommandBuilder
import me.zpp0196.mods.util.entity.CommandPermissionLevel
import me.zpp0196.mods.util.entity.player.difficultyString
import me.zpp0196.mods.util.entity.player.dimension
import me.zpp0196.mods.util.entity.player.displayNameString
import me.zpp0196.mods.util.entity.player.gamemodeString
import me.zpp0196.mods.util.event.ClickEventUtils
import me.zpp0196.mods.util.event.HoverEventUtils
import me.zpp0196.mods.util.text.TextComponentUtils
import me.zpp0196.mods.util.text.component
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.text.Style
import net.minecraft.util.text.TextFormatting
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import java.text.DecimalFormat

/**
 * @author zpp0196
 */
@Mod("death_notify")
class DeathNotifyMod {

    private val logger = LogManager.getLogger()

    init {
        DeathNotifyConfig.register()
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun onServerStaring(event: RegisterCommandsEvent) {
        logger.info("onServerStarting...")
        DeathDeathCommand.register(event.dispatcher, CommandBuilder.everyone("deathnotify"))
    }

    @SubscribeEvent
    fun onClonePlayerEvent(event: PlayerEvent.Clone) {
        val player = event.original as? ServerPlayerEntity ?: return
        val death = event.isWasDeath
        val gamemode = player.gamemodeString
        val difficulty = player.difficultyString
        val dimension = player.dimension
        logger.info(
            "Clone player {}, gamemode: {}, difficulty: {}, dimension: {}, position: {}",
            player.displayNameString, gamemode, difficulty, dimension, player.position()
        )

        if (!death) {
            logger.warn("Ignore not death")
            return
        }

        val serverConfig = DeathNotifyConfig.serverConfig
        if (!serverConfig.enabled.test()) {
            logger.warn("Ignore disabled")
            return
        }

        if (!serverConfig.gamemodes.test(gamemode)) {
            logger.warn("Ignore gamemode: $gamemode")
            return
        }

        if (!serverConfig.difficulties.test(difficulty)) {
            logger.warn("Ignore difficulty: $difficulty")
            return
        }

        if (!serverConfig.dimensions.test(dimension)) {
            logger.warn("Ignore dimension: $dimension")
            return
        }

        val p = player.position()
        val f = DecimalFormat("0.000")
        val x = f.format(p.x())
        val y = f.format(p.y())
        val z = f.format(p.z())
        var message = TranslationText.DEATH_DIMENSION.component().append(": ")
        message.append(dimension.component(TextFormatting.RED))
        player.sendMessage(message, player.uuid)
        message = TranslationText.DEATH_POSITION.component().append(": ")
        message.append(TextComponentUtils.coordinates(x, y, z).withStyle { style: Style ->
            style.withColor(TextFormatting.GREEN).run {
                if (player.hasPermissions(CommandPermissionLevel.EXECUTE)) {
                    withClickEvent(ClickEventUtils.tp(dimension, x, y, z))
                        .withHoverEvent(HoverEventUtils.chatCoordinatesTooltip())
                } else this
            }
        })
        player.sendMessage(message, player.uuid)
    }
}