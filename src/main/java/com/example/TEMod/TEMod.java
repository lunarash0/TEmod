package com.example.TEMod;

import com.mojang.logging.LogUtils;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import javax.swing.*;

@Mod(TEMod.MODID)
public class TEMod {
    public static final String MODID = "temod";
    private static final Logger LOGGER = LogUtils.getLogger();

    // Items registry
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> BURGER =
            ITEMS.register("burger", () ->
                    new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(12)
                                    .saturationMod(0.8f)
                                    .meat()
                                    .build()
                            )
                    )
            );

    public static final RegistryObject<Item> TEST_BLOCK_ITEM = ITEMS.register("test_block",
            () -> new BlockItem(ModBlocks.TEST_BLOCK.get(), new Item.Properties()));

    public TEMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register items
        ITEMS.register(modEventBus);

        // Register blocks
        ModBlocks.register(modEventBus);

        // Register Forge event handlers
        MinecraftForge.EVENT_BUS.register(this);

        // Add items to creative tabs
        modEventBus.addListener(this::addCreativeTabs);

        LOGGER.info("TEMod loaded");
    }

    private void addCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        // Put it in a vanilla tab (Ingredients is a nice default)
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(BURGER);
            event.accept(TEST_BLOCK_ITEM);
        }
    }
}
