package com.github.apehum.bundlecounter.mixin;

import com.github.apehum.bundlecounter.BundleCounter;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {
    @Redirect(method = "renderItemCount", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getCount()I"))
    private int getCount(ItemStack instance) {
        if (!BundleCounter.CONFIG.shouldRenderOnItem) {
            return instance.getCount();
        }

        if (instance.getCount() != 1) {
            return instance.getCount();
        }

        BundleContents contents = instance.get(DataComponents.BUNDLE_CONTENTS);
        if (contents == null) {
            return instance.getCount();
        }

        var weight = contents.weight();
        var stackValue = (int) (weight.floatValue() * 64);

        return Math.max(1, stackValue);
    }
}
