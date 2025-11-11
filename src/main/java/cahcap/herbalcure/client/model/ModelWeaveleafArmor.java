package cahcap.herbalcure.client.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;

import javax.annotation.Nonnull;

/**
 * Weaveleaf Armor Model
 * Generated with Blockbench 5.0.4
 * For Minecraft version 1.7 - 1.12
 */
public class ModelWeaveleafArmor extends ModelArmor
{
    protected final ModelRenderer Head;
    protected final ModelRenderer Body;
    protected final ModelRenderer RightRobeSide;
    protected final ModelRenderer RightRobeSideFront_r1;
    protected final ModelRenderer RightRobeSideCenter_r1;
    protected final ModelRenderer RightRobeSideCenter_r2;
    protected final ModelRenderer RightRobeSideCenter_r3;
    protected final ModelRenderer RightRobeSideCenter_r4;
    protected final ModelRenderer RightRobeSideFront_r2;
    protected final ModelRenderer LeftRobeSide;
    protected final ModelRenderer LeftRobeSideFront_r1;
    protected final ModelRenderer LeftRobeSideCenter_r1;
    protected final ModelRenderer LeftRobeSideCenter_r2;
    protected final ModelRenderer LeftRobeSideCenter_r3;
    protected final ModelRenderer LeftRobeSideCenter_r4;
    protected final ModelRenderer LeftRobeSideFront_r2;
    protected final ModelRenderer RightRobeBack;
    protected final ModelRenderer RightRobeBackSide_r1;
    protected final ModelRenderer LeftRobeBack;
    protected final ModelRenderer LeftRobeBackSide_r1;
    protected final ModelRenderer RightArm;
    protected final ModelRenderer LeftArm;
    public final ModelRenderer RightLegBoots;
    public final ModelRenderer LeftLegBoots;
    public final ModelRenderer RightLegLeggings;
    public final ModelRenderer LeftLegLeggings;

