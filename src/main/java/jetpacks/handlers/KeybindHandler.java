package jetpacks.handlers;

import com.mojang.blaze3d.platform.InputConstants;
import jetpacks.item.JetpackItem;
import jetpacks.network.NetworkHandler;
import jetpacks.network.packets.*;
import jetpacks.ui.JetpackScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import static jetpacks.ThermalJetpacks.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeybindHandler {

    public static KeyMapping JETPACK_GUI_KEY = new KeyMapping("keybind." + MOD_ID + ".jetpack_gui", GLFW.GLFW_KEY_K, "keybind." + MOD_ID + ".category");
    public static KeyMapping JETPACK_ENGINE_KEY = new KeyMapping("keybind." + MOD_ID + ".jetpack_engine", GLFW.GLFW_KEY_J, "keybind." + MOD_ID + ".category");
    public static KeyMapping JETPACK_HOVER_KEY = new KeyMapping("keybind." + MOD_ID + ".jetpack_hover", GLFW.GLFW_KEY_H, "keybind." + MOD_ID + ".category");
    public static KeyMapping JETPACK_EHOVER_KEY = new KeyMapping("keybind." + MOD_ID + ".jetpack_ehover", GLFW.GLFW_KEY_UNKNOWN, "keybind." + MOD_ID + ".category");
    public static KeyMapping JETPACK_CHARGER_KEY = new KeyMapping("keybind." + MOD_ID + ".jetpack_charger", GLFW.GLFW_KEY_UNKNOWN, "keybind." + MOD_ID + ".category");
    public static KeyMapping JETPACK_THROTTLE_INCREASE = new KeyMapping("keybind." + MOD_ID + ".jetpack_throttle_increase", GLFW.GLFW_KEY_PERIOD, "keybind." + MOD_ID + ".category");
    public static KeyMapping JETPACK_THROTTLE_DECREASE = new KeyMapping("keybind." + MOD_ID + ".jetpack_throttle_decrease", GLFW.GLFW_KEY_COMMA, "keybind." + MOD_ID + ".category");


    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        KeybindHandler.JETPACK_GUI_KEY = new KeyMapping("keybind.tjetpacks.jetpack_gui", GLFW.GLFW_KEY_K, "keybind.tjetpacks.category");
        event.register(KeybindHandler.JETPACK_GUI_KEY);
        KeybindHandler.JETPACK_ENGINE_KEY = new KeyMapping("keybind.tjetpacks.jetpack_engine", GLFW.GLFW_KEY_J, "keybind.tjetpacks.category");
        event.register(KeybindHandler.JETPACK_ENGINE_KEY);
        KeybindHandler.JETPACK_HOVER_KEY = new KeyMapping("keybind.tjetpacks.jetpack_hover", GLFW.GLFW_KEY_H, "keybind.tjetpacks.category");
        event.register(KeybindHandler.JETPACK_HOVER_KEY);
        KeybindHandler.JETPACK_EHOVER_KEY = new KeyMapping("keybind.tjetpacks.jetpack_ehover", GLFW.GLFW_KEY_UNKNOWN, "keybind.tjetpacks.category");
        event.register(KeybindHandler.JETPACK_EHOVER_KEY);
        KeybindHandler.JETPACK_CHARGER_KEY = new KeyMapping("keybind.tjetpacks.jetpack_charger", GLFW.GLFW_KEY_UNKNOWN, "keybind.tjetpacks.category");
        event.register(KeybindHandler.JETPACK_CHARGER_KEY);
        KeybindHandler.JETPACK_THROTTLE_INCREASE = new KeyMapping("keybind.tjetpacks.jetpack_throttle_increase", GLFW.GLFW_KEY_PERIOD, "keybind.tjetpacks.category");
        event.register(KeybindHandler.JETPACK_THROTTLE_INCREASE);
        KeybindHandler.JETPACK_THROTTLE_DECREASE = new KeyMapping("keybind.tjetpacks.jetpack_throttle_decrease", GLFW.GLFW_KEY_COMMA, "keybind.tjetpacks.category");
        event.register(KeybindHandler.JETPACK_THROTTLE_DECREASE);
    }


    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;
        if (player == null || event.getAction() != InputConstants.PRESS || Minecraft.getInstance().screen != null) {
            return;
        }

        ItemStack jetpackStack = ClientJetpackHandler.global_checkForEquippedJetpack(player);
        if (!jetpackStack.isEmpty() && jetpackStack.getItem() instanceof JetpackItem jetpack) {

            if (JETPACK_GUI_KEY.getKey().getValue() == event.getKey()) {
                Minecraft.getInstance().setScreen(new JetpackScreen());
            }
            if (JETPACK_ENGINE_KEY.getKey().getValue() == event.getKey()) {
                NetworkHandler.sendToServer(new PacketSetEngine(ToggleStatus.TOGGLE));
            }
            if (JETPACK_HOVER_KEY.getKey().getValue() == event.getKey()) {
                NetworkHandler.sendToServer(new PacketSetHover(ToggleStatus.TOGGLE));
            }
            if (JETPACK_EHOVER_KEY.getKey().getValue() == event.getKey()) {
                NetworkHandler.sendToServer(new PacketSetEHover(ToggleStatus.TOGGLE));
            }
            if (JETPACK_CHARGER_KEY.getKey().getValue() == event.getKey()) {
                NetworkHandler.sendToServer(new PacketSetCharger(ToggleStatus.TOGGLE));
            }
            if (JETPACK_THROTTLE_INCREASE.getKey().getValue() == event.getKey()) {
                NetworkHandler.sendToServer(new PacketUpdateThrottle(Math.max(0,
                        Math.min(100, jetpack.getThrottle(jetpackStack) + 10))));
            }
            if (JETPACK_THROTTLE_DECREASE.getKey().getValue() == event.getKey()) {
                NetworkHandler.sendToServer(new PacketUpdateThrottle(Math.max(0,
                        Math.min(100, jetpack.getThrottle(jetpackStack) - 10))));
            }
        }
    }
}
