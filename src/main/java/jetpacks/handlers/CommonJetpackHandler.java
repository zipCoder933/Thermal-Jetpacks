package jetpacks.handlers;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class CommonJetpackHandler {

    private static final Set<Player> INVERTED = new HashSet<>();
    private static final Map<Player, Boolean> HOLDING_UP = new HashMap<>();
    private static final Map<Player, Boolean> HOLDING_DOWN = new HashMap<>();
    private static final Map<Player, Boolean> HOLDING_FORWARDS = new HashMap<>();
    private static final Map<Player, Boolean> HOLDING_BACKWARDS = new HashMap<>();
    private static final Map<Player, Boolean> HOLDING_LEFT = new HashMap<>();
    private static final Map<Player, Boolean> HOLDING_RIGHT = new HashMap<>();

    public static boolean isHoldingUp(Player player) {
        return HOLDING_UP.containsKey(player) && HOLDING_UP.get(player);
    }

    public static boolean isInverted(Player player) {
        return INVERTED.contains(player);
    }

    public static boolean isHoldingDown(Player player) {
        return HOLDING_DOWN.containsKey(player) && HOLDING_DOWN.get(player);
    }

    public static boolean isHoldingForwards(Player player) {
        return HOLDING_FORWARDS.containsKey(player) && HOLDING_FORWARDS.get(player);
    }

    public static boolean isHoldingBackwards(Player player) {
        return HOLDING_BACKWARDS.containsKey(player) && HOLDING_BACKWARDS.get(player);
    }

    public static boolean isHoldingLeft(Player player) {
        return HOLDING_LEFT.containsKey(player) && HOLDING_LEFT.get(player);
    }

    public static boolean isHoldingRight(Player player) {
        return HOLDING_RIGHT.containsKey(player) && HOLDING_RIGHT.get(player);
    }

    public static void update(Player player, boolean invert, boolean up, boolean down, boolean forwards,
                              boolean backwards, boolean left, boolean right) {
        if(invert) {
            INVERTED.add(player);
        } else {
            INVERTED.remove(player);
        }

        HOLDING_UP.put(player, up);
        HOLDING_DOWN.put(player, down);
        HOLDING_FORWARDS.put(player, forwards);
        HOLDING_BACKWARDS.put(player, backwards);
        HOLDING_LEFT.put(player, left);
        HOLDING_RIGHT.put(player, right);
    }

    public static void clear() {
        HOLDING_UP.clear();
        HOLDING_FORWARDS.clear();
        INVERTED.clear();
        HOLDING_DOWN.clear();
        HOLDING_BACKWARDS.clear();
        HOLDING_LEFT.clear();
        HOLDING_RIGHT.clear();
    }

    public static void remove(Player player) {
        HOLDING_UP.remove(player);
        HOLDING_FORWARDS.remove(player);
        INVERTED.remove(player);
        HOLDING_DOWN.remove(player);
        HOLDING_BACKWARDS.remove(player);
        HOLDING_LEFT.remove(player);
        HOLDING_RIGHT.remove(player);
    }

    @SubscribeEvent
    public void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        remove(event.getEntity());
    }

    @SubscribeEvent
    public void onChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        remove(event.getEntity());
    }
}
