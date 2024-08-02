package com.plact.dei;

import com.plact.dei.blocks.DeiBlocks;
import com.plact.dei.blocks.block_entities.separator.SeparatorBlockEntityRenderer;
import com.plact.dei.blocks.block_entities.DeiBlockEntityTypes;
import com.plact.dei.client.DeiModelLayers;
import com.plact.dei.creative_tabs.DeiCreativeTabs;
import com.plact.dei.fluids.DeiFluids;
import com.plact.dei.items.DeiItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(DeiMod.MODID)
public class DeiMod
{
    public static final String MODID = "dei";
    private static final Logger LOGGER = LogUtils.getLogger();

    // REGISTERS
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MODID);

    // DAMAGE TYPES
    public static final ResourceKey<DamageType> SYRINGE_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(DeiMod.MODID, "syringe"));

    // TAGS

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public DeiMod(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        DeiFluids.register(modEventBus);
        DeiBlocks.register(modEventBus);
        DeiItems.register(modEventBus);
        DeiCreativeTabs.register(modEventBus);
        DeiBlockEntityTypes.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(DeiFluids.BLOOD_FLUID.getSourceFluid(), RenderType.solid());
            ItemBlockRenderTypes.setRenderLayer(DeiFluids.BLOOD_FLUID.getFlowingFluid(), RenderType.solid());
            ItemBlockRenderTypes.setRenderLayer(DeiFluids.ICHOR_FLUID.getSourceFluid(), RenderType.solid());
            ItemBlockRenderTypes.setRenderLayer(DeiFluids.ICHOR_FLUID.getFlowingFluid(), RenderType.solid());
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(DeiBlockEntityTypes.SEPARATOR_BLOCK_ENTITY_TYPE.get(), SeparatorBlockEntityRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(DeiModelLayers.SEPARATOR, SeparatorBlockEntityRenderer::createBodyLayer);
        }

        @SubscribeEvent
        static void registerRegistries(NewRegistryEvent event) {}

        @SubscribeEvent
        static void registerClientExtensions(RegisterClientExtensionsEvent event) {
            DeiFluids.registerClientFluidExtensions(event);
        }
    }
}
