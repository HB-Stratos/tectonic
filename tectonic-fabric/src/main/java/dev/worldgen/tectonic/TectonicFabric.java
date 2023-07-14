package dev.worldgen.tectonic;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TectonicFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		String pack = FabricLoader.getInstance().isModLoaded("terralith") ? "Terratonic" : "Tectonic";
		FabricLoader.getInstance().getModContainer("tectonic").ifPresent((modContainer -> ResourceManagerHelper.registerBuiltinResourcePack(
				new Identifier("tectonic", pack.toLowerCase()),
				modContainer,
				Text.literal(pack),
				ResourcePackActivationType.DEFAULT_ENABLED
		)));
	}
}