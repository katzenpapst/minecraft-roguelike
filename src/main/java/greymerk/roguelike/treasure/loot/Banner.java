package greymerk.roguelike.treasure.loot;

import java.util.Random;

import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityBanner.EnumBannerPattern;

public class Banner {

	public static ItemStack get(Random rand){
		
		ItemStack banner = new ItemStack(Items.banner);
		for(int i = 0; i < rand.nextInt(4) + 1; ++i){
			addPattern(banner, rand);
		}
		
		return banner;
	}
	
	public static ItemStack addPattern(ItemStack banner, Random rand){
		EnumBannerPattern pattern = EnumBannerPattern.values()[rand.nextInt(EnumBannerPattern.values().length)];
		EnumDyeColor color = EnumDyeColor.values()[rand.nextInt(EnumDyeColor.values().length)];
		
		return addPattern(banner, pattern, color);
	}
	
	public static ItemStack addPattern(ItemStack banner, EnumBannerPattern pattern, EnumDyeColor color){
		
		NBTTagCompound nbt = banner.getTagCompound();
		if(nbt == null){
			banner.setTagCompound(new NBTTagCompound());
			nbt = banner.getTagCompound();
		}
		
		NBTTagCompound tag;
		
		if(nbt.hasKey("BlockEntityTag")){
			tag = nbt.getCompoundTag("BlockEntityTag");
		} else {
			tag = new NBTTagCompound();
			nbt.setTag("BlockEntityTag", tag);
		}
		
		NBTTagList patterns;
		
		if(tag.hasKey("Patterns")){
			patterns = tag.getTagList("Patterns", 10);
		} else {
			patterns = new NBTTagList();
			tag.setTag("Patterns", patterns);
		}
				
		NBTTagCompound toAdd = new NBTTagCompound();
		toAdd.setInteger("Color", color.getDyeDamage());
		toAdd.setString("Pattern", pattern.getPatternID());
		patterns.appendTag(toAdd);
		
		return banner;
	}
	
}
