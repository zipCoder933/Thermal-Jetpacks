package jetpacks.network.packets;

import jetpacks.item.JetpackItem;
import jetpacks.network.NetworkHandler;
import jetpacks.util.JetpackUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSetEHover {

    final ToggleStatus status;


    public PacketSetEHover(FriendlyByteBuf buf) {
        status = buf.readEnum(ToggleStatus.class);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(status);
    }

    public PacketSetEHover(ToggleStatus status) {
        this.status = status;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = JetpackUtil.getFromChestAndCurios(player);
                Item item = stack.getItem();
                if (item instanceof JetpackItem jetpack) {
                    if (status == ToggleStatus.TOGGLE) jetpack.setEHoverOn(stack, player, !jetpack.isEHoverOn(stack));
                    else jetpack.setEHoverOn(stack, player, status == ToggleStatus.ON);
                    //Send the status back to the client
                    NetworkHandler.sendToClient(new PacketUpdateClientJetpackUI(jetpack, stack), player);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}