    public ModelWeaveleafArmor(EntityEquipmentSlot slot)
    {
        super(slot);
        textureWidth = 64;
        textureHeight = 64;

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        Head.cubeList.add(new ModelBox(Head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 1.0F, false));
        Head.cubeList.add(new ModelBox(Head, 31, 32, -4.0F, -8.0F, 5.0F, 8, 8, 1, 0.0F, true));
        
        // Map custom parts to ModelBiped parts
        this.bipedHead = Head;
        // Don't set bipedHeadwear here, it will be handled in render()

        Body = new ModelRenderer(this);
        Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        Body.cubeList.add(new ModelBox(Body, 16, 16, -4.0F, 0.0F, -2.0F, 8, 11, 4, 1.01F, false));

        RightRobeSide = new ModelRenderer(this);
        RightRobeSide.setRotationPoint(-5.1695F, 15.9086F, -3.0F);
        Body.addChild(RightRobeSide);
        setRotateAngle(RightRobeSide, 0.0F, 0.0F, 0.0436F);

        RightRobeSideFront_r1 = new ModelRenderer(this);
        RightRobeSideFront_r1.setRotationPoint(0.0F, 0.0F, 4.0F);
        RightRobeSide.addChild(RightRobeSideFront_r1);
        setRotateAngle(RightRobeSideFront_r1, 0.0F, -1.5708F, 0.1309F);
        RightRobeSideFront_r1.cubeList.add(new ModelBox(RightRobeSideFront_r1, 8, 32, 1.0F, -4.0F, -0.5F, 1, 7, 1, 0.0F, false));

        RightRobeSideCenter_r1 = new ModelRenderer(this);
        RightRobeSideCenter_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        RightRobeSide.addChild(RightRobeSideCenter_r1);
        setRotateAngle(RightRobeSideCenter_r1, 0.0F, -1.5708F, 0.1309F);
        RightRobeSideCenter_r1.cubeList.add(new ModelBox(RightRobeSideCenter_r1, 4, 32, 1.0F, -4.0F, -0.5F, 1, 7, 1, 0.0F, false));

        RightRobeSideCenter_r2 = new ModelRenderer(this);
        RightRobeSideCenter_r2.setRotationPoint(0.0F, 0.0F, 1.0F);
        RightRobeSide.addChild(RightRobeSideCenter_r2);
        setRotateAngle(RightRobeSideCenter_r2, 0.0F, -1.5708F, 0.1309F);
        RightRobeSideCenter_r2.cubeList.add(new ModelBox(RightRobeSideCenter_r2, 4, 32, 1.0F, -4.0F, -0.5F, 1, 7, 1, 0.0F, false));

        RightRobeSideCenter_r3 = new ModelRenderer(this);
        RightRobeSideCenter_r3.setRotationPoint(0.0F, 0.0F, 2.0F);
        RightRobeSide.addChild(RightRobeSideCenter_r3);
        setRotateAngle(RightRobeSideCenter_r3, 0.0F, -1.5708F, 0.1309F);
        RightRobeSideCenter_r3.cubeList.add(new ModelBox(RightRobeSideCenter_r3, 4, 32, 1.0F, -4.0F, -0.5F, 1, 7, 1, 0.0F, false));

        RightRobeSideCenter_r4 = new ModelRenderer(this);
        RightRobeSideCenter_r4.setRotationPoint(0.0F, 0.0F, 3.0F);
        RightRobeSide.addChild(RightRobeSideCenter_r4);
        setRotateAngle(RightRobeSideCenter_r4, 0.0F, -1.5708F, 0.1309F);
        RightRobeSideCenter_r4.cubeList.add(new ModelBox(RightRobeSideCenter_r4, 4, 32, 1.0F, -4.0F, -0.5F, 1, 7, 1, 0.0F, false));

        RightRobeSideFront_r2 = new ModelRenderer(this);
        RightRobeSideFront_r2.setRotationPoint(0.0F, 0.0F, -1.0F);
        RightRobeSide.addChild(RightRobeSideFront_r2);
        setRotateAngle(RightRobeSideFront_r2, 0.0F, -1.5708F, 0.1309F);
        RightRobeSideFront_r2.cubeList.add(new ModelBox(RightRobeSideFront_r2, 0, 32, 1.0F, -4.0F, -0.5F, 1, 6, 1, 0.0F, false));

        LeftRobeSide = new ModelRenderer(this);
        LeftRobeSide.setRotationPoint(5.1695F, 15.9086F, -3.0F);
        Body.addChild(LeftRobeSide);
        setRotateAngle(LeftRobeSide, 0.0F, 0.0F, -0.0436F);

        LeftRobeSideFront_r1 = new ModelRenderer(this);
        LeftRobeSideFront_r1.setRotationPoint(0.0F, 0.0F, 4.0F);
        LeftRobeSide.addChild(LeftRobeSideFront_r1);
        setRotateAngle(LeftRobeSideFront_r1, 0.0F, 1.5708F, -0.1309F);
        LeftRobeSideFront_r1.cubeList.add(new ModelBox(LeftRobeSideFront_r1, 8, 32, -2.0F, -4.0F, -0.5F, 1, 7, 1, 0.0F, true));

        LeftRobeSideCenter_r1 = new ModelRenderer(this);
        LeftRobeSideCenter_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        LeftRobeSide.addChild(LeftRobeSideCenter_r1);
        setRotateAngle(LeftRobeSideCenter_r1, 0.0F, 1.5708F, -0.1309F);
        LeftRobeSideCenter_r1.cubeList.add(new ModelBox(LeftRobeSideCenter_r1, 4, 32, -2.0F, -4.0F, -0.5F, 1, 7, 1, 0.0F, true));

        LeftRobeSideCenter_r2 = new ModelRenderer(this);
        LeftRobeSideCenter_r2.setRotationPoint(0.0F, 0.0F, 1.0F);
        LeftRobeSide.addChild(LeftRobeSideCenter_r2);
        setRotateAngle(LeftRobeSideCenter_r2, 0.0F, 1.5708F, -0.1309F);
        LeftRobeSideCenter_r2.cubeList.add(new ModelBox(LeftRobeSideCenter_r2, 4, 32, -2.0F, -4.0F, -0.5F, 1, 7, 1, 0.0F, true));

        LeftRobeSideCenter_r3 = new ModelRenderer(this);
        LeftRobeSideCenter_r3.setRotationPoint(0.0F, 0.0F, 2.0F);
        LeftRobeSide.addChild(LeftRobeSideCenter_r3);
        setRotateAngle(LeftRobeSideCenter_r3, 0.0F, 1.5708F, -0.1309F);
        LeftRobeSideCenter_r3.cubeList.add(new ModelBox(LeftRobeSideCenter_r3, 4, 32, -2.0F, -4.0F, -0.5F, 1, 7, 1, 0.0F, true));

        LeftRobeSideCenter_r4 = new ModelRenderer(this);
        LeftRobeSideCenter_r4.setRotationPoint(0.0F, 0.0F, 3.0F);
        LeftRobeSide.addChild(LeftRobeSideCenter_r4);
        setRotateAngle(LeftRobeSideCenter_r4, 0.0F, 1.5708F, -0.1309F);
        LeftRobeSideCenter_r4.cubeList.add(new ModelBox(LeftRobeSideCenter_r4, 4, 32, -2.0F, -4.0F, -0.5F, 1, 7, 1, 0.0F, true));

        LeftRobeSideFront_r2 = new ModelRenderer(this);
        LeftRobeSideFront_r2.setRotationPoint(0.0F, 0.0F, -1.0F);
        LeftRobeSide.addChild(LeftRobeSideFront_r2);
        setRotateAngle(LeftRobeSideFront_r2, 0.0F, 1.5708F, -0.1309F);
        LeftRobeSideFront_r2.cubeList.add(new ModelBox(LeftRobeSideFront_r2, 0, 32, -2.0F, -4.0F, -0.5F, 1, 6, 1, 0.0F, true));

        RightRobeBack = new ModelRenderer(this);
        RightRobeBack.setRotationPoint(1.5F, 17.0883F, 3.1085F);
        Body.addChild(RightRobeBack);
        setRotateAngle(RightRobeBack, 0.0436F, 0.0F, 0.0F);

        RightRobeBackSide_r1 = new ModelRenderer(this);
        RightRobeBackSide_r1.setRotationPoint(-2.0F, -1.19F, -0.074F);
        RightRobeBack.addChild(RightRobeBackSide_r1);
        setRotateAngle(RightRobeBackSide_r1, 0.1309F, 0.0F, 0.0F);
        RightRobeBackSide_r1.cubeList.add(new ModelBox(RightRobeBackSide_r1, 16, 32, -0.5F, -3.9653F, -0.303F, 1, 8, 1, 0.0F, false));
        RightRobeBackSide_r1.cubeList.add(new ModelBox(RightRobeBackSide_r1, 16, 32, -1.5F, -3.9653F, -0.303F, 1, 8, 1, 0.0F, false));
        RightRobeBackSide_r1.cubeList.add(new ModelBox(RightRobeBackSide_r1, 16, 32, -2.5F, -3.9653F, -0.303F, 1, 8, 1, 0.0F, false));
        RightRobeBackSide_r1.cubeList.add(new ModelBox(RightRobeBackSide_r1, 16, 32, -3.5F, -3.9653F, -0.303F, 1, 8, 1, 0.0F, false));
        RightRobeBackSide_r1.cubeList.add(new ModelBox(RightRobeBackSide_r1, 12, 32, -4.5F, -3.9653F, -0.303F, 1, 7, 1, 0.0F, false));

        LeftRobeBack = new ModelRenderer(this);
        LeftRobeBack.setRotationPoint(-1.5F, 17.0883F, 3.1085F);
        Body.addChild(LeftRobeBack);
        setRotateAngle(LeftRobeBack, 0.0436F, 0.0F, 0.0F);

        LeftRobeBackSide_r1 = new ModelRenderer(this);
        LeftRobeBackSide_r1.setRotationPoint(2.0F, -1.19F, -0.074F);
        LeftRobeBack.addChild(LeftRobeBackSide_r1);
        setRotateAngle(LeftRobeBackSide_r1, 0.1309F, 0.0F, 0.0F);
        LeftRobeBackSide_r1.cubeList.add(new ModelBox(LeftRobeBackSide_r1, 16, 32, -0.5F, -3.9653F, -0.303F, 1, 8, 1, 0.0F, true));
        LeftRobeBackSide_r1.cubeList.add(new ModelBox(LeftRobeBackSide_r1, 16, 32, 0.5F, -3.9653F, -0.303F, 1, 8, 1, 0.0F, true));
        LeftRobeBackSide_r1.cubeList.add(new ModelBox(LeftRobeBackSide_r1, 16, 32, 1.5F, -3.9653F, -0.303F, 1, 8, 1, 0.0F, true));
        LeftRobeBackSide_r1.cubeList.add(new ModelBox(LeftRobeBackSide_r1, 16, 32, 2.5F, -3.9653F, -0.303F, 1, 8, 1, 0.0F, true));
        LeftRobeBackSide_r1.cubeList.add(new ModelBox(LeftRobeBackSide_r1, 12, 32, 3.5F, -3.9653F, -0.303F, 1, 7, 1, 0.0F, true));

        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        RightArm.cubeList.add(new ModelBox(RightArm, 40, 16, -3.0F, -2.0F, -2.0F, 4, 10, 4, 1.0F, false));
        RightArm.cubeList.add(new ModelBox(RightArm, 21, 32, -3.0F, 3.0F, 2.7F, 4, 6, 1, 0.0F, false));
        RightArm.cubeList.add(new ModelBox(RightArm, 20, 44, -2.5F, 6.0F, 3.4F, 3, 3, 1, 0.0F, true));

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        LeftArm.cubeList.add(new ModelBox(LeftArm, 21, 32, -1.0F, 3.0F, 2.7F, 4, 6, 1, 0.0F, true));
        LeftArm.cubeList.add(new ModelBox(LeftArm, 20, 44, -0.5F, 6.0F, 3.3F, 3, 3, 1, 0.0F, true));
        LeftArm.cubeList.add(new ModelBox(LeftArm, 40, 16, -1.0F, -2.0F, -2.0F, 4, 10, 4, 1.0F, true));
        
        // Map custom parts to ModelBiped parts
        this.bipedBody = Body;
        this.bipedRightArm = RightArm;
        this.bipedLeftArm = LeftArm;

        RightLegBoots = new ModelRenderer(this);
        RightLegBoots.setRotationPoint(-1.9F, 12.0F, 0.0F);
        RightLegBoots.cubeList.add(new ModelBox(RightLegBoots, 0, 16, -2.0F, 6.0F, -2.0F, 4, 6, 4, 1.0F, false));

        LeftLegBoots = new ModelRenderer(this);
        LeftLegBoots.setRotationPoint(1.9F, 12.0F, 0.0F);
        LeftLegBoots.cubeList.add(new ModelBox(LeftLegBoots, 0, 16, -2.0F, 6.0F, -2.0F, 4, 6, 4, 1.0F, true));

        RightLegLeggings = new ModelRenderer(this);
        RightLegLeggings.setRotationPoint(-1.9F, 12.0F, 0.0F);
        RightLegLeggings.cubeList.add(new ModelBox(RightLegLeggings, 0, 40, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.5F, false));

        LeftLegLeggings = new ModelRenderer(this);
        LeftLegLeggings.setRotationPoint(1.9F, 12.0F, 0.0F);
        LeftLegLeggings.cubeList.add(new ModelBox(LeftLegLeggings, 0, 40, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.5F, true));
        
        // Map custom parts to ModelBiped parts
        this.bipedRightLeg = RightLegLeggings;
        this.bipedLeftLeg = LeftLegLeggings;
    }

