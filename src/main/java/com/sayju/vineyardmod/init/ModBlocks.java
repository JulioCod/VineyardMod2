package com.sayju.vineyardmod.init;

import com.sayju.vineyardmod.VineyardMod;
import com.sayju.vineyardmod.init.block.CaskBlock;
import com.sayju.vineyardmod.init.block.TallVineyardBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = VineyardMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(VineyardMod.MODID)
public class ModBlocks {
    public static final Block cask_block = null;
    public static final Block tall_grapevine = null;


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                new TallVineyardBlock(Block.Properties.create(Material.TALL_PLANTS)
                        .tickRandomly()
                        .doesNotBlockMovement()
                        .hardnessAndResistance(0.0F, 0.0F))
                        .setRegistryName(VineyardMod.MODID, "tall_grapevine"),
                new CaskBlock(Block.Properties.create(Material.WOOD)
                        .hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE))
                        .setRegistryName(VineyardMod.MODID, "cask_block")

        );
    }
}