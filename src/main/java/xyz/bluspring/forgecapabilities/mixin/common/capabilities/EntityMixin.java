package xyz.bluspring.forgecapabilities.mixin.common.capabilities;

import io.github.fabricators_of_create.porting_lib.extensions.EntityExtensions;
import io.github.fabricators_of_create.porting_lib.util.LazyOptional;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import xyz.bluspring.forgecapabilities.capabilities.*;
import xyz.bluspring.forgecapabilities.extensions.capabilities.CapabilityProviderExtension;
import xyz.bluspring.forgecapabilities.extensions.capabilities.EntityCapabilityProviderImpl;

import java.util.function.Supplier;

@Mixin(Entity.class)
public abstract class EntityMixin implements CapabilityProviderExtension, EntityCapabilityProviderImpl, EntityExtensions {
	private final CapabilityProviderWorkaround<Entity> workaround = new CapabilityProviderWorkaround<>(Entity.class, (Entity) (Object) this);

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return workaround.getCapability(cap, side);
	}

	@Override
	public @Nullable CapabilityDispatcher getCapabilities() {
		return workaround.invokeGetCapabilities();
	}

	@Override
	public CapabilityProviderWorkaround<Entity> port_lib$getWorkaround() {
		return workaround;
	}

	@Override
	public boolean areCapsCompatible(CapabilityProvider<Entity> other) {
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
