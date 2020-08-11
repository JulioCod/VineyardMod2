package com.sayju.vineyardmod.init.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class TallVineyardBlock extends BushBlock implements IGrowable {
   public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
   //Define la propiedad AGE //////////////////////////////////////
   public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;
   ////////////////////////////////////////////////////////////////

   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
      DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
      if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP) ||
              facingState.getBlock() == this && facingState.get(HALF) != doubleblockhalf) {
         return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ?
                 Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
      } else {
         return Blocks.AIR.getDefaultState();
      }
   }

   @Nullable
   public BlockState getStateForPlacement(BlockItemUseContext context) {
      BlockPos blockpos = context.getPos();
      return blockpos.getY() < context.getWorld().getDimension().getHeight() - 1 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context) ?
              super.getStateForPlacement(context) : null;
   }

   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
      if (state.get(HALF) != DoubleBlockHalf.UPPER) {
         return super.isValidPosition(state, worldIn, pos);
      } else {
         BlockState blockstate = worldIn.getBlockState(pos.down());
         if (state.getBlock() != this) return super.isValidPosition(state, worldIn, pos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
         return blockstate.getBlock() == this && blockstate.get(HALF) == DoubleBlockHalf.LOWER;
      }
   }


   public TallVineyardBlock(Properties properties) {

      super(properties);
      this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)).with(HALF, DoubleBlockHalf.LOWER)); //Establece la edad 0 y mitad inferir por defecto
   }

   public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
      return false;
   }

   //aquí establece como cambia de edad 	AGE /////////////////////////////////////////////////////////////////
   public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
      super.tick(state, worldIn, pos, random);
      int i = state.get(AGE);
      if (i < 3 && worldIn.getLightSubtracted(pos.up(), 0) >= 9 && state.get(HALF) == DoubleBlockHalf.LOWER &&
              net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0)) {
         worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
         net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
         if (i > 0 && state.get(HALF) == DoubleBlockHalf.LOWER){
            worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(AGE, Integer.valueOf(i + 1)), 2);
         }

      }



   }
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////

   // esto es para añadir la edad al contenedor de los estados de ese tipo de bloque///////////////////////
   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
      builder.add(AGE);
      builder.add(HALF);
   }
   ///////////////////////////////////////////////////////////////////////////////////////////////////////

   /**
    * Whether this IGrowable can grow
    */
   //puede crecer si su edad es menor de 3//////////////////////////////////////////////////////////////
   public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
      return state.get(AGE) < 3;
   }
   //////////////////////////////////////////////////////////////////////////////////////////////////////

   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
      return (state.get(HALF) == DoubleBlockHalf.LOWER);
   }

   //crece////////////////////////////////////////////////////////////////////////////////////////////////
   //cuando la parte de abajo cumple dos años le sale la de arriba//////////////////////////
   public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
      int i = Math.min(3, state.get(AGE) + 1);
      worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i)), 2);
      if (i > 0 && state.get(HALF) == DoubleBlockHalf.LOWER){
         worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(AGE, Integer.valueOf(i)), 2);
      }

   }
   ////////////////////////////////////////////////////////////////////////////////////////////////////////
}