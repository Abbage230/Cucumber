package com.blakebr0.cucumber.client.helper;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.item.ItemStack;

// TODO: 1.16: reevaluate
public final class RenderHelper {
	public static void drawCenteredText(Font font, PoseStack stack, String text, int x, int y, int color) {
		font.draw(stack, text, (x - font.width(text) / 2F), y, color);
	}

	/*
	 * Credit to ellpeck for actually additions:
	 * https://github.com/Ellpeck/ActuallyAdditions/blob/6ded1cc7b37e240642847a3addf90f5273844666/src/main/java/de/ellpeck/actuallyadditions/mod/util/StringUtil.java
	 */
	public static void drawScaledWrappedText(Font font, PoseStack stack, String s, int x, int y, float scale, float width, float height, int color, boolean shadow) {
//		stack.push();
//		stack.scale(scale, scale, scale);
//		List<String> lines = font.listFormattedStringToWidth(s, (int) (width / scale));
//		for (int i = 0; i < lines.size() && (!(height > -1) || i * font.FONT_HEIGHT < height); i++) {
//			if (shadow) {
//				font.drawStringWithShadow(lines.get(i), x / scale, y / scale + (i * (int) (font.FONT_HEIGHT * scale)), color);
//			} else {
//				font.drawString(lines.get(i), x / scale, y / scale + (i * (int) (font.FONT_HEIGHT * scale)), color);
//			}
//		}
//
//		stack.pop();
	}

	public static void drawScaledWrappedText(Font font, PoseStack stack, String s, int x, int y, float scale, float width, int color, boolean shadow) {
		drawScaledWrappedText(font, stack, s, x, y, scale, width, -1, color, shadow);
	}

	public static void drawScaledCenteredWrappedTextX(Font font, PoseStack stack, FormattedText text, int x, int y, float scale, float width, float height, int color, boolean shadow) {
//		stack.push();
//		stack.scale(scale, scale, scale);
//		List<ITextProperties> lines = font.func_238425_b_(text, (int) (width / scale));
//		for (int i = 0; i < lines.size() && (!(height > -1) || i * font.FONT_HEIGHT < height); i++) {
//			if (shadow) {
//				font.drawStringWithShadow(lines.get(i), (x - font.getStringWidth(lines.get(i).getString()) / 2) / scale, y / scale + (i * (int) (font.FONT_HEIGHT * scale)), color);
//			} else {
//				font.drawString(lines.get(i), (x - font.getStringWidth(lines.get(i).getString()) / 2) / scale, y / scale + (i * (int) (font.FONT_HEIGHT * scale)), color);
//			}
//		}
//
//		stack.pop();
	}

	public static void drawScaledCenteredWrappedTextY(Font font, String s, int x, int y, float scale, float width, float height, int color, boolean shadow) {
//		List<String> lines = font.listFormattedStringToWidth(s, (int) (width / scale));
//		y -= (font.FONT_HEIGHT / 2 / scale) * (lines.size() - 1);
//		drawScaledWrappedText(font, s, x, y, scale, width, height, color, shadow);
	}

	public static void drawScaledCenteredWrappedTextXY(Font font, String s, int x, int y, float scale, float width, float height, int color, boolean shadow) {
//		List<String> lines = font.listFormattedStringToWidth(s, (int) (width / scale));
//		y -= (font.FONT_HEIGHT / 2 / scale) * (lines.size() - 1);
//		drawScaledCenteredWrappedTextX(font, s, x, y, scale, width, height, color, shadow);
	}

	public static void drawScaledItemIntoGui(ItemRenderer render, ItemStack stack, int x, int y, float scale) {
//		RenderSystem.pushMatrix();
//		RenderSystem.scalef(scale, scale, scale);
//		Lighting.setupForFlatItems();
//		render.renderAndDecorateItem(stack, (int) (x / scale), (int) (y / scale));
//		RenderSystem.popMatrix();
	}
}
