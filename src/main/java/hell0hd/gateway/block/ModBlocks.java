package hell0hd.gateway.block;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.block.custom.ReinforcedDeepslateFrameBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.function.Function;

public class ModBlocks {

    public static final Block REINFORCED_DEEPSLATE_FRAME = registerBlock("reinforced_deepslate_frame",
            properties -> new ReinforcedDeepslateFrameBlock(properties.instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.GLASS).lightLevel((statex) -> 1).strength(-1.0F, 3600000.0F).noLootTable()));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Gateway.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Gateway.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Gateway.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Gateway.MOD_ID, name)))));
    }


    public static void registerModBlocks() {
        Gateway.LOGGER.info("i am placing blocks and shit cus i'm in fucking minecraft");
    }
}
