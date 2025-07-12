package jetpacks.ui;

import com.mojang.blaze3d.systems.RenderSystem;
import jetpacks.handlers.KeybindHandler;
import jetpacks.item.JetpackItem;
import jetpacks.network.NetworkHandler;
import jetpacks.network.packets.*;
import jetpacks.util.JetpackUtil;
import jetpacks.util.SJTextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.widget.ForgeSlider;

import static jetpacks.ThermalJetpacks.MOD_ID;

@OnlyIn(Dist.CLIENT)
public class JetpackScreen extends Screen {

    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final float LEAN_FACTOR = 2.0f;

    private final ResourceLocation JETPACK_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/jetpack_screen.png");
    private static final int WIDTH = 176;
    private static final int HEIGHT = 122;

    private final JetpackItem jetpackItem;
    private final ItemStack jetpackStack;

    private static ToggleImageButton engineButton, hoverButton, ehoverButton, chargerButton;
    private ForgeSlider slider;

    public JetpackScreen() {
        // TODO: test this
        super(Component.translatable("screen." + MOD_ID + ".jetpack_screen.title"));
        this.width = WIDTH;
        this.height = HEIGHT;
        this.jetpackItem = (JetpackItem) JetpackUtil.getFromChestAndCurios(minecraft.player).getItem();
        this.jetpackStack = JetpackUtil.getFromChestAndCurios(minecraft.player);
    }

    public static void update(boolean engineOn, boolean hoverOn, boolean eHoverOn, boolean chargerOn) {
        if (engineButton != null) {
            engineButton.setToggled(engineOn);
        }
        if (hoverButton != null) {
            hoverButton.setToggled(hoverOn);
        }
        if (ehoverButton != null) {
            ehoverButton.setToggled(eHoverOn);
        }
        if (chargerButton != null) {
            chargerButton.setToggled(chargerOn);
        }
    }


