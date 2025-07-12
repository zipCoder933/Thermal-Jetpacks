package jetpacks.network.packets;

import jetpacks.ui.HUDHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketToggleHUD {
    final boolean enabled;

    public PacketToggleHUD(FriendlyByteBuf buf) {
        this.enabled = buf.readBoolean();
    }

    public PacketToggleHUD(boolean enabled) {
        this.enabled = enabled;
    }


    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(enabled);
    }

    public static PacketToggleHUD fromBytes(FriendlyByteBuf buffer) {
        return new PacketToggleHUD(buffer);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            System.out.println("Toggling HUD: " + enabled);
            HUDHandler.renderJetpackHUD = enabled;
        });
        ctx.get().setPacketHandled(true);
    }
}
