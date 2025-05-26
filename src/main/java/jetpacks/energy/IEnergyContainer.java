package jetpacks.energy;

import net.minecraft.world.item.ItemStack;

public interface IEnergyContainer {

    int receiveEnergy(ItemStack container, int maxReceive, boolean simulate);

    int extractEnergy(ItemStack container, int maxExtract, boolean simulate);

    int getEnergy(ItemStack container);

    int getCapacity(ItemStack container);
}
