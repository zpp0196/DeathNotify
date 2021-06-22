package me.zpp0196.mods.config

/**
 * @author zpp0196
 */
abstract class ListConfig<T>(
    path: String,
    defaultValue: List<T> = emptyList()
) : Config<List<T>, T>(path, defaultValue) {

    abstract fun add(value: T)

    abstract fun remove(value: T)

    abstract fun clear()

    override fun test(value: T?): Boolean {
        for (config in get()) {
            if (config == value) {
                return true
            }
        }
        return false
    }
}