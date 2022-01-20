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
		add(Blocks.CHARGER.get(), "Item Charger");
		add(Blocks.GUNSMITHING_TABLE.get(),"Gunsmithing Table");
		
		//Items
		add(Items.TEST_APPLE.get(),"The Apple of Testing");
		add(Items.RGB_INATOR.get(),"\u00A74R\u00A72G\u00A71B\u00A7r-inator");
		add(Items.TEST_ITEM.get(), "Icon of the Creator");
		add(Items.TACTICAL_POUCHES.get(), "Tactical Pouches");
		add(Items.BREEFCASE.get(),"BrEEfcase");
		add(Items.BEECASE.get(),"BeeCase");
		
		//Guns
		add(Items.GUN_BLUNDERBUS.get(), "Blunderbus");
		add(Items.GUN_FLINTLOCK.get(),"Flintlock Pistol");
		add(Items.GUN_REVOLVER.get(),"Simple Revolver");
		add(Items.GUN_FIFTYCAL.get(),"Fifty Cal. Rifle");
		add(Items.GUN_LENS_REVOLVER.get(), "Len's Revolver");
		
		//Energy Guns
		add(Items.GUN_LASER_PISTOL.get(),"Laser Pistol");

		//Ammo
		add(Items.SIMPLE_BULLET.get(), "Simple Bullet");
		add(Items.PISTOL_ROUND.get(),"Pistol Round");
		add(Items.RIFLE_ROUND.get(),"Rifle Round");
		add(Items.SHOTGUN_SHELL.get(),"Shotgun Shell");
		add(Items.ARTILLERY_SHELL.get(),"Artillery Shell");
		add(Items.HE_GRENADE_SHELL.get(), "HE Grenade Round");
		add(Items.SNIPER_ROUND.get(),"Sniper Round");
		add(Items.ROCKET.get(),"Rocket");
		
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
		add("lensrandoms.gui.craftgun","Craft Weapon");		
		add("lensrandoms.gui.show_remaining", "Show Remaining");
	}
}
