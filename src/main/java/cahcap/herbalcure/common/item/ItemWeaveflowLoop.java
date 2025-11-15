package cahcap.herbalcure.common.item;

import cahcap.herbalcure.HerbalCure;
import net.minecraft.item.Item;

/**
 * Weaveflow Loop
 * Equivalent to a wrench in this mod, can be used for casting spells,
 * starting synthesis, starting alchemy, starting enchanting, binding Sylvana, etc.
 * Shift right-click to switch between spell state and work state.
 * Default state after crafting is work state.
 */
public class ItemWeaveflowLoop extends Item
{
    public ItemWeaveflowLoop()
    {
        setUnlocalizedName("weaveflow_loop");
        setRegistryName("weaveflow_loop");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
    }
}

