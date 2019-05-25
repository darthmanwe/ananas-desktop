package org.ananas.runner.steprunner;

import org.ananas.runner.kernel.ConnectorStepRunner;
import org.ananas.runner.kernel.model.Step;
import org.ananas.runner.kernel.model.StepType;
import org.ananas.runner.model.steps.files.csv.BeamTextCSVCustomTable;
import org.ananas.runner.model.steps.files.csv.CSVPaginator;
import org.ananas.runner.model.steps.files.csv.CSVStepConfig;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.commons.csv.CSVFormat;

public class CSVConnector extends ConnectorStepRunner {

  public CSVConnector(Pipeline pipeline, Step step, boolean doSampling, boolean isTest) {
    super(pipeline, step, doSampling, isTest);
  }

  public void build() {
    CSVStepConfig config = new CSVStepConfig(StepType.Connector, step.config);

    CSVFormat format =
        CSVFormat.DEFAULT
            .withDelimiter(config.delimiter)
            .withRecordSeparator(config.recordSeparator);
    CSVPaginator csvPaginator = new CSVPaginator(stepId, config);
    Schema inputschema = csvPaginator.getSchema();
    this.stepId = stepId;

    this.output =
        new BeamTextCSVCustomTable(
                this.errors, inputschema, config.url, format, config.hasHeader, doSampling, isTest)
            .buildIOReader(pipeline);
    this.output.setRowSchema(inputschema);
  }
}
