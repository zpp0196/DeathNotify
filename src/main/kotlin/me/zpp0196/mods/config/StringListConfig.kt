package me.zpp0196.mods.config

import me.zpp0196.mods.util.config.toTomlString
import me.zpp0196.mods.util.text.component
import net.minecraft.util.text.IFormattableTextComponent

/**
 * @author zpp0196
 */
abstract class StringListConfig(path: String, defaultValue: List<String> = emptyList()) :
    ListConfig<String>(path, defaultValue) {
    override fun toTextComponent(): IFormattableTextComponent {
        return "$path = ${get().toTomlString()}".component()
    }
}