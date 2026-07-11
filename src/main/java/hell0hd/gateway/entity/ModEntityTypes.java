package hell0hd.gateway.entity;


import hell0hd.gateway.Gateway;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.EyeOfEnder;

public class ModEntityTypes {
    public static final EntityType<EyeOfEnder> BLIND_EYE = register(
            "blind_eye",
            EntityType.Builder.<EyeOfEnder>of(EyeOfEnder::new, MobCategory.MISC)
                    .sized(0.25f,0.25f)
    );


    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Gateway.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void registerModEntityTypes() {
        Gateway.LOGGER.info("freaking entity 303 bro");
    }

    public static void registerAttributes() {
    }
}