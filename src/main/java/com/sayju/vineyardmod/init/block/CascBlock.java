package com.sayju.vineyardmod.init.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;


public class CascBlock extends Block {
    public static final DirectionProperty PROPERTY_FACING = BlockStateProperties.FACING;

    public CascBlock(Block.Properties cascProperties) {
        super(cascProperties);
        this.setDefaultState(this.stateContainer.getBaseState().with(PROPERTY_FACING, Direction.NORTH));

    }


    // Obtiene la dirección en que está colocado el bloque
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(PROPERTY_FACING, rot.rotate(state.get(PROPERTY_FACING)));
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
            return this.getDefaultState().with(PROPERTY_FACING, context.getNearestLookingDirection().getOpposite());
    }

    //Rellena el StateContainer con la posicion
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(PROPERTY_FACING);
    }
}

