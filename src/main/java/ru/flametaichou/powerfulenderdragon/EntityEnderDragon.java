package ru.flametaichou.powerfulenderdragon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEnderDragon extends EntityDragon {
	
    private int explosionStrength = 1;
    private Entity targetedEntity;

	public EntityEnderDragon(World par1World) {
		super(par1World);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0D);
	}
	
	@Override
    public void onUpdate()
    {
		super.onUpdate();
        this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);

        double d4 = 64.0D;
        
        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < d4 * d4)
            {
                double d5 = this.targetedEntity.posX - this.posX;
                double d6 = this.targetedEntity.boundingBox.minY + (double)(this.targetedEntity.height / 2.0F) - (this.posY + (double)(this.height / 2.0F)) - 1;
                double d7 = this.targetedEntity.posZ - this.posZ;
                //this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0F / (float)Math.PI;

                if (this.canEntityBeSeen(this.targetedEntity))
                
    	    if (this.ticksExisted % 100 == 0)
	    	    {
	    	        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
	    	        EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.worldObj, this, d5, d6, d7);
	    	        entitylargefireball.field_92057_e = this.explosionStrength ;
	    	        double d8 = 4.0D;
	    	        Vec3 vec3 = this.getLook(1.0F);
	    	        entitylargefireball.posX = this.posX + vec3.xCoord * d8;
	    	        entitylargefireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
	    	        entitylargefireball.posZ = this.posZ + vec3.zCoord * d8;
	    	        entitylargefireball.accelerationX *= 3;
	    	        entitylargefireball.accelerationY *= 3;
	    	        entitylargefireball.accelerationZ *= 3;
	    	        this.worldObj.spawnEntityInWorld(entitylargefireball);
	    	    }
            }
    }
	
	@Override
    protected void onDeathUpdate()
    {
        ++this.deathTicks;

        if (this.deathTicks >= 180 && this.deathTicks <= 200)
        {
            float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.worldObj.spawnParticle("hugeexplosion", this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
        }

        int i;
        int j;

        if (!this.worldObj.isRemote)
        {
            if (this.deathTicks > 150 && this.deathTicks % 5 == 0)
            {
                i = 1000;

                while (i > 0)
                {
                    j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }

            if (this.deathTicks == 1)
            {
                this.worldObj.playBroadcastSound(1018, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            }
        }

        this.moveEntity(0.0D, 0.10000000149011612D, 0.0D);
        this.renderYawOffset = this.rotationYaw += 20.0F;

        if (this.deathTicks == 200 && !this.worldObj.isRemote)
        {
            i = 2000;

            while (i > 0)
            {
                j = EntityXPOrb.getXPSplit(i);
                i -= j;
                this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
            }

            this.createEnderPortal(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));
            this.setDead();
        }
    }
	
	private void createEnderPortal(int p_70975_1_, int p_70975_2_)
    {
        byte b0 = 64;
        byte b1 = 4;
        
        this.worldObj.setBlock(p_70975_1_, b0 - 1, p_70975_2_, Blocks.bedrock);
        
        this.worldObj.setBlock(p_70975_1_ + 1, b0 - 1, p_70975_2_ + 1, Blocks.obsidian);
        this.worldObj.setBlock(p_70975_1_ + 1, b0 - 1, p_70975_2_ + 0, Blocks.obsidian);
        this.worldObj.setBlock(p_70975_1_ + 1, b0 - 1, p_70975_2_ - 1, Blocks.obsidian);
        this.worldObj.setBlock(p_70975_1_ - 1, b0 - 1, p_70975_2_ + 1, Blocks.obsidian);
        this.worldObj.setBlock(p_70975_1_ - 1, b0 - 1, p_70975_2_ + 0, Blocks.obsidian);
        this.worldObj.setBlock(p_70975_1_ - 1, b0 - 1, p_70975_2_ - 1, Blocks.obsidian);
        this.worldObj.setBlock(p_70975_1_ + 0, b0 - 1, p_70975_2_ + 1, Blocks.obsidian);
        this.worldObj.setBlock(p_70975_1_ + 0, b0 - 1, p_70975_2_ - 1, Blocks.obsidian);
        
        this.worldObj.setBlock(p_70975_1_, b0 - 0, p_70975_2_, Blocks.dragon_egg);
    }
}