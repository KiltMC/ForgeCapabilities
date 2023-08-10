package xyz.bluspring.forgecapabilities.capabilities.impl;

import io.github.fabricators_of_create.porting_lib.transfer.fluid.item.FluidBucketWrapper;
import io.github.fabricators_of_create.porting_lib.util.LazyOptional;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.bluspring.forgecapabilities.capabilities.Capability;
import xyz.bluspring.forgecapabilities.capabilities.ForgeCapabilities;
import xyz.bluspring.forgecapabilities.capabilities.ICapabilityProvider;

public class FluidBucketWrapperWithCapability extends FluidBucketWrapper implements ICapabilityProvider {
    private final LazyOptional<SingleSlotStorage<FluidVariant>> holder = LazyOptional.of(() -> this);

    public FluidBucketWrapperWithCapability(@NotNull ContainerItemContext context) {
        super(context);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return ForgeCapabilities.FLUID_HANDLER_ITEM.orEmpty(cap, this.holder);
    }
}
