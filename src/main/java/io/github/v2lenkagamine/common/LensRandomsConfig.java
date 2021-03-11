package io.github.v2lenkagamine.common;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;

public class LensRandomsConfig {
	public static class Common{ 
		public final DoubleValue damageMulti;
		Common(ForgeConfigSpec.Builder builder) {
			builder.push("general");
			builder.comment("These settings need not be the same on server and client, but are only loaded on world load.");
			
			damageMulti = builder
					.comment("Think this mod is too weak? Crank up the damage! default:1 Range: 0.5 to 100 Type: Double")
					.defineInRange("damageMulti", 1.0, 0.5, 100.0);
			
			builder.pop();
		}
		
	}
    public static final ForgeConfigSpec commonSpec;
    public static final Common COMMON;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
