package dev.worldgen.tectonic;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.nio.file.Path;
@Mod("tectonic")
public class TectonicForge {
    public TectonicForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::registerDatapack);
    }

    private void registerDatapack(final AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            String pack = ModList.get().isLoaded("terralith") ? "Terratonic" : "Tectonic";
            Path resourcePath = ModList.get().getModFileById("tectonic").getFile().findResource("resourcepacks/"+pack.toLowerCase());
            Pack builtinDataPack = Pack.readMetaAndCreate("builtin/base_"+pack.toLowerCase(), Component.literal(pack), false,
                (path) -> new PathPackResources(path, resourcePath, false), PackType.SERVER_DATA, Pack.Position.TOP, PackSource.BUILT_IN);
            event.addRepositorySource((packConsumer) -> packConsumer.accept(builtinDataPack));
        }
    }
}
