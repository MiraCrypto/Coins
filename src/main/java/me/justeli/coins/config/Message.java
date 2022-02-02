package me.justeli.coins.config;

import me.justeli.coins.util.Util;
import java.util.HashMap;

/** Created by Eli on 4/24/2017. */
public enum Message
{
    LOADED_SETTINGS ("&3Currently loaded settings of the Coins configuration."),
    NO_PERMISSION ("&4You do not have access to that command."),
    RELOAD_SUCCESS ("&eConfig of &6Coins &ehas been reloaded in &a{0}ms&e."),
    MINOR_ISSUES ("&cThere were some warnings while reloading, check console."),
    CHECK_SETTINGS ("&eYou can check the loaded settings with &f/coins settings&e."),
    WITHDRAW_COINS ("&eYou withdrawn &f{$}{0} &eand received &f{0} coins&e for it."),
    NOT_THAT_MUCH ("&cYou are not allowed to withdraw that much."),
    COINS_DISABLED ("&cCoins are disabled in this world."),
    SPAWNED_COINS ("&9Spawned {0} coins in radius {1} around {2}."),
    REMOVED_COINS ("&9Removed a total of {0} coins."),
    INVENTORY_FULL ("&cYou cannot withdraw when your inventory is full!"),

    PLAYER_NOT_FOUND ("&4That player could not be found."),
    COORDS_NOT_FOUND ("&4Those coords or the world couldn't be found."),

    INVALID_AMOUNT ("&cThat is an invalid amount."),
    INVALID_NUMBER ("&cThat is an invalid number."),
    INVALID_RADIUS ("&cThat is an invalid radius."),

    DROP_USAGE ("&c/coins drop <player|x,y,z[,world]> <amount> [radius]"),
    REMOVE_USAGE ("&c/coins remove [radius|all] &7- remove coins in a radius"),
    SETTINGS_USAGE ("&c/coins settings &7- list the currently loaded settings"),
    RELOAD_USAGE ("&c/coins reload &7- reload the settings from config.yml"),
    VERSION_CHECK ("&c/coins version &7- check if there's a new release"),
    WITHDRAW_USAGE ("&c/withdraw <worth> [amount] &7- withdraw money into coins"),
    TOGGLE_USAGE ("&c/coins toggle &7- disable or enable Coins globally"),

    DISABLED_REASONS ("&4Coins is disabled for the following reason(s):"),
    CURRENTLY_INSTALLED ("&eVersion currently installed: &f{0}"),
    LATEST_VERSION ("&eLatest released version: &f{0}"),
    UP_TO_DATE ("&aYou're up to date with version {0}."),
    LATEST_RETRIEVE_FAIL ("&cCouldn't get the latest version of Coins."),
    CONSIDER_UPDATING ("&cConsider updating the plugin to version {0}."),

    ENABLED ("&aenabled"),
    DISABLED ("&cdisabled"),

    GLOBALLY_DISABLED_INFORM("&eCoins has been globally {0}&e. Toggle with &f/coins toggle&e."),
    DISABLED_DESCRIPTION ("&eWhen disabled, coins will not drop and withdrawing coins isn't possible. Picking up coins " +
            "that were already on the ground and depositing coins is still possible."),
    WITHDRAWING_DISABLED ("&cWithdrawing coins is disabled on this server."),
    GLOBALLY_DISABLED ("Currently globally disabled."),
    OUTDATED ("(outdated → /coins update)"),
    ;

    final String defaultMessage;

    Message (String defaultMessage)
    {
        this.defaultMessage = defaultMessage;
    }

    static final HashMap<Message, String> MESSAGES = new HashMap<>();

    @Override
    public String toString ()
    {
        return MESSAGES.computeIfAbsent(this, empty -> Util.color(Util.formatCurrency(this.defaultMessage)));
    }

    public String replace (Object... replacements)
    {
        String message = toString();

        for (int i = 0; i < replacements.length; i++)
        {
            message = message.replace("{" + i + "}", replacements[i].toString());
        }

        return message;
    }
}
