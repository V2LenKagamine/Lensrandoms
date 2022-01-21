package io.github.v2lenkagamine.datagen;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.init.blocks.LensBlocks;
import io.github.v2lenkagamine.core.items.LensItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LensRandomsLanguage extends LanguageProvider{

	public LensRandomsLanguage(DataGenerator gen, String locale) {
		super(gen, Lensrandoms.MOD_ID, locale);

	}

	@Override
	protected void addTranslations() {
		
		
		//Blocks
		add(LensBlocks.RGBLOCK.get(), "RGBlock");
		add(LensBlocks.RGBLOCK_GLOW.get(),"Glowing RGBlock");
		add(LensBlocks.RGBLOCK_SLAB.get(),"RGBlock Slab");
		add(LensBlocks.RGBLOCK_STAIR.get(),"RGBlock Stair");
		add(LensBlocks.RGBLOCK_BRICK.get(),"RGBlock Bricks");
		add(LensBlocks.RGBLOCK_BRICK_GLOW.get(),"Glowing RGBlock Bricks");
		add(LensBlocks.RGBLOCK_BRICK_SLAB.get(),"RGBlock Brick Slab");
		add(LensBlocks.RGBLOCK_BRICK_STAIR.get(),"RGBlock Brick Stair");
		add(LensBlocks.RGBLOCK_GLASS.get(), "RGB Glass");
		add(LensBlocks.RGBLOCK_GLASS_GLOW.get(), "Glowing RGB Glass");
		add(LensBlocks.RGBLOCK_GLASS_BORDER.get(), "Bordered RGB Glass");
		add(LensBlocks.RGBLOCK_GLASS_BORDER_GLOW.get(), "Glowing Bordered RGB Glass");
		add(LensBlocks.CLEARGLASS.get(),"Clear Glass");
		add(LensBlocks.CLEARGLASS_BORDER.get(),"Bordered Clear Glass");
		add(LensBlocks.CLEARGLASS_GLOW.get(),"Glowing Clear Glass");
		add(LensBlocks.CLEARGLASS_BORDER_GLOW.get(),"Glowing Bordered Clear Glass");
		add(LensBlocks.CHARGER.get(), "Item Charger");
		add(LensBlocks.GUNSMITHING_TABLE.get(),"Gunsmithing Table");
		
		//Items
		add(LensItems.TEST_APPLE.get(),"The Apple of Testing");
		add(LensItems.RGB_INATOR.get(),"\u00A74R\u00A72G\u00A71B\u00A7r-inator");
		add(LensItems.TEST_ITEM.get(), "Icon of the Creator");
		add(LensItems.TACTICAL_POUCHES.get(), "Tactical Pouches");
		add(LensItems.BREEFCASE.get(),"BrEEfcase");
		add(LensItems.BEECASE.get(),"BeeCase");
		
		//Guns
		add(LensItems.GUN_BLUNDERBUS.get(), "Blunderbus");
		add(LensItems.GUN_FLINTLOCK.get(),"Flintlock Pistol");
		add(LensItems.GUN_REVOLVER.get(),"Simple Revolver");
		add(LensItems.GUN_FIFTYCAL.get(),"Fifty Cal. Rifle");
		add(LensItems.GUN_LENS_REVOLVER.get(), "Len's Revolver");
		
		//Energy Guns
		add(LensItems.GUN_LASER_PISTOL.get(),"Laser Pistol");

		//Ammo
		add(LensItems.SIMPLE_BULLET.get(), "Simple Bullet");
		add(LensItems.PISTOL_ROUND.get(),"Pistol Round");
		add(LensItems.RIFLE_ROUND.get(),"Rifle Round");
		add(LensItems.SHOTGUN_SHELL.get(),"Shotgun Shell");
		add(LensItems.ARTILLERY_SHELL.get(),"Artillery Shell");
		add(LensItems.HE_GRENADE_SHELL.get(), "HE Grenade Round");
		add(LensItems.SNIPER_ROUND.get(),"Sniper Round");
		add(LensItems.ROCKET.get(),"Rocket");
		
		//Sounds
		add("lensrandoms.gun_click.sub","Gun Clicks");
		add("lensrandoms.breefcase.sub", "Someone throws a briefcase of bees");
		add("lensrandoms.reload_base.sub","Gun Reloads");
		add("lensrandoms.shot_base.sub", "Gun Fires");
		add("lensrandoms.fifty_cal.sub","50. Cal. fires");
		add ("lensrandoms.laser_base.sub","Energy Weapon fires");
	
		//Misc
		add("lensrandoms.gui.done","Done");
		add("lensrandoms.gui.cancel","Cancel");
		add("tooltip.breefcase.breefcase","\u00A7cCreator not responsible for damages caused.");
		add("tooltip.rgb_inator.rgb_inator", "When in Offhand, changes the color of placed RGBlocks.");
		add("itemGroup.lensrandomstab","Len's Randoms Stuff");
		add("screen.lensrandoms.bulletpouch","Tactical Pouches");
		add("screen.lensrandoms.charger","Item Charger");
		add("screen.lensrandoms.gunsmithingtable","Gunsmithing Table");
		add("container.lensrandoms.gunsmithingtable","Gunsmithing Table");
		add("lensrandoms.gui.craftgun","Craft Item");		
		add("lensrandoms.gui.show_remaining", "Show Remaining");
	}
}
