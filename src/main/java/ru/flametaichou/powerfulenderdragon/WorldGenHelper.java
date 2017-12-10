package ru.flametaichou.powerfulenderdragon;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldGenHelper {

	public WorldGenHelper() {
		
	}
	
	public static void generatePillar(World world, int x, int y, int z, int height, Block block) {
		for(int i = 0; i < height; i++) {
			world.setBlock(x, y + i, z, block); 
		}
	}
	
	public static void generateWall(World world, int x, int y, int z, Block block, int height, int width) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				generatePillar(world, x + i, y , z, j, block);
			}
		}
	}
	
	public static void generateSolidCube(World world, int x, int y, int z, Block block, int height, int width, int volume) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				for(int k = 0; k < volume; k++) {
					world.setBlock(x + i, y + j, z + k, block);
				}
			}
		}
	}
	
	public static void generateSphere(World world, int x, int y, int z, Block block, int radius1, int radius2) {
		double squareDistance = 0;
		for (int x1 = x-radius1; x1 < x+radius1; x1++)
			for (int y1 = y-radius2; y1 < y+radius2; y1++)
				for (int z1 = z-radius1; z1 < z+radius1; z1++) {
				squareDistance = Math.pow(x1-x,2)/Math.pow(radius1,2) + Math.pow(y1-y,2)/Math.pow(radius2,2) + Math.pow(z1-z,2)/Math.pow(radius1,2);
				if (squareDistance <= 1) {
					world.setBlock(x1, y1, z1, block);
				}
				squareDistance = 0;
			}
	}
}
