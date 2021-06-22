package me.zpp0196.mods.util.entity.player

import net.minecraft.entity.player.ServerPlayerEntity

/**
 * @author zpp0196
 */

val ServerPlayerEntity.displayNameString: String
    get() = displayName.string

val ServerPlayerEntity.gamemodeString: String
    get() = gameMode.gameModeForPlayer.getName()

val ServerPlayerEntity.difficultyString: String
    get() = commandSenderWorld.difficulty.key

val ServerPlayerEntity.dimension: String
    get() = commandSenderWorld.dimension().location().toString()