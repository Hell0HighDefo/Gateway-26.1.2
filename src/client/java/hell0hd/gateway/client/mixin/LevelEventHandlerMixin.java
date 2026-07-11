package hell0hd.gateway.client.mixin;

import hell0hd.gateway.sound.CustomSounds;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelEventHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelEventHandler.class)
public class LevelEventHandlerMixin {
	@Shadow
	@Final
	private Minecraft minecraft;

	@Shadow
	@Final
	private ClientLevel level;

	@Inject(at = @At("HEAD"), method = "globalLevelEvent")
	public void Gateway$gatewaySpawnSound(int type, BlockPos pos, int data, CallbackInfo ci) {
        if (type == -1038) {
            Camera camera = this.minecraft.gameRenderer.getMainCamera();
            if (camera.isInitialized()) {
                Vec3 directionToEvent = Vec3.atCenterOf(pos).subtract(camera.position()).normalize();
                Vec3 soundPos = camera.position().add(directionToEvent.scale((double) 2.0F));
                this.level.playLocalSound(soundPos.x, soundPos.y, soundPos.z, CustomSounds.BLOCK_GATEWAY_SPAWN, SoundSource.HOSTILE, 1.0F, 1.0F, false);
            }
        }
    }
}