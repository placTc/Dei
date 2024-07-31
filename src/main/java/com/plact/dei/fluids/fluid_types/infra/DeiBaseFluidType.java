package com.plact.dei.fluids.fluid_types.infra;

import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.fluids.FluidType;

public class DeiBaseFluidType extends FluidType {
    protected IClientFluidTypeExtensions clientExtensions;

    public DeiBaseFluidType(Properties properties, IClientFluidTypeExtensions clientExtensions) {
        super(properties);
        this.clientExtensions = clientExtensions;
    }

    public void registerClientExtensions(RegisterClientExtensionsEvent e) {
        e.registerFluidType(clientExtensions, this);
    }
}
