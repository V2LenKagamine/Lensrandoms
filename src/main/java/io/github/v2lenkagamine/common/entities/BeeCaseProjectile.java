package io.github.v2lenkagamine.common.entities;

import io.github.v2lenkagamine.core.init.Entitytype;
import io.github.v2lenkagamine.core.items.Items;
import io.github.v2lenkagamine.core.util.RandomBetween;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class BeeCaseProjectile extends ThrowableItemProjectile{
	
	public BeeCaseProjectile(EntityType<? extends BeeCaseProjectile> type, Level worldIn) {
        super(type, worldIn);
	}

	public BeeCaseProjectile(Level worldIn, LivingEntity ent) {
		super(Entitytype.BEECASE_PROJECTILE.get(),ent, worldIn);
	}
	
	public BeeCaseProjectile(Level p_37394_, double p_37395_, double p_37396_, double p_37397_) {
		super(Entitytype.BEECASE_PROJECTILE.get(), p_37395_, p_37396_, p_37397_, p_37394_);
	}

	@Override
	protected Item getDefaultItem() {
		return Items.BEECASE.get();
	}
	@Override	
	protected void onHit(HitResult result) {
		super.onHit(result);
		int beeNum = RandomBetween.randBetween(2,5);
		if (!this.level.isClientSide) {
			for (int i=0;i<beeNum;i++) {
				Bee bee = EntityType.BEE.create(this.level);
				bee.setPos(this.getX(),this.getY(),this.getZ());
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