    @Override
    public void render(@Nonnull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        // Map parts to ModelBiped parts first
        bipedHead = Head;
        bipedBody = Body;
        bipedRightArm = RightArm;
        bipedLeftArm = LeftArm;
        
        // Show/hide parts based on slot
        Head.showModel = slot == EntityEquipmentSlot.HEAD;
        Body.showModel = slot == EntityEquipmentSlot.CHEST;
        RightArm.showModel = slot == EntityEquipmentSlot.CHEST;
        LeftArm.showModel = slot == EntityEquipmentSlot.CHEST;
        RightLegLeggings.showModel = slot == EntityEquipmentSlot.LEGS;
        LeftLegLeggings.showModel = slot == EntityEquipmentSlot.LEGS;
        RightLegBoots.showModel = slot == EntityEquipmentSlot.FEET;
        LeftLegBoots.showModel = slot == EntityEquipmentSlot.FEET;
        
        // Disable headwear layer
        bipedHeadwear.showModel = false;
        
        if (slot == EntityEquipmentSlot.LEGS)
        {
            bipedRightLeg = RightLegLeggings;
            bipedLeftLeg = LeftLegLeggings;
        }
        else if (slot == EntityEquipmentSlot.FEET)
        {
            bipedRightLeg = RightLegBoots;
            bipedLeftLeg = LeftLegBoots;
        }

        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

}

