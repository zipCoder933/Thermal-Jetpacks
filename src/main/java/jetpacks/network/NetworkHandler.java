package jetpacks.network;

import jetpacks.network.packets.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import static jetpacks.ThermalJetpacks.MOD_ID;

public class NetworkHandler {

    public static SimpleChannel CHANNEL_INSTANCE;
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        CHANNEL_INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, MOD_ID), () -> "1.0", s -> true, s -> true);

        CHANNEL_INSTANCE.messageBuilder(PacketToggleEngine.class, nextID())
                .encoder(PacketToggleEngine::toBytes)
                .decoder(PacketToggleEngine::new)
                .consumerNetworkThread(PacketToggleEngine::handle)
                .add();
        CHANNEL_INSTANCE.messageBuilder(PacketToggleHover.class, nextID())
                .encoder(PacketToggleHover::toBytes)
                .decoder(PacketToggleHover::new)
                .consumerNetworkThread(PacketToggleHover::handle)
                .add();

        CHANNEL_INSTANCE.messageBuilder(PacketToggleEHover.class, nextID())
                .encoder(PacketToggleEHover::toBytes)
                .decoder(PacketToggleEHover::new)
                .consumerNetworkThread(PacketToggleEHover::handle)
                .add();

        CHANNEL_INSTANCE.messageBuilder(PacketToggleCharger.class, nextID())
                .encoder(PacketToggleCharger::toBytes)
                .decoder(PacketToggleCharger::new)
                .consumerNetworkThread(PacketToggleCharger::handle)
                .add();

        CHANNEL_INSTANCE.messageBuilder(PacketUpdateInput.class, nextID())
                .encoder(PacketUpdateInput::toBytes)
                .decoder(PacketUpdateInput::fromBytes)
                .consumerNetworkThread(PacketUpdateInput::handle)
                .add();

        CHANNEL_INSTANCE.messageBuilder(PacketUpdateThrottle.class, nextID())
                .encoder(PacketUpdateThrottle::toBytes)
                .decoder(PacketUpdateThrottle::fromBytes)
                .consumerNetworkThread(PacketUpdateThrottle::handle)
                .add();

        CHANNEL_INSTANCE.messageBuilder(PacketJetpackConfigSync.class, nextID())
                .encoder(PacketJetpackConfigSync::toBytes)
                .decoder(PacketJetpackConfigSync::fromBytes)
                .consumerNetworkThread(PacketJetpackConfigSync::handle)
                .add();
    }

    public static void sendToClient(Object packet, ServerPlayer player) {
        CHANNEL_INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object packet) {
        CHANNEL_INSTANCE.sendToServer(packet);
    }
}
