package jetpacks.handlers;

import jetpacks.RegistryHandler;
import jetpacks.item.JetpackItem;
import jetpacks.network.NetworkHandler;
import jetpacks.network.packets.PacketToggleHUD;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static jetpacks.ThermalJetpacks.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class EquipmentChangeHandler {

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        /**
         * Create an event to turn on the HUD when the player equips the goggles
         */
        if (event.getTo().getItem() == RegistryHandler.PILOT_GOGGLES_GOLD.get() ||
                event.getTo().getItem() == RegistryHandler.PILOT_GOGGLES_IRON.get()) {
            System.out.println("Equipping goggles");
            NetworkHandler.sendToClient(new PacketToggleHUD(true), (ServerPlayer) player);

        } else if (event.getFrom().getItem() == RegistryHandler.PILOT_GOGGLES_GOLD.get() ||
                event.getFrom().getItem() == RegistryHandler.PILOT_GOGGLES_IRON.get()) {
            System.out.println("Unequipping goggles");
            NetworkHandler.sendToClient(new PacketToggleHUD(false), (ServerPlayer) player);
        }

        if (event.getTo().getItem() instanceof JetpackItem jetpack && event.getSlot() == EquipmentSlot.CHEST) {
            ClientJetpackHandler.jetpackItemStack = event.getTo();
            ClientJetpackHandler.jetpackItem = jetpack;
        } else if (event.getTo().getItem() instanceof JetpackItem jetpack) {
            ClientJetpackHandler.jetpackItem = null;
            ClientJetpackHandler.jetpackItemStack = null;
        }

    }
}