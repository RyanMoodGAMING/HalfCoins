package me.ryanmood.halfcoins.listeners;

import com.cryptomorin.xseries.XSound;
import me.ryanmood.halfcoins.HalfCoins;
import me.ryanmood.halfcoins.utils.RyMessageUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/*
 * This software and its content is copyright of RyanMoodGAMING - © RyanMoodGAMING 2023. All rights reserved.
 * Any redistribution or reproduction of part or all of the contents in any form is prohibited other than the following:
 * you may print or download to a local hard disk extracts for your personal and non-commercial use only
 * you may copy the content to individual third parties for their personal use, but only if you acknowledge the website as the source of the material
 * You may not, except with our express written permission, distribute or commercially exploit the content. Nor may you transmit it or store it in any other website or other form of electronic retrieval system.
 */

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        OfflinePlayer player = e.getEntity();

        Economy economy = HalfCoins.getEconomy();
        double bal = economy.getBalance(player);
        double newBal = bal / 2;

        economy.withdrawPlayer(player, newBal);

        RyMessageUtils.sendPlayerMessage(e.getEntity(), false, "&cYou lost half your coins.");
       // e.getEntity().playSound(e.getEntity().getLocation(), XSound.BLOCK_ANVIL_PLACE.toString(), 2f, 2f);
        XSound.BLOCK_ANVIL_PLACE.play(e.getEntity());

    }

}
