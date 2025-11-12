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
 * Converted from Blockbench export to work with ModelBiped
 */
public class ModelWeaveleafArmor extends ModelArmor
{
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer RightLegLeggings;
    private final ModelRenderer RightRobeSide;
    private final ModelRenderer RightRobeSideFront_r1;
    private final ModelRenderer RightRobeSideCenter_r1;
    private final ModelRenderer RightRobeSideCenter_r2;
    private final ModelRenderer RightRobeSideCenter_r3;
    private final ModelRenderer RightRobeSideCenter_r4;
    private final ModelRenderer RightRobeSideFront_r2;
    private final ModelRenderer RightRobeBack;
    private final ModelRenderer RightRobeBackSide_r1;
    private final ModelRenderer RightRobeBackSide_r2;
    private final ModelRenderer RightRobeRope;
    private final ModelRenderer LeftLegLeggings;
    private final ModelRenderer LeftRobeSide;
    private final ModelRenderer LeftRobeSideFront_r1;
    private final ModelRenderer LeftRobeSideCenter_r1;
    private final ModelRenderer LeftRobeSideCenter_r2;
    private final ModelRenderer LeftRobeSideCenter_r3;
    private final ModelRenderer LeftRobeSideCenter_r4;
    private final ModelRenderer LeftRobeSideFront_r2;
    private final ModelRenderer LeftRobeBack;
    private final ModelRenderer LeftRobeBackSide_r1;
    private final ModelRenderer LeftRobeBackSide_r2;
    private final ModelRenderer LeftRobeRope;
    private final ModelRenderer RightLegBoots;
    private final ModelRenderer LeftLegBoots;

