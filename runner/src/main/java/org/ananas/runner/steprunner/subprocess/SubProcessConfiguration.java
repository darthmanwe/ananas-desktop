package org.ananas.runner.steprunner.subprocess;

import java.io.Serializable;
import java.util.Map;
import org.ananas.runner.misc.StepConfigHelper;

/**
 * Configuration file used to setup the Process kernel for execution of the external library Values
 * are copied from the Options to all them to be Serializable.
 */
@SuppressWarnings("serial")
public class SubProcessConfiguration implements Serializable {

  protected static final String SOURCE_FILE = "source-file";
  protected static final String BINARY_NAME = "binary";
  protected static final String SOURCE_PATH = "source-path";
  protected static final String WORKER_PATH = "worker-path";
  protected static final String WAIT_TIME = "wait-time";
  protected static final String CONCURRENCY = "concurrency";
  protected static final String BATCH_SIZE = "batch-size";
  public static final String AVRO_SCHEMA = "avro-schema";

  public SubProcessConfiguration(Map<String, Object> config) {
    this.binaryName = (String) config.getOrDefault(BINARY_NAME, "");
    this.executableName = (String) config.getOrDefault(SOURCE_FILE, "");
    this.sourcePath = (String) config.getOrDefault(SOURCE_PATH, "/");
    this.workerPath = (String) config.getOrDefault(WORKER_PATH, "");
    this.waitTime = Integer.valueOf(StepConfigHelper.getConfig(config, WAIT_TIME, "1"));
    this.concurrency = Integer.valueOf(StepConfigHelper.getConfig(config, CONCURRENCY, "1"));
    this.batchSize = Integer.valueOf(StepConfigHelper.getConfig(config, BATCH_SIZE, "1000"));
    this.avroSchema = (String) config.getOrDefault(AVRO_SCHEMA, "");
  }

  // Source binary name
  public String binaryName;

  // Source binary name
  public String executableName;

  // Source directory where the library is located
  public String sourcePath;

  // Working directory for the process I/O
  public String workerPath;

  // The maximum time to wait for the sub-process to complete
  public Integer waitTime;

  // "As sub-processes can be heavy weight match the concurrency level to num cores on the machines"
  public Integer concurrency;

  // "As sub-processes can be heavy weight match the desired batch size buffered to each worker"
  public Integer batchSize;

  // Output Avro Schema definition
  public String avroSchema;

  public String getSourcePath() {
    return sourcePath;
  }

  public void setSourcePath(String sourcePath) {
    this.sourcePath = sourcePath;
  }

  public String getWorkerPath() {
    return workerPath;
  }

  public void setWorkerPath(String workerPath) {
    this.workerPath = workerPath;
  }

  public Integer getWaitTime() {
    return waitTime;
  }

  public void setWaitTime(Integer waitTime) {
    this.waitTime = waitTime;
  }

  public Integer getConcurrency() {
    return concurrency;
  }

  public void setConcurrency(Integer concurrency) {
    this.concurrency = concurrency;
  }
}