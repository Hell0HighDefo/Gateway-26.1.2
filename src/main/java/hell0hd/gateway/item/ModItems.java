package hell0hd.gateway.item;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.block.ModBlocks;
import hell0hd.gateway.item.custom.BlindEyeItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {
    public static final Item BLIND_EYE = registerItem("blind_eye", BlindEyeItem::new);




    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Gateway.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Gateway.MOD_ID, name)))));
    }


    public static void registerModItems() {
        Gateway.LOGGER.info("fuckin items bro");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {
            output.accept(BLIND_EYE);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(output -> {
            output.accept(ModBlocks.REINFORCED_DEEPSLATE_FRAME);
        });
    }
}
