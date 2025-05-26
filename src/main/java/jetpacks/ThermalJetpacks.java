package jetpacks;

import jetpacks.config.SimplyJetpacksConfig;
import jetpacks.handlers.ClientJetpackHandler;
import jetpacks.handlers.CommonJetpackHandler;
import jetpacks.handlers.KeybindForgeBusHandler;
import jetpacks.handlers.RegistryHandler;
import jetpacks.integration.CuriosIntegration;
import jetpacks.item.JetpackItem;
import jetpacks.item.PilotGogglesItem;
import jetpacks.item.SJItemGroup;
import jetpacks.network.NetworkHandler;
import jetpacks.ui.HUDHandler;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.CuriosCapability;

import static jetpacks.ThermalJetpacks.MOD_ID;

@Mod(MOD_ID)
public class ThermalJetpacks {
    public static final String MOD_ID = "tjetpacks";
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final Logger LOGGER = LogManager.getLogger();

    public ThermalJetpacks() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        CREATIVE_TAB.register(MOD_ID+".main", SJItemGroup::new);
        CREATIVE_TAB.register(FMLJavaModLoadingContext.get().getModEventBus());

        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            bus.register(new HUDHandler());
        });

        // TODO: fix this.
        if (ModList.get().isLoaded("curios")) {
            MinecraftForge.EVENT_BUS.addGenericListener(ItemStack.class, this::attachCapabilities);
        }

        MinecraftForge.EVENT_BUS.register(this);
//        MinecraftForge.EVENT_BUS.register(new JetpackCraftingEvents());
        MinecraftForge.EVENT_BUS.register(new CommonJetpackHandler());
//        MinecraftForge.EVENT_BUS.register(new SJSounds());

        SimplyJetpacksConfig.register();
        RegistryHandler.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common Setup Method registered.");
        NetworkHandler.registerMessages();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Client Setup Method registered.");

        MinecraftForge.EVENT_BUS.register(new KeybindForgeBusHandler());
        MinecraftForge.EVENT_BUS.register(new ClientJetpackHandler());
        MinecraftForge.EVENT_BUS.register(new HUDHandler());

        if (ModList.get().isLoaded("curios")) {
            CuriosIntegration.initRenderers();
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server starting...");
    }

    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        LOGGER.info("Server stopping...");
        CommonJetpackHandler.clear();
    }

    @SubscribeEvent
    public void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent loggedInEvent) {
        ThermalJetpacks.LOGGER.info("{} logging in. Syncing server jetpack configs with client.", loggedInEvent.getEntity().getName().getString());
        SimplyJetpacksConfig.sendServerConfigFiles(loggedInEvent.getEntity());
        ThermalJetpacks.LOGGER.info("Finished syncing server jetpack configs.");
    }

    private void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (!ModList.get().isLoaded("curios")) {
            return;
        }
        ItemStack stack = event.getObject();
        if (stack.getItem() instanceof JetpackItem) {
            event.addCapability(CuriosCapability.ID_ITEM, CuriosIntegration.initJetpackCapabilities(stack));
        }
        if (stack.getItem() instanceof PilotGogglesItem) {
            event.addCapability(CuriosCapability.ID_ITEM, CuriosIntegration.initGogglesCapabilities(stack));
        }
    }
}
