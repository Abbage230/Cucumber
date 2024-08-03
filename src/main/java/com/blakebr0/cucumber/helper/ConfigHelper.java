package com.blakebr0.cucumber.helper;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class ConfigHelper {
    public static void load(ModConfigSpec config, String location) {
        var path = FMLPaths.CONFIGDIR.get().resolve(location);
        var data = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();

        data.load();
// TODO: 1.21 reevaluate if this is necessary
//        config.setConfig(data);
    }
}
