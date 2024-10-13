package net.gitgilev.enderium.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gitgilev.enderium.Enderium;
import net.gitgilev.enderium.block.RaincubeBlock;
import net.gitgilev.enderium.blockentity.RaincubeBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class EnderiumBlocks {
    public static final Block RAINCUBE = registerBlock("raincube",
            new RaincubeBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.25F, 4.2F).sound(SoundType.DEEPSLATE)));
    public static final BlockEntityType<RaincubeBlockEntity> RAINCUBE_S = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Enderium.path("raincube"), BlockEntityType.Builder.of(RaincubeBlockEntity::new, RAINCUBE).build(null));
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.tryBuild(Enderium.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(Enderium.MOD_ID, name),
                new BlockItem(block, new Item.Properties()));
    }
    public static void registerModBlocks() {
        Enderium.LOGGER.info("Registering Mod Blocks for " + Enderium.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            entries.accept(EnderiumBlocks.RAINCUBE);
        });

}
}
