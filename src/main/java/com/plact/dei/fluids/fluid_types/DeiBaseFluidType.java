package com.plact.dei.fluids.fluid_types;

import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;

public class DeiBaseFluidType extends FluidType {
    protected IClientFluidTypeExtensions clientExtensions;

    public DeiBaseFluidType(Properties properties, IClientFluidTypeExtensions clientExtensions) {
        super(properties);
        this.clientExtensions = clientExtensions;
    }

    public IClientFluidTypeExtensions getClientExtensions() {
        return clientExtensions;
    }
}
