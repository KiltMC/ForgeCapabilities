package xyz.bluspring.forgecapabilities.extensions.capabilities;

import io.github.fabricators_of_create.porting_lib.extensions.ItemStackExtensions;
import io.github.fabricators_of_create.porting_lib.util.LazyOptional;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.bluspring.forgecapabilities.capabilities.Capability;

public interface ItemStackCapabilityProviderImpl extends ICapabilityProviderImplWorkaround<ItemStack>, ItemStackExtensions {
    @Override
    default @NotNull <T> LazyOptional<T> getCapability(final @NotNull Capability<T> cap, final @Nullable Direction side) {
        throw new IllegalStateException("bruh");
    }

    @Override
    default void invalidateCaps() {
        ICapabilityProviderImplWorkaround.super.invalidateCaps();
    }
}
