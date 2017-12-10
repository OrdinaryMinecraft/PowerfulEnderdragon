package ru.flametaichou.powerfulenderdragon;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;


@Mod (modid = "powerfulenderdragon", name = "PowerfulEnderdragon", version = "0.2")

public class Main {
	
public static DragonHandler eventHandler = new DragonHandler();	

	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(eventHandler);
		MinecraftForge.EVENT_BUS.register(eventHandler);
		EntityRegistry.registerGlobalEntityID(EntityEnderDragon.class, "ancientenderdragon", EntityRegistry.findGlobalUniqueEntityId());
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 1);
	}
	
}
