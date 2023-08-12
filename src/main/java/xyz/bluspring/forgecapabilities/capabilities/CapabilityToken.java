/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package xyz.bluspring.forgecapabilities.capabilities;

import org.objectweb.asm.Type;

import java.lang.reflect.ParameterizedType;
import java.util.UUID;

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
    private String typeName;

    protected final String getType()
    {
        if (typeName != null)
            return typeName;

		// Gets the type name of the generic.
        var type = (this.getClass().getGenericSuperclass());

        try {
            if (type instanceof ParameterizedType paramType)
                typeName = Type.getInternalName((Class<T>) paramType.getActualTypeArguments()[0]);
            else if (type instanceof Class<?> clazz)
                typeName = Type.getInternalName(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        typeName = "unknown_type_" + UUID.randomUUID().toString().replace("-", "");

        return typeName;
    }

    @Override
    public String toString()
    {
        return "CapabilityToken[" + getType() + "]";
    }
}
