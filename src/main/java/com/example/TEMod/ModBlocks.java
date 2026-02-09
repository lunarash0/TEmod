package com.example.TEMod;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TEMod.MODID);

    public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(2.0f)));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
