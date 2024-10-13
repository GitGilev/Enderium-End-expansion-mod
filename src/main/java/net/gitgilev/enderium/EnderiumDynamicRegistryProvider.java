package net.gitgilev.enderium;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.gitgilev.enderium.worldgen.EnderiumBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import java.util.concurrent.CompletableFuture;

public class EnderiumDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    protected EnderiumDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void configure(HolderLookup.Provider registries, Entries entries) {
        // worldgen
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
        EnderiumBiomes.populate(entries);


    }

    @Override
    public String getName() {
        return "Enderium";
    }


}
