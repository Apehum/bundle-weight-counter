package com.github.apehum.bundlecounter.config;

import com.github.apehum.bundlecounter.BundleCounter;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreen {
	public static Screen createConfigScreen(Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
			.setParentScreen(parent)
			.setTitle(Component.literal("Bundle Counter"));

		builder.setSavingRunnable(BundleCounter.CONFIG::writeToFile);

		ConfigCategory generalCategory = builder.getOrCreateCategory(Component.literal("General"));
		ConfigEntryBuilder renderOnItemOption = builder.entryBuilder();

		generalCategory.addEntry(renderOnItemOption
			.startBooleanToggle(Component.literal("Render on item"), BundleCounter.CONFIG.shouldRenderOnItem)
			.setDefaultValue(true)
			.setTooltip(Component.literal("Whether or not the weight of the bundle should render directly on the item sprite"))
			.setSaveConsumer(newVal -> BundleCounter.CONFIG.shouldRenderOnItem = newVal)
			.build()
		);

		return builder.build();
	}
}
