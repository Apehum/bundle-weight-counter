package com.github.apehum.bundlecounter.mixin;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientBundleTooltip;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.BundleContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientBundleTooltip.class)
public abstract class ClientBundleTooltipMixin implements ClientTooltipComponent {

    @Shadow public abstract int getWidth(Font font);

    @Shadow @Final private BundleContents contents;

    @Inject(method = "getProgressBarFillText", at = @At("RETURN"), cancellable = true)
    private void getProgressBarFillText(CallbackInfoReturnable<Component> cir) {
        var text = cir.getReturnValue();
        if (text != null) return;

        var weight = contents.weight();
        var stackValue = (int) (weight.floatValue() * 64);
        var newText = Component.literal(stackValue + "/64");

        cir.setReturnValue(newText);
    }
}
