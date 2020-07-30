package com.sayju.vineyardmod.init;

import com.sayju.vineyardmod.VineyardMod;
import com.sayju.vineyardmod.init.block.CaskBlock;
import com.sayju.vineyardmod.init.block.GrapevineBlock;
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
    public static final Block tutorial_block = null;
    public static final Block casc_block = null;
    public static final Block cask_block = null;
    public static final Block grapevine = null;


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                new GrapevineBlock().setRegistryName(VineyardMod.MODID, "grapevine"),
                new Block(Block.Properties.create(Material.ROCK)
                        .hardnessAndResistance(3.0F, 3.0F))
                        .setRegistryName(VineyardMod.MODID, "example_ore"),
                new Block(Block.Properties.create(Material.IRON)
                        .hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE))
                        .setRegistryName(VineyardMod.MODID, "tutorial_block"),
                new CaskBlock(Block.Properties.create(Material.WOOD)
                        .hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE))
                        .setRegistryName(VineyardMod.MODID, "cask_block")

        );
    }
}