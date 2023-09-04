/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package xyz.bluspring.forgecapabilities.capabilities;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Inspired by {@link com.google.common.reflect.TypeToken TypeToken}, use a subclass to capture
 * generic types. Then uses CapabilityTokenSubclass a transformer
 * to convert that generic into a string returned by {@link #getType}
 * This allows us to know the generic type, without having a hard reference to the
 * class.
 *
 * Example usage:
 * <pre>{@code
 *    public static Capability<IDataHolder> DATA_HOLDER_CAPABILITY
 *    		= CapabilityManager.get(new CapabilityToken<>(){});
 * }</pre>
 *
 */
public abstract class CapabilityToken<T>
{
    private final TypeToken<T> typeToken = new TypeToken<>(this.getClass()) {};
    private final Type type = typeToken.getType();

    protected final String getType()
    {
        return this.type.getTypeName();
    }

    @Override
    public String toString()
    {
        return "CapabilityToken[" + getType() + "]";
    }
}
