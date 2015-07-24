package io.github.phantamanta44.cliffside.ctm;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockWall;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.init.Blocks;
import net.minecraft.src.FMLRenderAccessLibrary;
import net.minecraft.util.IIcon;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderBlocksUtil {
	
	public static boolean renderWorldBlockByGivenType(Block block, int x, int y, int z, RenderBlocks rb, int mode) {
		if (mode == -1)
		{
			return false;
		}
		else
		{
			block.setBlockBoundsBasedOnState(rb.blockAccess, x, y, z);
			rb.setRenderBoundsFromBlock(block);

			switch (mode)
			{
			//regex: ' : \(l == ([\d]+) \?' replace: ';\ncase \1: return' ::: IMPORTANT: REMEMBER THIS ON FIRST line!
			case 0: return rb.renderStandardBlock(block, x, y, z) ;
			case 4: return rb.renderBlockLiquid(block, x, y, z) ;
			case 31: return rb.renderBlockLog(block, x, y, z) ;
			case 1: return rb.renderCrossedSquares(block, x, y, z) ;
			case 40: return rb.renderBlockDoublePlant((BlockDoublePlant)block, x, y, z) ;
			case 2: return rb.renderBlockTorch(block, x, y, z) ;
			case 20: return rb.renderBlockVine(block, x, y, z) ;
			case 11: return rb.renderBlockFence((BlockFence)block, x, y, z) ;
			case 39: return rb.renderBlockQuartz(block, x, y, z) ;
			case 5: return rb.renderBlockRedstoneWire(block, x, y, z) ;
			case 13: return rb.renderBlockCactus(block, x, y, z) ;
			case 9: return rb.renderBlockMinecartTrack((BlockRailBase)block, x, y, z) ;
			case 19: return rb.renderBlockStem(block, x, y, z) ;
			case 23: return rb.renderBlockLilyPad(block, x, y, z) ;
			case 6: return rb.renderBlockCrops(block, x, y, z) ;
			case 3: return rb.renderBlockFire((BlockFire)block, x, y, z) ;
			case 8: return rb.renderBlockLadder(block, x, y, z) ;
			case 7: return rb.renderBlockDoor(block, x, y, z) ;
			case 10: return rb.renderBlockStairs((BlockStairs)block, x, y, z) ;
			case 27: return rb.renderBlockDragonEgg((BlockDragonEgg)block, x, y, z) ;
			case 32: return rb.renderBlockWall((BlockWall)block, x, y, z) ;
			case 12: return rb.renderBlockLever(block, x, y, z) ;
			case 29: return rb.renderBlockTripWireSource(block, x, y, z) ;
			case 30: return rb.renderBlockTripWire(block, x, y, z) ;
			case 14: return rb.renderBlockBed(block, x, y, z) ;
			case 15: return rb.renderBlockRepeater((BlockRedstoneRepeater)block, x, y, z) ;
			case 36: return rb.renderBlockRedstoneDiode((BlockRedstoneDiode)block, x, y, z) ;
			case 37: return rb.renderBlockRedstoneComparator((BlockRedstoneComparator)block, x, y, z) ;
			case 16: return rb.renderPistonBase(block, x, y, z, false) ;
			case 17: return rb.renderPistonExtension(block, x, y, z, true) ;
			case 18: return rb.renderBlockPane((BlockPane)block, x, y, z) ;
			case 41: return rb.renderBlockStainedGlassPane(block, x, y, z) ;
			case 21: return rb.renderBlockFenceGate((BlockFenceGate)block, x, y, z) ;
			case 24: return rb.renderBlockCauldron((BlockCauldron)block, x, y, z) ;
			case 33: return rb.renderBlockFlowerpot((BlockFlowerPot)block, x, y, z) ;
			case 35: return rb.renderBlockAnvil((BlockAnvil)block, x, y, z) ;
			case 25: return rb.renderBlockBrewingStand((BlockBrewingStand)block, x, y, z) ;
			case 26: return rb.renderBlockEndPortalFrame((BlockEndPortalFrame)block, x, y, z) ;
			case 28: return rb.renderBlockCocoa((BlockCocoa)block, x, y, z) ;
			case 34: return rb.renderBlockBeacon((BlockBeacon)block, x, y, z) ;
			case 38: return rb.renderBlockHopper((BlockHopper)block, x, y, z);
			default: return FMLRenderAccessLibrary.renderWorldBlock(rb, rb.blockAccess, x, y, z, block, mode);
			}
		}
	}

    public static void renderItemBlockByGivenType(Block block, int meta, float light, RenderBlocks rb, int mode)
    {
        Tessellator tessellator = Tessellator.instance;
        boolean flag = block == Blocks.grass;

        if (block == Blocks.dispenser || block == Blocks.dropper || block == Blocks.furnace)
        {
            meta = 3;
        }

        int j;
        float f1;
        float f2;
        float f3;

        if (rb.useInventoryTint)
        {
            j = block.getRenderColor(meta);

            if (flag)
            {
                j = 16777215;
            }

            f1 = (float)(j >> 16 & 255) / 255.0F;
            f2 = (float)(j >> 8 & 255) / 255.0F;
            f3 = (float)(j & 255) / 255.0F;
            GL11.glColor4f(f1 * light, f2 * light, f3 * light, 1.0F);
        }

        j = mode;
        rb.setRenderBoundsFromBlock(block);
        int k;

        if (j != 0 && j != 31 && j != 39 && j != 16 && j != 26)
        {
            if (j == 1)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                IIcon iicon = rb.getBlockIconFromSideAndMetadata(block, 0, meta);
                rb.drawCrossedSquares(iicon, -0.5D, -0.5D, -0.5D, 1.0F);
                tessellator.draw();
            }
            else if (j == 19)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                block.setBlockBoundsForItemRender();
                rb.renderBlockStemSmall(block, meta, rb.renderMaxY, -0.5D, -0.5D, -0.5D);
                tessellator.draw();
            }
            else if (j == 23)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                block.setBlockBoundsForItemRender();
                tessellator.draw();
            }
            else if (j == 13)
            {
                block.setBlockBoundsForItemRender();
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                f1 = 0.0625F;
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                rb.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 0));
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 1.0F, 0.0F);
                rb.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 1));
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, -1.0F);
                tessellator.addTranslation(0.0F, 0.0F, f1);
                rb.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 2));
                tessellator.addTranslation(0.0F, 0.0F, -f1);
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, 1.0F);
                tessellator.addTranslation(0.0F, 0.0F, -f1);
                rb.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 3));
                tessellator.addTranslation(0.0F, 0.0F, f1);
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                tessellator.addTranslation(f1, 0.0F, 0.0F);
                rb.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 4));
                tessellator.addTranslation(-f1, 0.0F, 0.0F);
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(1.0F, 0.0F, 0.0F);
                tessellator.addTranslation(-f1, 0.0F, 0.0F);
                rb.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 5));
                tessellator.addTranslation(f1, 0.0F, 0.0F);
                tessellator.draw();
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }
            else if (j == 22)
            {
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                TileEntityRendererChestHelper.instance.renderChest(block, meta, light);
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            }
            else if (j == 6)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                rb.renderBlockCropsImpl(block, meta, -0.5D, -0.5D, -0.5D);
                tessellator.draw();
            }
            else if (j == 2)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                rb.renderTorchAtAngle(block, -0.5D, -0.5D, -0.5D, 0.0D, 0.0D, 0);
                tessellator.draw();
            }
            else if (j == 10)
            {
                for (k = 0; k < 2; ++k)
                {
                    if (k == 0)
                    {
                        rb.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
                    }

                    if (k == 1)
                    {
                        rb.setRenderBounds(0.0D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    rb.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 0));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    rb.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 1));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    rb.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    rb.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 3));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    rb.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 4));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    rb.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 5));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }
            }
            else if (j == 27)
            {
                k = 0;
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                tessellator.startDrawingQuads();

                for (int l = 0; l < 8; ++l)
                {
                    byte b0 = 0;
                    byte b1 = 1;

                    if (l == 0)
                    {
                        b0 = 2;
                    }

                    if (l == 1)
                    {
                        b0 = 3;
                    }

                    if (l == 2)
                    {
                        b0 = 4;
                    }

                    if (l == 3)
                    {
                        b0 = 5;
                        b1 = 2;
                    }

                    if (l == 4)
                    {
                        b0 = 6;
                        b1 = 3;
                    }

                    if (l == 5)
                    {
                        b0 = 7;
                        b1 = 5;
                    }

                    if (l == 6)
                    {
                        b0 = 6;
                        b1 = 2;
                    }

                    if (l == 7)
                    {
                        b0 = 3;
                    }

                    float f5 = (float)b0 / 16.0F;
                    float f6 = 1.0F - (float)k / 16.0F;
                    float f7 = 1.0F - (float)(k + b1) / 16.0F;
                    k += b1;
                    rb.setRenderBounds((double)(0.5F - f5), (double)f7, (double)(0.5F - f5), (double)(0.5F + f5), (double)f6, (double)(0.5F + f5));
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    rb.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 0));
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    rb.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 1));
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    rb.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 2));
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    rb.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 3));
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    rb.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 4));
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    rb.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 5));
                }

                tessellator.draw();
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                rb.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            else if (j == 11)
            {
                for (k = 0; k < 4; ++k)
                {
                    f2 = 0.125F;

                    if (k == 0)
                    {
                        rb.setRenderBounds((double)(0.5F - f2), 0.0D, 0.0D, (double)(0.5F + f2), 1.0D, (double)(f2 * 2.0F));
                    }

                    if (k == 1)
                    {
                        rb.setRenderBounds((double)(0.5F - f2), 0.0D, (double)(1.0F - f2 * 2.0F), (double)(0.5F + f2), 1.0D, 1.0D);
                    }

                    f2 = 0.0625F;

                    if (k == 2)
                    {
                        rb.setRenderBounds((double)(0.5F - f2), (double)(1.0F - f2 * 3.0F), (double)(-f2 * 2.0F), (double)(0.5F + f2), (double)(1.0F - f2), (double)(1.0F + f2 * 2.0F));
                    }

                    if (k == 3)
                    {
                        rb.setRenderBounds((double)(0.5F - f2), (double)(0.5F - f2 * 3.0F), (double)(-f2 * 2.0F), (double)(0.5F + f2), (double)(0.5F - f2), (double)(1.0F + f2 * 2.0F));
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    rb.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 0));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    rb.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 1));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    rb.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    rb.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 3));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    rb.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 4));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    rb.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 5));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }

                rb.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            else if (j == 21)
            {
                for (k = 0; k < 3; ++k)
                {
                    f2 = 0.0625F;

                    if (k == 0)
                    {
                        rb.setRenderBounds((double)(0.5F - f2), 0.30000001192092896D, 0.0D, (double)(0.5F + f2), 1.0D, (double)(f2 * 2.0F));
                    }

                    if (k == 1)
                    {
                        rb.setRenderBounds((double)(0.5F - f2), 0.30000001192092896D, (double)(1.0F - f2 * 2.0F), (double)(0.5F + f2), 1.0D, 1.0D);
                    }

                    f2 = 0.0625F;

                    if (k == 2)
                    {
                        rb.setRenderBounds((double)(0.5F - f2), 0.5D, 0.0D, (double)(0.5F + f2), (double)(1.0F - f2), 1.0D);
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    rb.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 0));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    rb.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 1));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    rb.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    rb.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 3));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    rb.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 4));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    rb.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSide(block, 5));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }
            }
            else if (j == 32)
            {
                for (k = 0; k < 2; ++k)
                {
                    if (k == 0)
                    {
                        rb.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
                    }

                    if (k == 1)
                    {
                        rb.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    rb.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 0, meta));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    rb.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 1, meta));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    rb.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 2, meta));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    rb.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 3, meta));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    rb.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 4, meta));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    rb.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 5, meta));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }

                rb.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            else if (j == 35)
            {
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                rb.renderBlockAnvilOrient((BlockAnvil)block, 0, 0, 0, meta << 2, true);
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }
            else if (j == 34)
            {
                for (k = 0; k < 3; ++k)
                {
                    if (k == 0)
                    {
                        rb.setRenderBounds(0.125D, 0.0D, 0.125D, 0.875D, 0.1875D, 0.875D);
                        rb.setOverrideBlockTexture(rb.getBlockIcon(Blocks.obsidian));
                    }
                    else if (k == 1)
                    {
                        rb.setRenderBounds(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
                        rb.setOverrideBlockTexture(rb.getBlockIcon(Blocks.beacon));
                    }
                    else if (k == 2)
                    {
                        rb.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
                        rb.setOverrideBlockTexture(rb.getBlockIcon(Blocks.glass));
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    rb.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 0, meta));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    rb.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 1, meta));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    rb.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 2, meta));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    rb.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 3, meta));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    rb.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 4, meta));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    rb.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 5, meta));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }

                rb.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
                rb.clearOverrideBlockTexture();
            }
            else if (j == 38)
            {
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                rb.renderBlockHopperMetadata((BlockHopper)block, 0, 0, 0, 0, true);
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }
            else
            {
                FMLRenderAccessLibrary.renderInventoryBlock(rb, block, meta, j);
            }
        }
        else
        {
            if (j == 16)
            {
                meta = 1;
            }

            block.setBlockBoundsForItemRender();
            rb.setRenderBoundsFromBlock(block);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
            rb.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 0, meta));
            tessellator.draw();

            if (flag && rb.useInventoryTint)
            {
                k = block.getRenderColor(meta);
                f2 = (float)(k >> 16 & 255) / 255.0F;
                f3 = (float)(k >> 8 & 255) / 255.0F;
                float f4 = (float)(k & 255) / 255.0F;
                GL11.glColor4f(f2 * light, f3 * light, f4 * light, 1.0F);
            }

            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            rb.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 1, meta));
            tessellator.draw();

            if (flag && rb.useInventoryTint)
            {
                GL11.glColor4f(light, light, light, 1.0F);
            }

            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
            rb.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 2, meta));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            rb.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 3, meta));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
            rb.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 4, meta));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            rb.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, rb.getBlockIconFromSideAndMetadata(block, 5, meta));
            tessellator.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
    }
	
}
