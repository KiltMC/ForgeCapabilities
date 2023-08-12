package xyz.bluspring.forgecapabilities.mixin.common.capabilities;

import io.github.fabricators_of_create.porting_lib.extensions.BlockEntityExtensions;
import io.github.fabricators_of_create.porting_lib.util.LazyOptional;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import xyz.bluspring.forgecapabilities.capabilities.*;
import xyz.bluspring.forgecapabilities.extensions.capabilities.BlockEntityCapabilityProviderImpl;
import xyz.bluspring.forgecapabilities.extensions.capabilities.CapabilityProviderExtension;

import java.util.function.Supplier;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin implements CapabilityProviderExtension, BlockEntityCapabilityProviderImpl, BlockEntityExtensions {
	private final CapabilityProviderWorkaround<BlockEntity> workaround = new CapabilityProviderWorkaround<>(BlockEntity.class, (BlockEntity) (Object) this);

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return workaround.getCapability(cap, side);
	}

	@Override
	public @Nullable CapabilityDispatcher getCapabilities() {
		return workaround.invokeGetCapabilities();
	}

	@Override
	public CapabilityProviderWorkaround<BlockEntity> port_lib$getWorkaround() {
		return workaround;
	}

	@Override
	public boolean areCapsCompatible(CapabilityProvider<BlockEntity> other) {
		return workaround.areCapsCompatible(other);
	}

	@Override
	public boolean areCapsCompatible(@Nullable CapabilityDispatcher other) {
		return workaround.areCapsCompatible(other);
	}

	@Override
	public void invalidateCaps() {
		workaround.invalidateCaps();
	}

	@Override
	public void reviveCaps() {
		workaround.reviveCaps();
	}

	@Override
	public void gatherCapabilities(@Nullable Supplier<ICapabilityProvider> parent) {
		workaround.invokeGatherCapabilities(parent);
	}
}
