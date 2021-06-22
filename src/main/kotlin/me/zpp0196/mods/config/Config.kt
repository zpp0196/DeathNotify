package me.zpp0196.mods.config

import me.zpp0196.mods.util.text.component
import net.minecraft.util.text.IFormattableTextComponent
import net.minecraftforge.common.ForgeConfigSpec

/**
 * @author zpp0196
 */
abstract class Config<T, V>(val path: String, val defaultValue: T) {

    protected abstract val commendHeader: String
    protected lateinit var value: ForgeConfigSpec.ConfigValue<T>
    var parent: String? = null

    fun get(): T = value.get()

    fun set(value: T) {
        this.value.set(value)
        this.value.save()
    }

    open fun restore() = set(defaultValue)

    fun build(builder: ForgeConfigSpec.Builder) {
        value = buildImpl(builder)
    }

    abstract fun buildImpl(builder: ForgeConfigSpec.Builder): ForgeConfigSpec.ConfigValue<T>

    abstract fun test(value: V? = null): Boolean

    open fun toTextComponent(): IFormattableTextComponent {
        return "$path = ${get()}".component()
    }
}