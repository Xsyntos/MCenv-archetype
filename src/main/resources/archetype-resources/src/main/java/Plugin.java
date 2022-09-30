package ${package};

import org.bukkit.plugin.java.JavaPlugin;

import ${package}.Initializers.CommandInit;

public class Plugin extends JavaPlugin {
    public static JavaPlugin plugin;
    
    @Override
    public void onEnable() {
        Plugin.plugin = this;
        getLogger().info("Plugin is Starting!");
        this.saveDefaultConfig();
        getLogger().info("Config Loaded!");
        CommandInit.init();
        getLogger().info("Registered commands!");

    }
    @Override
    public void onDisable() {
        getLogger().info("Plugin is Disabling!");
    }
}
