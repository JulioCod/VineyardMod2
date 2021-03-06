package com.sayju.vineyardmod.init;

import com.sayju.vineyardmod.VineyardMod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = VineyardMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(VineyardMod.MODID)
public class ModItems {
    public static final Item example_item = null;
    public static final Item example_otom = null;
    public static final BlockItem cask_block = null;
    public static final BlockItem grapes = null;
    public static final BlockItem grapevine = null;


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        assert false;
        event.getRegistry().registerAll(
                new Item(new Item.Properties().maxStackSize(32).group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName(VineyardMod.MODID, "grapes"),
                new BlockItem(ModBlocks.tall_grapevine, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(ModBlocks.tall_grapevine.getRegistryName()),
                new BlockItem(ModBlocks.cask_block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(ModBlocks.cask_block.getRegistryName())

        );
    }



}

