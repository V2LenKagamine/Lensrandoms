package io.github.v2lenkagamine.common.tileentity;

import io.github.v2lenkagamine.core.init.TileEntityTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class PowerHoleTE extends TileEntity{

	public PowerHoleTE(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	public PowerHoleTE() {
		this(TileEntityTypes.POWER_HOLE_TE.get());
	}
}
