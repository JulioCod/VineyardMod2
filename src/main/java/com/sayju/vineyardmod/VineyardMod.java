
package com.sayju.vineyardmod;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(VineyardMod.MODID)
public class VineyardMod {
    public static final String MODID = "vineyardmod";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public VineyardMod() {
        LOGGER.debug("Hello from Example Mod!");

    }
}
