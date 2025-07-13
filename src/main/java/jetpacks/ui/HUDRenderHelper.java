package jetpacks.ui;

import com.mojang.blaze3d.platform.Window;
import jetpacks.ThermalJetpacks;
import jetpacks.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import jetpacks.config.ConfigDefaults;

public class HUDRenderHelper {

    private static final Font font = Minecraft.getInstance().font;

    public static void drawStringAtPosition(GuiGraphics graphics, Window window, Component text, int lineOffset) {
        int windowScaleHeight = window.getGuiScaledHeight();
        int windowScaleWidth = window.getGuiScaledWidth();

        ConfigDefaults.HUDPosition position = ModConfig.hudTextPosition.get();
        int color = ModConfig.hudTextColor.get();
        int xOffset = ModConfig.hudXOffset.get();
        int yOffset = ModConfig.hudYOffset.get();
        long hudScale = ModConfig.hudScale.get();
        boolean hudTextShadow = ModConfig.hudTextShadow.get();

        int screenHeight = (int) (windowScaleHeight / hudScale);
        int screenWidth = (int) (windowScaleWidth / hudScale);

        switch (position) {
            case TOP_LEFT -> {
                yOffset += lineOffset * 9;
                drawStringLeft(graphics, text, 2 + xOffset, 2 + yOffset, color, hudTextShadow);
            }
            case TOP_CENTER -> {
                yOffset += lineOffset * 9;
                drawStringCenter(graphics, text, screenWidth / 2 + xOffset, 2 + yOffset, color, hudTextShadow);
            }
            case TOP_RIGHT -> {
                yOffset += lineOffset * 9;
                drawStringRight(graphics, text, screenWidth - 2 + xOffset, 2 + yOffset, color, hudTextShadow);
            }
            case LEFT -> {
                yOffset += lineOffset * 9;
                drawStringLeft(graphics, text, 2 + xOffset, screenHeight / 2 + yOffset, color, hudTextShadow);
            }
            case RIGHT -> {
                yOffset += lineOffset * 9;
                drawStringRight(graphics, text, screenWidth - 2 + xOffset, screenHeight / 2 + yOffset, color, hudTextShadow);
            }
            case BOTTOM_LEFT -> {
                yOffset -= lineOffset * 9;
                drawStringLeft(graphics, text, 2 + xOffset, screenHeight - 9 + yOffset, color, hudTextShadow);
            }
            case BOTTOM_RIGHT -> {
                yOffset -= lineOffset * 9;
                drawStringRight(graphics, text, screenWidth - 2 + xOffset, screenHeight - 9 + yOffset, color, hudTextShadow);
            }
            default -> ThermalJetpacks.LOGGER.info("Invalid HUD Position passed to renderer.");
        }
    }

    private static void drawStringLeft(GuiGraphics graphics, Component text, int x, int y, int color, boolean shadow) {
        graphics.drawString(font, text, x, y, color, shadow);
    }

    private static void drawStringCenter(GuiGraphics graphics, Component text, int x, int y, int color, boolean shadow) {
        float textWidth = font.width(text);
        graphics.drawString(font, text, (int) (x - (textWidth / 2)), y, color, shadow);
    }

    private static void drawStringRight(GuiGraphics graphics, Component text, int x, int y, int color, boolean shadow) {
        float textWidth = font.width(text);
        graphics.drawString(font, text, (int) (x - textWidth), y, color, shadow);
    }
}
