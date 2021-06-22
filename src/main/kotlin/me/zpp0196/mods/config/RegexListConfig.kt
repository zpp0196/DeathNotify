package me.zpp0196.mods.config

import me.zpp0196.mods.util.config.StringListConfigValue
import me.zpp0196.mods.util.config.toTomlString
import net.minecraftforge.common.ForgeConfigSpec
import java.util.regex.Pattern

/**
 * @author zpp0196
 */
abstract class RegexListConfig(path: String, defaultValue: List<String>) : StringListConfig(path, defaultValue) {

    final override fun buildImpl(builder: ForgeConfigSpec.Builder): StringListConfigValue =
        builder.comment(
            " $commendHeader",
            " \tSupport for regular expressions",
            " \tDefault value: ${defaultValue.toTomlString()}",
            " \tDisable for all $path: [\"\"]"
        ).defineList(path, defaultValue) { true }

    override fun test(value: String?): Boolean {
        value ?: return false
        for (config in get()) {
            if (config == value) {
                return true
            }
            if (Pattern.compile(config).matcher(value).matches()) {
                return true
            }
        }
        return false
    }

    override fun add(value: String) {
        val list = ArrayList(get())
        if (!list.contains(value) && value.isNotEmpty()) {
            list.add(value)
            list.removeAll(listOf(""))
        }
        set(list)
    }

    override fun remove(value: String) {
        val list = ArrayList(get())
        if (list.contains(value)) {
            list.remove(value)
        }
        if (list.isEmpty()) {
            clear()
        } else {
            set(list)
        }
    }

    override fun clear() {
        set(listOf(""))
    }
}