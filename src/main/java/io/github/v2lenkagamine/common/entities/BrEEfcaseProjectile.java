package io.github.v2lenkagamine.common.entities;

import io.github.v2lenkagamine.core.init.Entitytype;
import io.github.v2lenkagamine.core.items.LensItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class BrEEfcaseProjectile extends ThrowableItemProjectile{

	public BrEEfcaseProjectile(EntityType<? extends BrEEfcaseProjectile> type, Level worldIn) {
        super(type, worldIn);
	}

	public BrEEfcaseProjectile(Level worldIn, LivingEntity ent) {
		super(Entitytype.BREEFCASE_PROJECTILE.get(),ent, worldIn);
	}
	
	public BrEEfcaseProjectile(Level p_37394_, double p_37395_, double p_37396_, double p_37397_) {
		super(Entitytype.BREEFCASE_PROJECTILE.get(), p_37395_, p_37396_, p_37397_, p_37394_);
	}

	@Override
	protected Item getDefaultItem() {
		return LensItems.BREEFCASE.get();
	}
	@Override	
	protected void onHit(HitResult result) {
		super.onHit(result);
		if (!this.level.isClientSide) {
			for (int i=0;i<100;i++) {
				Bee bee = EntityType.BEE.create(this.level);
				bee.setPos(this.getX(),this.getY(),this.getZ());
				bee.setAggressive(true);
				bee.setTarget(this.level.getNearestPlayer(this, 15));
				this.level.addFreshEntity(bee);
			}
		}
		this.discard();
	}
	   
	
	//This is NEEDED.
	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}