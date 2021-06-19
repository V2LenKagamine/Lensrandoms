package io.github.v2lenkagamine.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class RayTraceList {
	public static List<EntityRayTraceResult> rayTraceEntityList(World worldIn, Entity projectile, Vector3d startVec, Vector3d endVec, AxisAlignedBB boundingBox, Predicate<Entity> filter) {
        List<EntityRayTraceResult> entityRayTracelist = new ArrayList<>();

        for(Entity entity1 : worldIn.getEntitiesInAABBexcluding(projectile, boundingBox, filter)) {
           AxisAlignedBB axisalignedbb = entity1.getBoundingBox().grow((double)0.3F);
           Optional<Vector3d> optional = axisalignedbb.rayTrace(startVec, endVec);
           if (optional.isPresent()) {
              entityRayTracelist.add(new EntityRayTraceResult(entity1));
           }
        }

        return entityRayTracelist;
     }
}
