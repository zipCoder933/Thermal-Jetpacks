package jetpacks.handlers;

import jetpacks.RegistryHandler;
import jetpacks.ThermalJetpacks;
import jetpacks.config.ModConfig;
import jetpacks.item.JetpackItem;
import jetpacks.item.JetpackType;
import jetpacks.particle.JetpackParticleType;
import jetpacks.util.JetpackUtil;
import jetpacks.util.Pos3D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class ClientJetpackHandler {


    @SubscribeEvent
    public void onClientPlayerQuit(final ClientPlayerNetworkEvent.LoggingOut loggedOutEvent) {
        ThermalJetpacks.LOGGER.info("Reverting jetpack settings to client config.");
        JetpackType.loadAllConfigs();
        ThermalJetpacks.LOGGER.info("Client jetpack config successfully reverted.");
    }

    public static JetpackItem jetpackItem;
    public static ItemStack jetpackItemStack;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && jetpackItemStack != null) {

            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player != null
                    && minecraft.level != null
                    && !minecraft.player.isSpectator()
                    && !minecraft.player.getAbilities().flying) {

                //Make sure the jetpack is still here!
                jetpackItemStack = JetpackUtil.getFromChest(minecraft.player);
                if (jetpackItemStack == null) return;

                if (isJetpackFlying(minecraft.player, jetpackItemStack, jetpackItem)) {
                    //Make particles
                    if (ModConfig.enableJetpackParticles.get()
                            && (minecraft.options.particles().get() != ParticleStatus.MINIMAL)) {
                        makeParticles(minecraft, JetpackParticleType.CLOUD);
                    }

                    // Play sounds:
                    if (ModConfig.enableJetpackSounds.get() && !JetpackSoundEvent.playing(minecraft.player.getId())) {
                        minecraft.getSoundManager().play(new JetpackSoundEvent(minecraft.player, RegistryHandler.SOUND_JETPACK_OTHER.get()));
                    }
                }
            }
        }
    }

    private boolean isJetpackFlying(LocalPlayer player, ItemStack stack, JetpackItem jetpack) {
        if (jetpack.isEngineOn(stack) && (jetpack.getEnergy(stack) > 0 || jetpack.isCreative)) {
            if (jetpack.isHoverOn(stack)) {
                return !player.onGround();
            } else {
                return CommonJetpackHandler.isHoldingUp(player);
            }
        }
        return false;
    }

    private final Random rand = new Random();

    private void makeParticles(Minecraft minecraft, JetpackParticleType particleType) {
        ParticleOptions particle = particleType.getParticleData();

        float random = (rand.nextFloat() - 0.5F) * 0.1F;
        double[] sneakBonus = minecraft.player.isCrouching() ? new double[]{-0.30, -0.10} : new double[]{0, 0};
        Pos3D playerPos = new Pos3D(minecraft.player).translate(0, 1.5, 0);
        Pos3D vLeft = new Pos3D(-0.18, -0.90 + sneakBonus[1], -0.30 + sneakBonus[0]).rotate(minecraft.player.yBodyRot, 0);
        Pos3D vRight = new Pos3D(0.18, -0.90 + sneakBonus[1], -0.30 + sneakBonus[0]).rotate(minecraft.player.yBodyRot, 0);

        Pos3D v = playerPos.translate(vLeft).translate(new Pos3D(minecraft.player.getDeltaMovement()));
        minecraft.particleEngine.createParticle(particle, v.x, v.y, v.z, random, -0.2D, random);
        v = playerPos.translate(vRight).translate(new Pos3D(minecraft.player.getDeltaMovement()));
        minecraft.particleEngine.createParticle(particle, v.x, v.y, v.z, random, -0.2D, random);
    }

}
