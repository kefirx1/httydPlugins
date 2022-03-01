package pl.dev.httyd.httydplugins;

import nl.svenar.PowerRanks.Cache.CacheManager;
import org.bukkit.entity.Player;

import java.util.List;

public class PowerRanksExtensions {

    public String getUserTaq(Player player) {
        return getUserTagFromApi(player);
    }

    private String getUserTagFromApi(Player player) {
        if (player == null) {
            return "";
        } else {
            List<String> userTagsNames = CacheManager.getPlayer(player.getUniqueId().toString()).getUsertags();
            return userTagsNames.get(0);
        }
    }
}
