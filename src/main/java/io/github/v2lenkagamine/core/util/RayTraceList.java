package io.github.v2lenkagamine.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

public class RayTraceList {
	public static List<EntityHitResult> rayTraceEntityList(Level worldIn, Entity projectile, Vec3 startVec, Vec3 endVec, AABB boundingBox, Predicate<Entity> filter) {
        List<EntityHitResult> entityRayTracelist = new ArrayList<>();

        for(Entity entity1 : worldIn.getEntities(projectile, boundingBox, filter)) {
           AABB axisalignedbb = entity1.getBoundingBox().inflate((double)0.3F);
           Optional<Vec3> optional = axisalignedbb.clip(startVec, endVec);
           if (optional.isPresent()) {
              entityRayTracelist.add(new EntityHitResult(entity1));
           }
        }

        return entityRayTracelist;
     }
}
