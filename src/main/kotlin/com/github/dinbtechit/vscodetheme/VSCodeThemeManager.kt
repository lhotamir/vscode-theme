package com.github.dinbtechit.vscodetheme

import com.github.dinbtechit.vscodetheme.settings.VSCodeThemeSettingsStore
import com.intellij.ide.plugins.IdeaPluginDescriptor
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.ide.ui.LafManager
import com.intellij.openapi.extensions.PluginId

class VSCodeThemeManager {
    companion object {
        const val pluginId = "com.github.dinbtechit.vscodetheme"
        private var myObject: VSCodeThemeManager? = null
        fun getInstance(): VSCodeThemeManager {
            if (myObject == null) {
                myObject = VSCodeThemeManager()
            }
            return myObject!!
        }
    }

    private fun getPlugin(): IdeaPluginDescriptor? = PluginManagerCore.getPlugin(PluginId.getId(pluginId))
    fun isVSCodeThemeReady(): Boolean {
        val vscodeTheme = LafManager.getInstance().installedLookAndFeels.first { it.name == "VSCode Dark" }
        if (vscodeTheme != null && getPlugin()?.isEnabled != null) {
            return true
        }
        return false
    }

    fun switchToVSCodeTheme(always: Boolean = false) {
        val vscodeTheme = LafManager.getInstance().installedLookAndFeels.first { it.name == "VSCode Dark" }
        LafManager.getInstance().currentLookAndFeel = vscodeTheme
        if (always) {
            val settings = VSCodeThemeSettingsStore.instance
            settings.alwaysApply = true
            settings.showNotificationOnUpdate = false
        }
    }
}
