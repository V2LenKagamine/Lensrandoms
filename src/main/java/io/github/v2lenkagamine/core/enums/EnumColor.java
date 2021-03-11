package io.github.v2lenkagamine.core.enums;

import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;

public enum EnumColor {
	
	BLACK("\u00a70",new int[]{64, 64, 64},DyeColor.BLACK,"black"),
	BLUE("\u00a71",new int[]{54, 107, 208},DyeColor.BLUE,"blue"),
	GREEN("\u00a72", new int[]{89, 193, 95}, DyeColor.GREEN, "green"),
	CYAN("\u00a73",new int[]{0, 243, 208}, DyeColor.CYAN,"cyan"),
	RED("\u00a7c",new int[]{255, 56, 60}, DyeColor.RED,"red"),
	PURPLE("\u00a75", new int[]{164, 96, 217}, DyeColor.PURPLE,"purple"),
	ORANGE("\u00a76", new int[]{255, 161, 96}, DyeColor.ORANGE,"orange"),
	LIGHT_GREY("\u00a77",new int[]{207, 207, 207}, DyeColor.LIGHT_GRAY,"light_grey"),
	GREY("\u00a78",new int[]{122, 122, 122}, DyeColor.GRAY,"grey"),
	LIGHT_BLUE("\u00a79",new int[]{85, 158, 255}, DyeColor.LIGHT_BLUE, "light_blue"),
	LIME("\u00a7a", new int[]{117, 255, 137}, DyeColor.LIME, "lime"),
	MAGENTA("\u00a7d",new int[]{213, 94, 203}, DyeColor.MAGENTA,"magenta"),
	YELLOW("\u00a7e",new int[]{255, 221, 79}, DyeColor.YELLOW,"yellow"),
	WHITE("\u00a7f",new int[]{255, 255, 255}, DyeColor.WHITE,"white"),
	BROWN("\u00a76",new int[]{161, 118, 73}, DyeColor.BROWN,"brown"),
	PINK("\u00a7d",new int[]{255, 188, 196}, DyeColor.PINK,"pink");
	
	public final String code;
	private int[] rgbCode;
	private final ITag<Item> dyeTag;
    private final MaterialColor mapColor;
    private final String registryPrefix;
    
	EnumColor(String code, int[] rgbCode,DyeColor dyeColor,String registryPrefix) {
		 this(code,rgbCode, dyeColor.getMapColor(), dyeColor.getTag(), registryPrefix);
	}
	EnumColor(String code, int[] rgbCode, MaterialColor mapColor, ITag<Item> dyeTag, String registryPrefix) {
		
		this.registryPrefix = registryPrefix;
        this.code = code;
		this.mapColor = mapColor;
        this.dyeTag = dyeTag;
    }
	
	public String getRegistryPrefix() {
        return registryPrefix;
    }
	
	public int[] getRgbCode() {
        return rgbCode;
    }
	
	public ITag<Item> getDyeTag() {
        return dyeTag;
    }
	
    public MaterialColor getMapColor() {
        return mapColor;
    }
    public float getColor(int index) {
        return rgbCode[index] / 255F;
    }
    public int getRgbasint(int[] array){
    	int color = 0xFF000000 | array[0] << 16 | array[1] << 8 | array[2];
    	return color;
    }
    
}
