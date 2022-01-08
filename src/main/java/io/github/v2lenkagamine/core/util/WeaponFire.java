package io.github.v2lenkagamine.core.util;

import java.util.List;
import java.util.function.Predicate;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.HitResult.Type;
import net.minecraft.world.phys.Vec3;

public class WeaponFire {

	public static void fireNormal (Player playerIn,int range,int minDamage,int maxDamage) {
		double distance = Math.pow(range, 2);
		int damage = RandomBetween.randBetween(minDamage, maxDamage);
		
		HitResult result = playerIn.pick(distance, 1.0F, false);
		Vec3 look = playerIn.getLookAngle();
		Vec3 start = new Vec3(playerIn.getX(),playerIn.getY() + playerIn.getEyeHeight(),playerIn.getZ());
		Vec3 end = new Vec3(playerIn.getX() + look.x * (double) distance, playerIn.getY() + playerIn.getEyeHeight() + look.y * (double) distance, playerIn.getZ() + look.z * (double) distance);
		if (result.getType() != Type.MISS ) {
			end = result.getLocation();
			}
		Predicate<Entity> pred = entity -> entity instanceof LivingEntity && !entity.isSpectator();
		end = result.getLocation();
		AABB box = playerIn.getBoundingBox();
		EntityHitResult hitresult = ProjectileUtil.getEntityHitResult(playerIn, start, end, box.expandTowards(look.scale(distance)), pred, distance);
		
		if (hitresult != null) 
			hitresult.getEntity().hurt(DamageSource.playerAttack(playerIn) , damage);
		
		
	}
	
	public static void firePierceAll (Player playerIn,int range,int minDamage,int maxDamage) {
		double distance = Math.pow(range, 2);
		int damage = RandomBetween.randBetween(minDamage, maxDamage);
		
		HitResult result = playerIn.pick(distance, 1.0F, false);
		Vec3 look = playerIn.getLookAngle();
		Vec3 start = new Vec3(playerIn.getX(),playerIn.getY() + playerIn.getEyeHeight(),playerIn.getZ());
		Vec3 end = new Vec3(playerIn.getX() + look.x * (double) distance, playerIn.getY() + playerIn.getEyeHeight() + look.y * (double) distance, playerIn.getZ() + look.z * (double) distance);
		if (result.getType() != Type.MISS ) {
			end = result.getLocation();
			}
		Predicate<Entity> pred = entity -> entity instanceof LivingEntity && !entity.isSpectator();
		end = result.getLocation();
		AABB box = playerIn.getBoundingBox();
		List<EntityHitResult> hitlist = RayTraceList.rayTraceEntityList(playerIn.level, playerIn, start, end, box.expandTowards(look.scale(distance)), pred);
		for (EntityHitResult hitresult : hitlist) {
		if (hitresult != null) 
			hitresult.getEntity().hurt(DamageSource.playerAttack(playerIn) , damage);
		}
		
	}
}
