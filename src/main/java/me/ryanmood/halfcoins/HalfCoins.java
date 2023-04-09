package me.ryanmood.halfcoins;

import me.ryanmood.halfcoins.listeners.DeathListener;
import me.ryanmood.halfcoins.utils.RyMessageUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * This software and its content is copyright of RyanMoodGAMING - © RyanMoodGAMING 2023. All rights reserved.
 * Any redistribution or reproduction of part or all of the contents in any form is prohibited other than the following:
 * you may print or download to a local hard disk extracts for your personal and non-commercial use only
 * you may copy the content to individual third parties for their personal use, but only if you acknowledge the website as the source of the material
 * You may not, except with our express written permission, distribute or commercially exploit the content. Nor may you transmit it or store it in any other website or other form of electronic retrieval system.
 */

public final class HalfCoins extends JavaPlugin {

    private static Economy economy = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!setupEconomy() ) {
           RyMessageUtils.sendConsoleMessage(true, "&cDisabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public static Economy getEconomy() {
        return economy;
    }
}
