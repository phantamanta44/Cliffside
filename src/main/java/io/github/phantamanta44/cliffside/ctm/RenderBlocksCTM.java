package io.github.phantamanta44.cliffside.ctm;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderBlocksCTM extends RenderBlocks {

	/**
	 * An enum for all possible 26 sub-side vertices.
	 *
	 * The naming scheme is as follows:
	 *
	 * ZERO is a special case, it is the absolute min point of the block. X, Y, Z, or any combination means that the axes listed in the name are at 1. X, Y, Z, or any combination followed by HALF
	 * means that those axes are at 0.5. X, Y, Z, or any combination after a HALF means those axes are at 1.
	 */
	protected enum Vert {
		// @formatter:off
		ZERO(0, 0, 0),
		XYZ(1, 1, 1),
		X(1, 0, 0),
		Y(0, 1, 0),
		Z(0, 0, 1),
		XY(1, 1, 0),
		YZ(0, 1, 1),
		XZ(1, 0, 1),
		X_HALF(0.5, 0, 0),
		Y_HALF(0, 0.5, 0),
		Z_HALF(0, 0, 0.5),
		XY_HALF(0.5, 0.5, 0),
		YZ_HALF(0, 0.5, 0.5),
		XZ_HALF(0.5, 0, 0.5),
		X_HALF_Y(0.5, 1, 0),
		X_HALF_Z(0.5, 0, 1),
		Y_HALF_X(1, 0.5, 0),
		Y_HALF_Z(0, 0.5, 1),
		Z_HALF_X(1, 0, 0.5),
		Z_HALF_Y(0, 1, 0.5),
		X_HALF_YZ(0.5, 1, 1),
		Y_HALF_XZ(1, 0.5, 1),
		Z_HALF_XY(1, 1, 0.5),
		XY_HALF_Z(0.5, 0.5, 1),
		YZ_HALF_X(1, 0.5, 0.5),
		XZ_HALF_Y(0.5, 1, 0.5);
		// @formatter:on

		private double x, y, z;

		private Vert(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		private static double u, v, xDiff, yDiff, zDiff, uDiff, vDiff;

		void render(RenderBlocksCTM inst, ForgeDirection normal, int cacheID) {
			if (inst.enableAO) {
				inst.tessellator.setColorOpaque_F(inst.redCache[cacheID], inst.grnCache[cacheID], inst.bluCache[cacheID]);
				inst.tessellator.setBrightness(inst.lightingCache[cacheID]);
			}
			u = cacheID == 1 || cacheID == 2 ? inst.maxU : inst.minU;
			v = cacheID < 2 ? inst.maxV : inst.minV;

			uDiff = inst.maxU - inst.minU;
			vDiff = inst.maxV - inst.minV;

			if (inst.renderMinX + inst.renderMinY + inst.renderMinZ != 0 || inst.renderMaxX + inst.renderMaxY + inst.renderMaxZ != 3) {
				boolean uMin = u == inst.minU;
				boolean vMin = v == inst.minV;

				xDiff = inst.renderMaxX - inst.renderMinX;
				yDiff = inst.renderMaxY - inst.renderMinY;
				zDiff = inst.renderMaxZ - inst.renderMinZ;

				if (normal.offsetY != 0) {
					uDiff *= uMin ? inst.renderMinX : 1 - inst.renderMaxX;
					vDiff *= vMin ? inst.renderMinZ : 1 - inst.renderMaxZ;
				} else if (normal.offsetX != 0) {
					uDiff *= uMin ? inst.renderMinZ : 1 - inst.renderMaxZ;
					vDiff *= vMin ? inst.renderMinY : 1 - inst.renderMaxY;
				} else if (normal.offsetZ != 0) {
					uDiff *= uMin ? inst.renderMinX : 1 - inst.renderMaxX;
					vDiff *= vMin ? inst.renderMinY : 1 - inst.renderMaxY;
				}
				u = u == inst.minU ? inst.minU + uDiff : inst.maxU - uDiff;

				v = v == inst.minV ? inst.minV + vDiff : inst.maxV - vDiff;
			} else {
				xDiff = yDiff = zDiff = 1;
			}

			inst.tessellator.addVertexWithUV(inst.renderMinX + (x * xDiff), inst.renderMinY + (y * yDiff), inst.renderMinZ + (z * zDiff), u, v);
		}
	}

	/**
	 * Each side is divided into 4 sub-sides. LB(left bottom), RB(right bottom), LT(right top), and RT(right top).
	 *
	 * Each sub-side contains 4 {@link Vert} objects representing its position on the block.
	 */
	protected enum SubSide {
		// @formatter:off
		XNEG_LB(Vert.ZERO, Vert.Z_HALF, Vert.YZ_HALF, Vert.Y_HALF), XNEG_RB(Vert.Z_HALF, Vert.Z, Vert.Y_HALF_Z, Vert.YZ_HALF), XNEG_RT(Vert.YZ_HALF, Vert.Y_HALF_Z, Vert.YZ, Vert.Z_HALF_Y), XNEG_LT(Vert.Y_HALF, Vert.YZ_HALF, Vert.Z_HALF_Y, Vert.Y),
		XPOS_LB(Vert.XZ, Vert.Z_HALF_X, Vert.YZ_HALF_X, Vert.Y_HALF_XZ), XPOS_RB(Vert.Z_HALF_X, Vert.X, Vert.Y_HALF_X, Vert.YZ_HALF_X), XPOS_RT(Vert.YZ_HALF_X, Vert.Y_HALF_X, Vert.XY, Vert.Z_HALF_XY), XPOS_LT(Vert.Y_HALF_XZ, Vert.YZ_HALF_X, Vert.Z_HALF_XY, Vert.XYZ),

		ZNEG_LB(Vert.X, Vert.X_HALF, Vert.XY_HALF, Vert.Y_HALF_X), ZNEG_RB(Vert.X_HALF, Vert.ZERO, Vert.Y_HALF, Vert.XY_HALF), ZNEG_RT(Vert.XY_HALF, Vert.Y_HALF, Vert.Y, Vert.X_HALF_Y), ZNEG_LT(Vert.Y_HALF_X, Vert.XY_HALF, Vert.X_HALF_Y, Vert.XY),
		ZPOS_LB(Vert.Z, Vert.X_HALF_Z, Vert.XY_HALF_Z, Vert.Y_HALF_Z), ZPOS_RB(Vert.X_HALF_Z, Vert.XZ, Vert.Y_HALF_XZ, Vert.XY_HALF_Z), ZPOS_RT(Vert.XY_HALF_Z, Vert.Y_HALF_XZ, Vert.XYZ, Vert.X_HALF_YZ), ZPOS_LT(Vert.Y_HALF_Z, Vert.XY_HALF_Z, Vert.X_HALF_YZ, Vert.YZ),

		YNEG_LB(Vert.ZERO, Vert.X_HALF, Vert.XZ_HALF, Vert.Z_HALF), YNEG_RB(Vert.X_HALF, Vert.X, Vert.Z_HALF_X, Vert.XZ_HALF), YNEG_RT(Vert.XZ_HALF, Vert.Z_HALF_X, Vert.XZ, Vert.X_HALF_Z), YNEG_LT(Vert.Z_HALF, Vert.XZ_HALF, Vert.X_HALF_Z, Vert.Z),
		YPOS_LB(Vert.YZ, Vert.X_HALF_YZ, Vert.XZ_HALF_Y, Vert.Z_HALF_Y), YPOS_RB(Vert.X_HALF_YZ, Vert.XYZ, Vert.Z_HALF_XY, Vert.XZ_HALF_Y), YPOS_RT(Vert.XZ_HALF_Y, Vert.Z_HALF_XY, Vert.XY, Vert.X_HALF_Y), YPOS_LT(Vert.Z_HALF_Y, Vert.XZ_HALF_Y, Vert.X_HALF_Y, Vert.Y);
		// @formatter:on
		private Vert xmin, xmax, ymin, ymax;
		private ForgeDirection normal;

		SubSide(Vert xmin, Vert ymin, Vert ymax, Vert xmax) {
			this.xmin = xmin;
			this.ymin = ymin;
			this.ymax = ymax;
			this.xmax = xmax;
			this.normal = calcNormal();
		}

		private ForgeDirection calcNormal() {
			double xTot = xmin.x + xmax.x + ymin.x + ymax.x;
			double yTot = xmin.y + xmax.y + ymin.y + ymax.y;
			double zTot = xmin.z + xmax.z + ymin.z + ymax.z;
			if (xTot % 4 == 0) {
				return xTot > 0 ? ForgeDirection.EAST : ForgeDirection.WEST;
			} else if (yTot % 4 == 0) {
				return yTot > 0 ? ForgeDirection.UP : ForgeDirection.DOWN;
			} else if (zTot % 4 == 0) {
				return zTot > 0 ? ForgeDirection.SOUTH : ForgeDirection.NORTH;
			}
			return ForgeDirection.UNKNOWN;
		}

		void render(RenderBlocksCTM inst) {
			xmin.render(inst, normal, 0);
			ymin.render(inst, normal, 1);
			ymax.render(inst, normal, 2);
			xmax.render(inst, normal, 3);
		}
	}

	public CTM ctm = CTM.getInstance();

	// globals added to save the JVM some trouble. No need to constantly create
	// and destroy ints if we don't have to
	protected int blockLightBitChannel = 0;
	protected int redBitChannel = 0;
	protected int greenBitChannel = 0;
	protected int blueBitChannel = 0;
	protected int sunlightBitChannel = 0;

	public RenderBlocksCTM() {
		super();
		renderMaxX = renderMaxY = renderMaxZ = 1;
	}

	protected Tessellator tessellator = Tessellator.instance;
	protected double minU, maxU;
	protected double minV, maxV;
	protected int[] lightingCache = new int[4];
	protected float[] redCache = new float[4];
	protected float[] grnCache = new float[4];
	protected float[] bluCache = new float[4];
	public TextureSubmap submap;
	public TextureSubmap submapSmall;
	public RenderBlocks rendererOld;
	public ISubmapManager manager;

	protected int[][] lightmap = new int[3][3];
	protected float[][] redmap = new float[3][3];
	protected float[][] grnmap = new float[3][3];
	protected float[][] blumap = new float[3][3];

	protected int bx, by, bz;
	
	protected boolean inWorld = false;

	@Override
	public boolean renderStandardBlock(Block block, int x, int y, int z) {
		bx = x;
		by = y;
		bz = z;

		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tessellator.addTranslation(x, y, z);
		if (rendererOld != null && rendererOld.hasOverrideBlockTexture()) {
			setOverrideBlockTexture(rendererOld.overrideBlockTexture);
		}
		inWorld = true;
		boolean res = super.renderStandardBlock(block, x, y, z);
		inWorld = false;
		tessellator.addTranslation(-x, -y, -z);

		return res;
	}

	/* @formatter:off
	 * This method fills a 3x3 grid of light values based on the four corners, by averaging them together.
	 *
	 * 2  TL   x    TR
	 *
	 * 1  x    x    x
	 *
	 * 0  BL   x    BR
	 *    0    1    2
	 *
	 * Note: Variable names mean nothing... don't touch
	 *
	 * ._.
	 *
	 * shakes fist in anger
	 */
	// @formatter:on
	protected void fillLightmap(int bottomLeft, int bottomRight, int topRight, int topLeft) {
		lightmap[0][0] = bottomLeft;
		lightmap[2][0] = bottomRight;
		lightmap[2][2] = topRight;
		lightmap[0][2] = topLeft;

		lightmap[1][0] = avg(bottomLeft, bottomRight);
		lightmap[2][1] = avg(bottomRight, topRight);
		lightmap[1][2] = avg(topRight, topLeft);
		lightmap[0][1] = avg(topLeft, bottomLeft);

		lightmap[1][1] = avg(bottomLeft, bottomRight, topRight, topLeft);
	}

	protected void fillColormap(float bottomLeft, float bottomRight, float topRight, float topLeft, float[][] map) {
		map[0][0] = bottomLeft;
		map[2][0] = bottomRight;
		map[2][2] = topRight;
		map[0][2] = topLeft;

		map[1][0] = (bottomLeft + bottomRight) / 2.0F;
		map[2][1] = (bottomRight + topRight) / 2.0F;
		map[1][2] = (topRight + topLeft) / 2.0F;
		map[0][1] = (topLeft + bottomLeft) / 2.0F;

		map[1][1] = (bottomLeft + bottomRight + topRight + topLeft) / 4.0F;
	}

	protected void getLight(int x, int y) {
		lightingCache[0] = lightmap[0 + x][0 + y];
		lightingCache[1] = lightmap[1 + x][0 + y];
		lightingCache[2] = lightmap[1 + x][1 + y];
		lightingCache[3] = lightmap[0 + x][1 + y];

		redCache[0] = redmap[0 + x][0 + y];
		redCache[1] = redmap[1 + x][0 + y];
		redCache[2] = redmap[1 + x][1 + y];
		redCache[3] = redmap[0 + x][1 + y];

		grnCache[0] = grnmap[0 + x][0 + y];
		grnCache[1] = grnmap[1 + x][0 + y];
		grnCache[2] = grnmap[1 + x][1 + y];
		grnCache[3] = grnmap[0 + x][1 + y];

		bluCache[0] = blumap[0 + x][0 + y];
		bluCache[1] = blumap[1 + x][0 + y];
		bluCache[2] = blumap[1 + x][1 + y];
		bluCache[3] = blumap[0 + x][1 + y];
	}

	/**
	 * This works around a bug in CLC atm
	 */
	private int avg(int... lightVals) {
		blockLightBitChannel = 0;
		redBitChannel = 0;
		greenBitChannel = 0;
		blueBitChannel = 0;
		sunlightBitChannel = 0;

		for (int light : lightVals) {
			blockLightBitChannel += (light & 0xFF);
			redBitChannel += (light & 0xF00);
			greenBitChannel += (light & 0xF000);
			blueBitChannel += (light & 0xF0000);
			sunlightBitChannel += (light & 0xF00000);
		}

		return ((blockLightBitChannel / lightVals.length) & 0xFF) | ((redBitChannel / lightVals.length) & 0xF00) | ((greenBitChannel / lightVals.length) & 0xF000)
				| ((blueBitChannel / lightVals.length) & 0xF0000) | ((sunlightBitChannel / lightVals.length) & 0xF00000);
	}

	protected void side(Block block, SubSide side, int iconIndex) {

		IIcon icon;
		TextureSubmap map;
		if (iconIndex >= 16) {
			iconIndex -= 16;
			map = submapSmall;
		} else {
			map = submap;
		}

		if (map == null) {
			icon = getBlockIconFromSide(block, side.normal.ordinal());
		} else {
			int x = iconIndex % map.getWidth();
			int y = iconIndex / map.getHeight();
			icon = map.getSubIcon(x, y);
		}

		double umax = icon.getMaxU();
		double umin = icon.getMinU();
		double vmax = icon.getMaxV();
		double vmin = icon.getMinV();

		minU = umin;
		maxU = umax;
		minV = vmin;
		maxV = vmax;

		// uCache[0] = umin;
		// uCache[1] = umax;
		// uCache[2] = umax;
		// uCache[3] = umin;
		//
		// vCache[0] = vmax;
		// vCache[1] = vmax;
		// vCache[2] = vmin;
		// vCache[3] = vmin;

		side.render(this);
	}

	@Override
	public void renderFaceXNeg(Block block, double x, double y, double z, IIcon icon) {
		if (!inWorld || hasOverrideBlockTexture()) {
			IIcon i = hasOverrideBlockTexture() ? overrideBlockTexture : icon;

			tessellator.addVertexWithUV(renderMinX, renderMaxY, renderMinZ, i.getMinU(), i.getMinV());
			tessellator.addVertexWithUV(renderMinX, renderMinY, renderMinZ, i.getMinU(), i.getMaxV());
			tessellator.addVertexWithUV(renderMinX, renderMinY, renderMaxZ, i.getMaxU(), i.getMaxV());
			tessellator.addVertexWithUV(renderMinX, renderMaxY, renderMaxZ, i.getMaxU(), i.getMinV());
		} else {
			int tex[] = ctm.getSubmapIndices(blockAccess, bx, by, bz, 4);

			pre(ForgeDirection.WEST);

			fillLightmap(brightnessBottomRight, brightnessTopRight, brightnessTopLeft, brightnessBottomLeft);
			fillColormap(colorRedBottomRight, colorRedTopRight, colorRedTopLeft, colorRedBottomLeft, redmap);
			fillColormap(colorGreenBottomRight, colorGreenTopRight, colorGreenTopLeft, colorGreenBottomLeft, grnmap);
			fillColormap(colorBlueBottomRight, colorBlueTopRight, colorBlueTopLeft, colorBlueBottomLeft, blumap);

			getLight(0, 0);
			side(block, SubSide.XNEG_LB, tex[0]);
			getLight(1, 0);
			side(block, SubSide.XNEG_RB, tex[1]);
			getLight(1, 1);
			side(block, SubSide.XNEG_RT, tex[2]);
			getLight(0, 1);
			side(block, SubSide.XNEG_LT, tex[3]);

			post(ForgeDirection.WEST);
		}
	}

	@Override
	public void renderFaceXPos(Block block, double x, double y, double z, IIcon icon) {
		if (!inWorld || hasOverrideBlockTexture()) {
			IIcon i = hasOverrideBlockTexture() ? overrideBlockTexture : icon;

			tessellator.addVertexWithUV(renderMaxX, renderMaxY, renderMaxZ, i.getMaxU(), i.getMinV());
			tessellator.addVertexWithUV(renderMaxX, renderMinY, renderMaxZ, i.getMaxU(), i.getMaxV());
			tessellator.addVertexWithUV(renderMaxX, renderMinY, renderMinZ, i.getMinU(), i.getMaxV());
			tessellator.addVertexWithUV(renderMaxX, renderMaxY, renderMinZ, i.getMinU(), i.getMinV());
		} else {
			int tex[] = ctm.getSubmapIndices(blockAccess, bx, by, bz, 5);

			pre(ForgeDirection.EAST);

			fillLightmap(brightnessTopLeft, brightnessBottomLeft, brightnessBottomRight, brightnessTopRight);
			fillColormap(colorRedTopLeft, colorRedBottomLeft, colorRedBottomRight, colorRedTopRight, redmap);
			fillColormap(colorGreenTopLeft, colorGreenBottomLeft, colorGreenBottomRight, colorGreenTopRight, grnmap);
			fillColormap(colorBlueTopLeft, colorBlueBottomLeft, colorBlueBottomRight, colorBlueTopRight, blumap);

			getLight(0, 0);
			side(block, SubSide.XPOS_LB, tex[0]);
			getLight(1, 0);
			side(block, SubSide.XPOS_RB, tex[1]);
			getLight(1, 1);
			side(block, SubSide.XPOS_RT, tex[2]);
			getLight(0, 1);
			side(block, SubSide.XPOS_LT, tex[3]);

			post(ForgeDirection.EAST);
		}
	}

	@Override
	public void renderFaceZNeg(Block block, double x, double y, double z, IIcon icon) {
		if (!inWorld || hasOverrideBlockTexture()) {
			IIcon i = hasOverrideBlockTexture() ? overrideBlockTexture : icon;

			tessellator.addVertexWithUV(renderMaxX, renderMaxY, renderMinZ, i.getMaxU(), i.getMinV());
			tessellator.addVertexWithUV(renderMaxX, renderMinY, renderMinZ, i.getMaxU(), i.getMaxV());
			tessellator.addVertexWithUV(renderMinX, renderMinY, renderMinZ, i.getMinU(), i.getMaxV());
			tessellator.addVertexWithUV(renderMinX, renderMaxY, renderMinZ, i.getMinU(), i.getMinV());
		} else {
			int tex[] = ctm.getSubmapIndices(blockAccess, bx, by, bz, 2);

			pre(ForgeDirection.NORTH);

			fillLightmap(brightnessBottomRight, brightnessTopRight, brightnessTopLeft, brightnessBottomLeft);
			fillColormap(colorRedBottomRight, colorRedTopRight, colorRedTopLeft, colorRedBottomLeft, redmap);
			fillColormap(colorGreenBottomRight, colorGreenTopRight, colorGreenTopLeft, colorGreenBottomLeft, grnmap);
			fillColormap(colorBlueBottomRight, colorBlueTopRight, colorBlueTopLeft, colorBlueBottomLeft, blumap);

			getLight(0, 0);
			side(block, SubSide.ZNEG_LB, tex[0]);
			getLight(1, 0);
			side(block, SubSide.ZNEG_RB, tex[1]);
			getLight(1, 1);
			side(block, SubSide.ZNEG_RT, tex[2]);
			getLight(0, 1);
			side(block, SubSide.ZNEG_LT, tex[3]);

			post(ForgeDirection.NORTH);
		}
	}

	@Override
	public void renderFaceZPos(Block block, double x, double y, double z, IIcon icon) {
		if (!inWorld || hasOverrideBlockTexture()) {
			IIcon i = hasOverrideBlockTexture() ? overrideBlockTexture : icon;

			tessellator.addVertexWithUV(renderMinX, renderMaxY, renderMaxZ, i.getMinU(), i.getMinV());
			tessellator.addVertexWithUV(renderMinX, renderMinY, renderMaxZ, i.getMinU(), i.getMaxV());
			tessellator.addVertexWithUV(renderMaxX, renderMinY, renderMaxZ, i.getMaxU(), i.getMaxV());
			tessellator.addVertexWithUV(renderMaxX, renderMaxY, renderMaxZ, i.getMaxU(), i.getMinV());
		} else {
			int tex[] = ctm.getSubmapIndices(blockAccess, bx, by, bz, 3);

			pre(ForgeDirection.SOUTH);

			fillLightmap(brightnessBottomLeft, brightnessBottomRight, brightnessTopRight, brightnessTopLeft);
			fillColormap(colorRedBottomLeft, colorRedBottomRight, colorRedTopRight, colorRedTopLeft, redmap);
			fillColormap(colorGreenBottomLeft, colorGreenBottomRight, colorGreenTopRight, colorGreenTopLeft, grnmap);
			fillColormap(colorBlueBottomLeft, colorBlueBottomRight, colorBlueTopRight, colorBlueTopLeft, blumap);

			getLight(0, 0);
			side(block, SubSide.ZPOS_LB, tex[0]);
			getLight(1, 0);
			side(block, SubSide.ZPOS_RB, tex[1]);
			getLight(1, 1);
			side(block, SubSide.ZPOS_RT, tex[2]);
			getLight(0, 1);
			side(block, SubSide.ZPOS_LT, tex[3]);

			post(ForgeDirection.SOUTH);
		}
	}

	@Override
	public void renderFaceYNeg(Block block, double x, double y, double z, IIcon icon) {
		if (!inWorld || hasOverrideBlockTexture()) {
			IIcon i = hasOverrideBlockTexture() ? overrideBlockTexture : icon;

			tessellator.addVertexWithUV(renderMinX, renderMinY, renderMaxZ, i.getMinU(), i.getMaxV());
			tessellator.addVertexWithUV(renderMinX, renderMinY, renderMinZ, i.getMinU(), i.getMinV());
			tessellator.addVertexWithUV(renderMaxX, renderMinY, renderMinZ, i.getMaxU(), i.getMinV());
			tessellator.addVertexWithUV(renderMaxX, renderMinY, renderMaxZ, i.getMaxU(), i.getMaxV());
		} else {
			int tex[] = ctm.getSubmapIndices(blockAccess, bx, by, bz, 0);

			pre(ForgeDirection.DOWN);

			fillLightmap(brightnessBottomLeft, brightnessBottomRight, brightnessTopRight, brightnessTopLeft);
			fillColormap(colorRedBottomLeft, colorRedBottomRight, colorRedTopRight, colorRedTopLeft, redmap);
			fillColormap(colorGreenBottomLeft, colorGreenBottomRight, colorGreenTopRight, colorGreenTopLeft, grnmap);
			fillColormap(colorBlueBottomLeft, colorBlueBottomRight, colorBlueTopRight, colorBlueTopLeft, blumap);

			getLight(0, 0);
			side(block, SubSide.YNEG_LB, tex[0]);
			getLight(1, 0);
			side(block, SubSide.YNEG_RB, tex[1]);
			getLight(1, 1);
			side(block, SubSide.YNEG_RT, tex[2]);
			getLight(0, 1);
			side(block, SubSide.YNEG_LT, tex[3]);

			post(ForgeDirection.DOWN);
		}
	}

	@Override
	public void renderFaceYPos(Block block, double x, double y, double z, IIcon icon) {
		if (!inWorld || hasOverrideBlockTexture()) {
			IIcon i = hasOverrideBlockTexture() ? overrideBlockTexture : icon;

			tessellator.addVertexWithUV(renderMinX, renderMaxY, renderMinZ, i.getMinU(), i.getMinV());
			tessellator.addVertexWithUV(renderMinX, renderMaxY, renderMaxZ, i.getMinU(), i.getMaxV());
			tessellator.addVertexWithUV(renderMaxX, renderMaxY, renderMaxZ, i.getMaxU(), i.getMaxV());
			tessellator.addVertexWithUV(renderMaxX, renderMaxY, renderMinZ, i.getMaxU(), i.getMinV());
		} else {
			int tex[] = ctm.getSubmapIndices(blockAccess, bx, by, bz, 1);

			pre(ForgeDirection.UP);

			fillLightmap(brightnessTopRight, brightnessTopLeft, brightnessBottomLeft, brightnessBottomRight);
			fillColormap(colorRedTopRight, colorRedTopLeft, colorRedBottomLeft, colorRedBottomRight, redmap);
			fillColormap(colorGreenTopRight, colorGreenTopLeft, colorGreenBottomLeft, colorGreenBottomRight, grnmap);
			fillColormap(colorBlueTopRight, colorBlueTopLeft, colorBlueBottomLeft, colorBlueBottomRight, blumap);

			getLight(0, 0);
			side(block, SubSide.YPOS_LB, tex[0]);
			getLight(1, 0);
			side(block, SubSide.YPOS_RB, tex[1]);
			getLight(1, 1);
			side(block, SubSide.YPOS_RT, tex[2]);
			getLight(0, 1);
			side(block, SubSide.YPOS_LT, tex[3]);

			post(ForgeDirection.UP);
		}
	}

	protected void pre(ForgeDirection face) {
		manager.preRenderSide(this, blockAccess, bx, by, bz, face);
	}

	protected void post(ForgeDirection face) {
		manager.postRenderSide(this, blockAccess, bx, by, bz, face);
	}
}