    public ModelWeaveleafArmor(EntityEquipmentSlot slot)
    {
        super(slot);
        textureWidth = 64;
        textureHeight = 64;

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        Head.cubeList.add(new ModelBox(Head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 1.0F, false));
        Head.cubeList.add(new ModelBox(Head, 31, 32, -4.0F, -8.0F, 5.0F, 8, 8, 1, 0.0F, true));
        this.bipedHead = Head;
        this.bipedHeadwear.showModel = false;

        Body = new ModelRenderer(this);
        Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        Body.cubeList.add(new ModelBox(Body, 16, 16, -4.0F, 0.0F, -2.0F, 8, 11, 4, 1.01F, false));
        this.bipedBody = Body;

        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        RightArm.cubeList.add(new ModelBox(RightArm, 40, 16, -3.0F, -2.0F, -2.0F, 4, 10, 4, 1.0F, false));
        RightArm.cubeList.add(new ModelBox(RightArm, 21, 32, -3.0F, 3.0F, 2.7F, 4, 6, 1, 0.0F, false));
        RightArm.cubeList.add(new ModelBox(RightArm, 20, 44, -2.5F, 6.0F, 3.4F, 3, 3, 1, 0.0F, true));
        this.bipedRightArm = RightArm;

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        LeftArm.cubeList.add(new ModelBox(LeftArm, 21, 32, -1.0F, 3.0F, 2.7F, 4, 6, 1, 0.0F, true));
        LeftArm.cubeList.add(new ModelBox(LeftArm, 20, 44, -0.5F, 6.0F, 3.3F, 3, 3, 1, 0.0F, true));
        LeftArm.cubeList.add(new ModelBox(LeftArm, 40, 16, -1.0F, -2.0F, -2.0F, 4, 10, 4, 1.0F, true));
        this.bipedLeftArm = LeftArm;

        RightLegBoots = new ModelRenderer(this);
        RightLegBoots.setRotationPoint(-1.9F, 12.0F, 0.0F);
        RightLegBoots.cubeList.add(new ModelBox(RightLegBoots, 0, 16, -2.0F, 6.0F, -2.0F, 4, 6, 4, 1.0F, false));

        LeftLegBoots = new ModelRenderer(this);
        LeftLegBoots.setRotationPoint(1.9F, 12.0F, 0.0F);
        LeftLegBoots.cubeList.add(new ModelBox(LeftLegBoots, 0, 16, -2.0F, 6.0F, -2.0F, 4, 6, 4, 1.0F, true));

        RightLegLeggings = new ModelRenderer(this);
        RightLegLeggings.setRotationPoint(-1.9F, 12.0F, 0.0F);

        RightRobeSide = new ModelRenderer(this);
        RightRobeSide.setRotationPoint(-3.3945F, 2.3493F, 0.0F);
        RightLegLeggings.addChild(RightRobeSide);
        setRotationAngle(RightRobeSide, 0.0F, 0.0F, 0.0436F);

        RightRobeSideFront_r1 = new ModelRenderer(this);
        RightRobeSideFront_r1.setRotationPoint(-0.0109F, 0.0826F, 2.5F);
        RightRobeSide.addChild(RightRobeSideFront_r1);
        setRotationAngle(RightRobeSideFront_r1, 0.0F, -1.5708F, 0.1309F);
        RightRobeSideFront_r1.cubeList.add(new ModelBox(RightRobeSideFront_r1, 8, 32, -0.5F, -4.5F, -0.5F, 1, 9, 1, 0.0F, false));

        RightRobeSideCenter_r1 = new ModelRenderer(this);
        RightRobeSideCenter_r1.setRotationPoint(-0.0109F, 0.0826F, -1.5F);
        RightRobeSide.addChild(RightRobeSideCenter_r1);
        setRotationAngle(RightRobeSideCenter_r1, 0.0F, -1.5708F, 0.1309F);
        RightRobeSideCenter_r1.cubeList.add(new ModelBox(RightRobeSideCenter_r1, 4, 32, -0.5F, -4.5F, -0.5F, 1, 9, 1, 0.0F, false));

        RightRobeSideCenter_r2 = new ModelRenderer(this);
        RightRobeSideCenter_r2.setRotationPoint(-0.0109F, 0.0826F, -0.5F);
        RightRobeSide.addChild(RightRobeSideCenter_r2);
        setRotationAngle(RightRobeSideCenter_r2, 0.0F, -1.5708F, 0.1309F);
        RightRobeSideCenter_r2.cubeList.add(new ModelBox(RightRobeSideCenter_r2, 4, 32, -0.5F, -4.5F, -0.5F, 1, 9, 1, 0.0F, false));

        RightRobeSideCenter_r3 = new ModelRenderer(this);
        RightRobeSideCenter_r3.setRotationPoint(-0.0109F, 0.0826F, 0.5F);
        RightRobeSide.addChild(RightRobeSideCenter_r3);
        setRotationAngle(RightRobeSideCenter_r3, 0.0F, -1.5708F, 0.1309F);
        RightRobeSideCenter_r3.cubeList.add(new ModelBox(RightRobeSideCenter_r3, 4, 32, -0.5F, -4.5F, -0.5F, 1, 9, 1, 0.0F, false));

        RightRobeSideCenter_r4 = new ModelRenderer(this);
        RightRobeSideCenter_r4.setRotationPoint(-0.0109F, 0.0826F, 1.5F);
        RightRobeSide.addChild(RightRobeSideCenter_r4);
        setRotationAngle(RightRobeSideCenter_r4, 0.0F, -1.5708F, 0.1309F);
        RightRobeSideCenter_r4.cubeList.add(new ModelBox(RightRobeSideCenter_r4, 4, 32, -0.5F, -4.5F, -0.5F, 1, 9, 1, 0.0F, false));

        RightRobeSideFront_r2 = new ModelRenderer(this);
        RightRobeSideFront_r2.setRotationPoint(0.0544F, -0.4131F, -2.5F);
        RightRobeSide.addChild(RightRobeSideFront_r2);
        setRotationAngle(RightRobeSideFront_r2, 0.0F, -1.5708F, 0.1309F);
        RightRobeSideFront_r2.cubeList.add(new ModelBox(RightRobeSideFront_r2, 0, 32, -0.5F, -4.0F, -0.5F, 1, 8, 1, 0.0F, false));

        RightRobeBack = new ModelRenderer(this);
        RightRobeBack.setRotationPoint(-1.35F, 2.6716F, 3.3657F);
        RightLegLeggings.addChild(RightRobeBack);
        setRotationAngle(RightRobeBack, 0.0436F, 0.0F, 0.0F);

        RightRobeBackSide_r1 = new ModelRenderer(this);
        RightRobeBackSide_r1.setRotationPoint(1.25F, 0.2479F, 0.0326F);
        RightRobeBack.addChild(RightRobeBackSide_r1);
        setRotationAngle(RightRobeBackSide_r1, 0.1309F, 0.0F, 0.0F);
        RightRobeBackSide_r1.cubeList.add(new ModelBox(RightRobeBackSide_r1, 40, 44, -2.0F, -5.0F, -0.5F, 4, 10, 1, 0.0F, true));

        RightRobeBackSide_r2 = new ModelRenderer(this);
        RightRobeBackSide_r2.setRotationPoint(-1.25F, -0.2479F, -0.0326F);
        RightRobeBack.addChild(RightRobeBackSide_r2);
        setRotationAngle(RightRobeBackSide_r2, 0.1309F, 0.0F, 0.0F);
        RightRobeBackSide_r2.cubeList.add(new ModelBox(RightRobeBackSide_r2, 12, 32, -0.5F, -4.5F, -0.5F, 1, 9, 1, 0.0F, false));

        RightRobeRope = new ModelRenderer(this);
        RightRobeRope.setRotationPoint(-0.04F, -1.4211F, 1.6F);
        RightLegLeggings.addChild(RightRobeRope);
        setRotationAngle(RightRobeRope, 0.0F, 1.5708F, 0.0F);
        RightRobeRope.cubeList.add(new ModelBox(RightRobeRope, 14, 58, -1.0F, -0.5F, -2.36F, 2, 1, 1, 0.0F, false));
        RightRobeRope.cubeList.add(new ModelBox(RightRobeRope, 14, 58, -1.0F, -0.5F, 0.64F, 2, 1, 1, 0.0F, false));
        RightRobeRope.cubeList.add(new ModelBox(RightRobeRope, 14, 58, -1.0F, -0.5F, 0.94F, 2, 1, 1, 0.0F, false));
        RightRobeRope.cubeList.add(new ModelBox(RightRobeRope, 14, 58, -1.0F, -0.5F, -0.36F, 2, 1, 1, 0.0F, false));
        RightRobeRope.cubeList.add(new ModelBox(RightRobeRope, 14, 58, -1.0F, -0.5F, -1.36F, 2, 1, 1, 0.0F, false));

        LeftLegLeggings = new ModelRenderer(this);
        LeftLegLeggings.setRotationPoint(1.9F, 12.0F, 0.0F);

        LeftRobeSide = new ModelRenderer(this);
        LeftRobeSide.setRotationPoint(3.3945F, 2.3493F, 0.0F);
        LeftLegLeggings.addChild(LeftRobeSide);
        setRotationAngle(LeftRobeSide, 0.0F, 0.0F, -0.0436F);

        LeftRobeSideFront_r1 = new ModelRenderer(this);
        LeftRobeSideFront_r1.setRotationPoint(0.0109F, 0.0826F, 2.5F);
        LeftRobeSide.addChild(LeftRobeSideFront_r1);
        setRotationAngle(LeftRobeSideFront_r1, 0.0F, 1.5708F, -0.1309F);
        LeftRobeSideFront_r1.cubeList.add(new ModelBox(LeftRobeSideFront_r1, 8, 32, -0.5F, -4.5F, -0.5F, 1, 9, 1, 0.0F, true));

        LeftRobeSideCenter_r1 = new ModelRenderer(this);
        LeftRobeSideCenter_r1.setRotationPoint(0.0109F, 0.0826F, -1.5F);
        LeftRobeSide.addChild(LeftRobeSideCenter_r1);
        setRotationAngle(LeftRobeSideCenter_r1, 0.0F, 1.5708F, -0.1309F);
        LeftRobeSideCenter_r1.cubeList.add(new ModelBox(LeftRobeSideCenter_r1, 4, 32, -0.5F, -4.5F, -0.5F, 1, 9, 1, 0.0F, true));

        LeftRobeSideCenter_r2 = new ModelRenderer(this);
        LeftRobeSideCenter_r2.setRotationPoint(0.0109F, 0.0826F, -0.5F);
        LeftRobeSide.addChild(LeftRobeSideCenter_r2);
        setRotationAngle(LeftRobeSideCenter_r2, 0.0F, 1.5708F, -0.1309F);
        LeftRobeSideCenter_r2.cubeList.add(new ModelBox(LeftRobeSideCenter_r2, 4, 32, -0.5F, -4.5F, -0.5F, 1, 9, 1, 0.0F, true));

        LeftRobeSideCenter_r3 = new ModelRenderer(this);
        LeftRobeSideCenter_r3.setRotationPoint(0.0109F, 0.0826F, 0.5F);
        LeftRobeSide.addChild(LeftRobeSideCenter_r3);
        setRotationAngle(LeftRobeSideCenter_r3, 0.0F, 1.5708F, -0.1309F);
        LeftRobeSideCenter_r3.cubeList.add(new ModelBox(LeftRobeSideCenter_r3, 4, 32, -0.5F, -4.5F, -0.5F, 1, 9, 1, 0.0F, true));

        LeftRobeSideCenter_r4 = new ModelRenderer(this);
        LeftRobeSideCenter_r4.setRotationPoint(0.0109F, 0.0826F, 1.5F);
        LeftRobeSide.addChild(LeftRobeSideCenter_r4);
        setRotationAngle(LeftRobeSideCenter_r4, 0.0F, 1.5708F, -0.1309F);
        LeftRobeSideCenter_r4.cubeList.add(new ModelBox(LeftRobeSideCenter_r4, 4, 32, -0.5F, -4.5F, -0.5F, 1, 9, 1, 0.0F, true));

        LeftRobeSideFront_r2 = new ModelRenderer(this);
        LeftRobeSideFront_r2.setRotationPoint(-0.0544F, -0.4131F, -2.5F);
        LeftRobeSide.addChild(LeftRobeSideFront_r2);
        setRotationAngle(LeftRobeSideFront_r2, 0.0F, 1.5708F, -0.1309F);
        LeftRobeSideFront_r2.cubeList.add(new ModelBox(LeftRobeSideFront_r2, 0, 32, -0.5F, -4.0F, -0.5F, 1, 8, 1, 0.0F, true));

        LeftRobeBack = new ModelRenderer(this);
        LeftRobeBack.setRotationPoint(1.35F, 2.6716F, 3.3657F);
        LeftLegLeggings.addChild(LeftRobeBack);
        setRotationAngle(LeftRobeBack, 0.0436F, 0.0F, 0.0F);

        LeftRobeBackSide_r1 = new ModelRenderer(this);
        LeftRobeBackSide_r1.setRotationPoint(-1.25F, 0.2479F, 0.0326F);
        LeftRobeBack.addChild(LeftRobeBackSide_r1);
        setRotationAngle(LeftRobeBackSide_r1, 0.1309F, 0.0F, 0.0F);
        LeftRobeBackSide_r1.cubeList.add(new ModelBox(LeftRobeBackSide_r1, 29, 44, -2.0F, -5.0F, -0.5F, 4, 10, 1, 0.0F, true));

        LeftRobeBackSide_r2 = new ModelRenderer(this);
        LeftRobeBackSide_r2.setRotationPoint(1.25F, -0.2479F, -0.0326F);
        LeftRobeBack.addChild(LeftRobeBackSide_r2);
        setRotationAngle(LeftRobeBackSide_r2, 0.1309F, 0.0F, 0.0F);
        LeftRobeBackSide_r2.cubeList.add(new ModelBox(LeftRobeBackSide_r2, 12, 32, -0.5F, -4.5F, -0.5F, 1, 9, 1, 0.0F, true));

        LeftRobeRope = new ModelRenderer(this);
        LeftRobeRope.setRotationPoint(0.04F, -1.4211F, 1.6F);
        LeftLegLeggings.addChild(LeftRobeRope);
        setRotationAngle(LeftRobeRope, 0.0F, 1.5708F, 0.0F);
        LeftRobeRope.cubeList.add(new ModelBox(LeftRobeRope, 14, 58, -1.0F, -0.5F, 1.36F, 2, 1, 1, 0.0F, false));
        LeftRobeRope.cubeList.add(new ModelBox(LeftRobeRope, 14, 58, -1.0F, -0.5F, 0.36F, 2, 1, 1, 0.0F, false));
        LeftRobeRope.cubeList.add(new ModelBox(LeftRobeRope, 14, 58, -1.0F, -0.5F, -1.64F, 2, 1, 1, 0.0F, false));
        LeftRobeRope.cubeList.add(new ModelBox(LeftRobeRope, 14, 58, -1.0F, -0.5F, -1.94F, 2, 1, 1, 0.0F, false));
        LeftRobeRope.cubeList.add(new ModelBox(LeftRobeRope, 14, 58, -1.0F, -0.5F, -0.64F, 2, 1, 1, 0.0F, false));

        // Map legs based on slot
        if (slot == EntityEquipmentSlot.LEGS)
        {
            this.bipedRightLeg = RightLegLeggings;
            this.bipedLeftLeg = LeftLegLeggings;
        }
        else if (slot == EntityEquipmentSlot.FEET)
        {
            this.bipedRightLeg = RightLegBoots;
            this.bipedLeftLeg = LeftLegBoots;
        }
    }

    @Override
    public void render(@Nonnull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
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

        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }
}
