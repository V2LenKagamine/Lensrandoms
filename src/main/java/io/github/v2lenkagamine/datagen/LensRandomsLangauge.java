package io.github.v2lenkagamine.datagen;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.init.blocks.Blocks;
import io.github.v2lenkagamine.core.items.Items;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LensRandomsLangauge extends LanguageProvider{

	public LensRandomsLangauge(DataGenerator gen, String locale) {
		super(gen, Lensrandoms.MOD_ID, locale);

	}

	@Override
	protected void addTranslations() {
		
		
		//Blocks
		add(Blocks.RGBLOCK.get(), "RGBlock");
		add(Blocks.RGBLOCK_GLOW.get(),"Glowing RGBlock");
		add(Blocks.RGBLOCK_SLAB.get(),"RGBlock Slab");
		add(Blocks.RGBLOCK_STAIR.get(),"RGBlock Stair");
		add(Blocks.RGBLOCK_BRICK.get(),"RGBlock Bricks");
		add(Blocks.RGBLOCK_BRICK_GLOW.get(),"Glowing RGBlock Bricks");
		add(Blocks.RGBLOCK_BRICK_SLAB.get(),"RGBlock Brick Slab");
		add(Blocks.RGBLOCK_BRICK_STAIR.get(),"RGBlock Brick Stair");
		add(Blocks.RGBLOCK_GLASS.get(), "RGB Glass");
		add(Blocks.RGBLOCK_GLASS_GLOW.get(), "Glowing RGB Glass");
		add(Blocks.RGBLOCK_GLASS_BORDER.get(), "Bordered RGB Glass");
		add(Blocks.RGBLOCK_GLASS_BORDER_GLOW.get(), "Glowing Bordered RGB Glass");
		add(Blocks.CLEARGLASS.get(),"Clear Glass");
		add(Blocks.CLEARGLASS_BORDER.get(),"Bordered Clear Glass");
		add(Blocks.CLEARGLASS_GLOW.get(),"Glowing Clear Glass");
		add(Blocks.CLEARGLASS_BORDER_GLOW.get(),"Glowing Bordered Clear Glass");
		
		//Items
		add(Items.TEST_APPLE.get(),"The Apple of Testing");
		add(Items.RGB_INATOR.get(),"\u00A74R\u00A72G\u00A71B\u00A7r-inator");
		add(Items.TEST_ITEM.get(), "Icon of the Creator");
		add(Items.GUN_BLUNDERBUS.get(), "Blunderbus");
		add(Items.SIMPLE_BULLET.get(), "Simple Bullet");
		add(Items.TACTICAL_POUCHES.get(), "Tactical Pouches");
		
		
		//Misc
		add("lensrandoms.gui.done","Done");
		add("lensrandoms.gui.cancel","Cancel");
		add("tooltip.rgb_inator.rgb_inator", "When in Offhand, changes the color of placed RGBlocks.");
		add("itemGroup.lensrandomstab","Len's Randoms Stuff");
		add("screen.lensrandoms.bulletpouch","Tactical Pouches");
		
	}
}
