package org.ananas.runner.model.steps.files.utils;

import java.util.Map;
import org.ananas.runner.kernel.model.StepType;
import org.ananas.runner.model.core.StepConfig;
import org.ananas.runner.model.steps.files.FileLoader;

public class StepFileConfigToUrl {

  public static String url(
      StepType type, Map<String, Object> config, FileLoader.SupportedFormat f) {
    return type == StepType.Connector ? (String) config.get(StepConfig.PATH) : url(config, f);
  }

  public static String url(Map<String, Object> config, FileLoader.SupportedFormat f) {
    return String.format(
        "%s/%s-%s-of-%s.%s",
        config.get(StepConfig.PATH),
        config.get(StepConfig.PREFIX),
        "00000",
        "00001",
        f.name().toLowerCase());
  }
}