    @Override
    protected void init() {
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        Item item = jetpackStack.getItem();
        Player player = minecraft.player;
        assert player != null;

        NetworkHandler.sendToServer(new PacketUpdateClientJetpackUI());
        int buttonsYOffset = HEIGHT / 2 - (42 / 2);

        if (item instanceof JetpackItem jetpack) {
            engineButton = new ToggleImageButton(relX + 120, relY + buttonsYOffset, 20, 20, 176,
                    40, 20, 0, JETPACK_TEXTURE,
                    button -> {
//                        boolean on = !jetpack.isEngineOn(jetpackStack);
                        NetworkHandler.sendToServer(new PacketSetEngine(ToggleStatus.TOGGLE));
                    });
            engineButton.setToggled(false);
            engineButton.setTooltip(Tooltip.create(Component.translatable("tooltip." + MOD_ID + ".engine_on")));
            addRenderableWidget(engineButton);

            hoverButton = new ToggleImageButton(relX + 120, relY + 22 + buttonsYOffset, 20, 20, 216,
                    40, 20, 0, JETPACK_TEXTURE,
                    button -> {
//                        boolean on = !jetpack.isEngineOn(jetpackStack);
                        NetworkHandler.sendToServer(new PacketSetHover(ToggleStatus.TOGGLE));
                    });
            hoverButton.setToggled(false);
            hoverButton.setTooltip(Tooltip.create(Component.translatable("tooltip." + MOD_ID + ".hover_on")));
            hoverButton.active = jetpack.getJetpackType().getHoverMode();
            addRenderableWidget(hoverButton);


            chargerButton = new ToggleImageButton(relX + 142, relY + buttonsYOffset, 20, 20, 196,
                    40, 20, 0, JETPACK_TEXTURE,
                    button -> {
//                        boolean on = !jetpack.isChargerOn(jetpackStack);
                        NetworkHandler.sendToServer(new PacketSetCharger(ToggleStatus.TOGGLE));
                    });
            chargerButton.setToggled(false);
            chargerButton.setTooltip(Tooltip.create(Component.translatable("tooltip." + MOD_ID + ".charger_on")));
            chargerButton.active = jetpack.getJetpackType().getChargerMode();
            addRenderableWidget(chargerButton);


            ehoverButton = new ToggleImageButton(relX + 142, relY + 22 + buttonsYOffset, 20, 20, 236,
                    40, 20, 0, JETPACK_TEXTURE,
                    button -> {
//                        boolean on = !jetpack.isEHoverOn(jetpackStack);
                        NetworkHandler.sendToServer(new PacketSetEHover(ToggleStatus.TOGGLE));
                    });
            ehoverButton.setToggled(false);
            ehoverButton.setTooltip(Tooltip.create(Component.translatable("tooltip." + MOD_ID + ".ehover_on")));
            ehoverButton.active = jetpack.getJetpackType().getEmergencyHoverMode();
            addRenderableWidget(ehoverButton);
        }

//        update(jetpackItem, jetpackStack);


        addRenderableWidget(slider = new ForgeSlider(relX + 10, relY + 98, 152, 16,
                Component.translatable("screen." + MOD_ID + ".throttle"),
                Component.literal("%"), 0, 100,
                jetpackItem.getThrottle(jetpackStack), true));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        RenderSystem.setShaderTexture(0, JETPACK_TEXTURE);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        graphics.blit(JETPACK_TEXTURE, relX, relY, 0, 0, WIDTH, HEIGHT);

        assert minecraft.screen != null;
        float halfScreenHeight = minecraft.screen.height / 2f;
        float halfScreenWidth = minecraft.screen.width / 2f;
        float angleX = -((mouseX - halfScreenWidth) / halfScreenWidth * LEAN_FACTOR);
        float angleY = -(((mouseY + 25) - halfScreenHeight) / halfScreenHeight * LEAN_FACTOR);

        InventoryScreen.renderEntityInInventoryFollowsAngle(graphics, relX + 80, relY + 90, 40, angleX, angleY, minecraft.player);
        graphics.drawCenteredString(minecraft.font, Component.translatable(jetpackStack.getDescriptionId()), relX + 88, relY + 5, 0xFFFFFF);
        RenderSystem.setShaderTexture(0, JETPACK_TEXTURE);

        NetworkHandler.sendToServer(new PacketUpdateThrottle(slider.getValueInt()));

        int amount = getEnergyBarAmount(); // Texture height
        int barOffset = 78 - amount;
        int barX = 0;
        boolean useGradient = false;

        if (jetpackItem.isCreative) {
            graphics.blit(JETPACK_TEXTURE, relX + 10, relY + 16, 70, 178, 14, 78);
        } else {
            graphics.blit(JETPACK_TEXTURE, relX + 10, relY + 16, barX, 178, 14, 78);
            if (useGradient) {
                // Top left corner -> bottom right corner
                graphics.fillGradient(relX + 12, relY + 18 + barOffset, relX + 22, relY + 14 + 78, 0xffb51500, 0xff600b00);
            } else {
                //matrixStack, xPos, yPos, textureXPos, textureYPos, textureWidth, textureHeight
                //blit(matrixStack, relX + 10, relY + 14 + barOffset - 1, barX + 14, 178 + 1, 14, amount - 1);
                graphics.blit(JETPACK_TEXTURE, relX + 10, relY + 16 + 1 + barOffset, barX + 14, 178 + 1, 14, amount - 2);
            }
        }
        // This does not update like a screen container :(
        if (mouseX >= relX + 10 && mouseY >= relY + 16 && mouseX < relX + 10 + 14 && mouseY < relY + 16 + 78) {
            Component text;
            //text = SJTextUtil.energyWithMax(jetpackItem.getEnergy(jetpackStack), jetpackItem.getCapacity(jetpackStack));
            if (jetpackItem.isCreative) {
                text = SJTextUtil.translate("tooltip", "infiniteEnergy", ChatFormatting.LIGHT_PURPLE);
            } else if (jetpackItem.getEnergy(jetpackStack) == 0) {
                text = SJTextUtil.translate("hud", "energyDepleted", ChatFormatting.RED);
            } else text = null;
            if (text != null) {
                graphics.renderTooltip(font, text, mouseX, mouseY);
            }
        }
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    private int getEnergyBarAmount() {
        Item item = jetpackStack.getItem();
        if (item instanceof JetpackItem) {
            JetpackItem jetpack = (JetpackItem) item;
            if (jetpack.isCreative) {
                return 78;
            }
            int i = jetpack.getEnergy(jetpackStack);
            int j = jetpack.getCapacity(jetpackStack);
            return (int) (j != 0 && i != 0 ? (long) i * 78 / j : 0);
        }
        return 0;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (KeybindHandler.JETPACK_GUI_KEY.matches(keyCode, scanCode) || minecraft.options.keyInventory.matches(keyCode, scanCode)) {
            minecraft.setScreen(null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}