package ru.flametaichou.powerfulenderdragon;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class DragonHandler {
	
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event) {
		if(event.entity.getClass().equals(EntityDragon.class)){
        	if	(!event.world.isRemote && event.world.provider.dimensionId == 1)	{
				EntityEnderDragon dragon = new EntityEnderDragon(event.world);
				dragon.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
				event.world.spawnEntityInWorld(dragon);
        	}
        	event.setCanceled(true);
		}
	}
}
