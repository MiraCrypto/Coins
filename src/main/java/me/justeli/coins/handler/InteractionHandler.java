package me.justeli.coins.handler;

import me.justeli.coins.Coins;
import me.justeli.coins.item.CoinUtil;
import me.justeli.coins.util.Util;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/** Created by Eli on 2/4/2017. */
public class InteractionHandler
        implements Listener
{
    private final Coins coins;

    public InteractionHandler (Coins coins)
    {
        this.coins = coins;
    }

    @EventHandler
    public void coinPlace (PlayerInteractEvent event)
    {
        if (event.getAction() == Action.PHYSICAL)
            return;

        if (!this.coins.getCoinUtil().isWithdrawnCoin(event.getItem()))
            return;

        Player player = event.getPlayer();

        // because of .setAmount(0) AND Container, players have to drop coin instead
        if (!player.hasPermission("coins.withdraw"))
        {
            event.setCancelled(true);
            return;
        }

        if (event.getClickedBlock() == null || !(event.getClickedBlock().getState() instanceof Container))
        {
            event.setCancelled(true);
            double amount = this.coins.getCoinUtil().getValue(event.getItem());

            if (event.getItem() == null)
                return;

            event.getItem().setAmount(0);

            this.coins.getPickupHandler().giveMoney(player, amount);
            Util.playCoinPickupSound(player);
        }
    }
}
