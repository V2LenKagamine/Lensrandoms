package io.github.v2lenkagamine.core.util;

import java.util.List;
import java.util.function.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector3d;

public class WeaponFire {

	public static void fireNormal (PlayerEntity playerIn,int range,int damage) {
		double distance = Math.pow(range, 2);
		
		RayTraceResult result = playerIn.pick(distance, 1.0F, false);
		Vector3d look = playerIn.getLookVec();
		Vector3d start = new Vector3d(playerIn.getPosX(),playerIn.getPosY() + playerIn.getEyeHeight(),playerIn.getPosZ());
		Vector3d end = new Vector3d(playerIn.getPosX() + look.x * (double) distance, playerIn.getPosY() + playerIn.getEyeHeight() + look.y * (double) distance, playerIn.getPosZ() + look.z * (double) distance);
		if (result.getType() != Type.MISS ) {
			end = result.getHitVec();
			}
		Predicate<Entity> pred = entity -> entity instanceof LivingEntity && !entity.isSpectator();
		end = result.getHitVec();
		AxisAlignedBB box = playerIn.getBoundingBox();
		EntityRayTraceResult hitresult = ProjectileHelper.rayTraceEntities(playerIn, start, end, box.expand(look.scale(distance)), pred, distance);
		
		if (hitresult != null) 
			hitresult.getEntity().attackEntityFrom(DamageSource.causePlayerDamage(playerIn) , damage);
		
		
	}
	
	public static void fireShotgun (PlayerEntity playerIn,int range,int damage) {
		double distance = Math.pow(range, 2);
		
		RayTraceResult result = playerIn.pick(distance, 1.0F, false);
		Vector3d look = playerIn.getLookVec();
		Vector3d start = new Vector3d(playerIn.getPosX(),playerIn.getPosY() + playerIn.getEyeHeight(),playerIn.getPosZ());
		Vector3d end = new Vector3d(playerIn.getPosX() + look.x * (double) distance, playerIn.getPosY() + playerIn.getEyeHeight() + look.y * (double) distance, playerIn.getPosZ() + look.z * (double) distance);
		if (result.getType() != Type.MISS ) {
			end = result.getHitVec();
			}
		Predicate<Entity> pred = entity -> entity instanceof LivingEntity && !entity.isSpectator();
		end = result.getHitVec();
		AxisAlignedBB box = playerIn.getBoundingBox();
		List<EntityRayTraceResult> hitlist = RayTraceList.rayTraceEntityList(playerIn.world, playerIn, start, end, box.expand(look.scale(distance)), pred);
		for (EntityRayTraceResult hitresult : hitlist) {
		if (hitresult != null) 
			hitresult.getEntity().attackEntityFrom(DamageSource.causePlayerDamage(playerIn) , damage);
		}
		
	}
}
