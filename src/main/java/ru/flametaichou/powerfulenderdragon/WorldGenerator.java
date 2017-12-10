package ru.flametaichou.powerfulenderdragon;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenSpikes;

public class WorldGenerator implements IWorldGenerator {

	
	
	public WorldGenerator() {
		
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId) {
		case 1:
				//generateSurface(world, chunkX, chunkZ, random);
			break;
		default:
			return;
		}
	}
	
	private void generateSurface(World world, int chunkX, int chunkZ, Random random) {
		int posX = chunkX + random.nextInt(600)-300;
		int posY = (random.nextInt(55 - 50 + 1) + 50);
		int posZ = random.nextInt(600)-300 + chunkZ;
		
		if(random.nextInt(100) >= 101) { //All these are here to try to make the structures much much much rarer. These numbers may need some tweaking
						if(world.getBlock(posX, posY, posZ) == Blocks.end_stone) {
							WorldGenHelper.generateSolidCube(world, posX, posY, posZ, Blocks.brick_block, 8, 8, 8);
							world.setBlock(posX + 4, posY + 4, posZ + 4, Blocks.chest);
							
							TileEntityChest chest = (TileEntityChest) world.getTileEntity(posX + 4, posY + 4, posZ + 4);
							
							if(chest != null) { //So we don't crash the game if another structure overwrites the chest
								Item[] possibleLoot = new Item[] {Items.diamond, Items.gold_ingot, Items.iron_ingot, Items.emerald, Items.bone, Items.coal, Items.rotten_flesh, Items.carrot, Items.golden_apple};
								
								for(int i = 0; i < MathHelper.getRandomIntegerInRange(random, 1, 9); i++) {
									chest.setInventorySlotContents(i, new ItemStack(possibleLoot[random.nextInt(possibleLoot.length)], MathHelper.getRandomIntegerInRange(random, 1, 5)));
								}
							}
							System.out.println("Generated Tomb at: " + posX + " " + posY + " " + posZ); 
						} else {
							return;
						}
		}
		if(random.nextInt(100) >= 75) { //All these are here to try to make the structures much much much rarer. These numbers may need some tweaking
			if(world.getBlock(posX, posY, posZ) == Blocks.air) {
				WorldGenHelper.generateSphere(world, posX, posY, posZ, Blocks.end_stone, (random.nextInt(50 - 30 + 1) + 30), (random.nextInt(10 - 5 + 1) + 5));
				//System.out.println("Generated sphere at: " + posX + " " + posY + " " + posZ); 
				}
			} else {
				return;
			}
	}
}