package hell0hd.gateway.sound;

import hell0hd.gateway.Gateway;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class CustomSounds {
    private CustomSounds() {

    }

    public static final SoundEvent BLOCK_REINFORCED_DEEPSLATE_FRAME_FILL = registerSound("block.reinforced_deepslate_frame.fill");
    public static final SoundEvent BLOCK_GATEWAY_SPAWN = registerSound("block.gateway.spawn");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.fromNamespaceAndPath(Gateway.MOD_ID, id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }


    public static void initialize() {
        Gateway.LOGGER.info("sound");

    }
}
