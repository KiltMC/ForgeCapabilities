package xyz.bluspring.forgecapabilities.capabilities;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.Internal // Modders should use ICapabilityProvider, this is for Forge
public interface ICapabilityProviderImpl<B extends ICapabilityProviderImpl<B>> extends ICapabilityProvider
{
    default boolean areCapsCompatible(CapabilityProvider<B> other) {
        throw new IllegalStateException("mixin didn't touch this?");
    }
    
    default boolean areCapsCompatible(@Nullable CapabilityDispatcher other) {
        throw new IllegalStateException("mixin didn't touch this?");
    }

    default void invalidateCaps() {
        throw new IllegalStateException("mixin didn't touch this?");
    }

    default void reviveCaps() {
        throw new IllegalStateException("mixin didn't touch this?");
    }
}
