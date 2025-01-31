package com.plact.dei.fluids.fluid_types;

import com.plact.dei.fluids.fluid_types.infra.DeiBaseFluidType;
import com.plact.dei.fluids.fluid_types.infra.DeiClientFluidTypeExtensions;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidType;
import org.joml.Vector3f;

public class BloodFluidType extends DeiBaseFluidType {
    public BloodFluidType() {
        super(
                FluidType.Properties.create().density(20).viscosity(20),
                new DeiClientFluidTypeExtensions(
                        ResourceLocation.parse("dei:block/blood_still"),
                        ResourceLocation.parse("dei:block/blood_flow"),
                        ResourceLocation.parse("dei:textures/misc/in_blood.png"),
                        0xFFFFFFF,
                        new Vector3f(40f / 255, 0f / 255, 0f / 255)
                )
        );
    }
}
