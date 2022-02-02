package org.example.camunda.bpm.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.management.JobDefinition;
import org.example.camunda.bpm.CockpitPlugin;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Path("plugin/" + CockpitPlugin.ID)
@Component
public class CockpitPluginRootResource extends AbstractCockpitPluginRootResource {

  public CockpitPluginRootResource() {
    super(CockpitPlugin.ID);
  }

  @Path("{engineName}/process-instance")
  public ProcessInstanceResource getProcessInstanceResource(@PathParam("engineName") String engineName) throws IOException {

    ProcessEngine processEngine = ProcessEngines.getProcessEngine(engineName);

    ManagementService managementService = processEngine.getManagementService();

    List<JobDefinition> jobDefinitions = managementService.createJobDefinitionQuery().processDefinitionKey("some-process").list();

    for (JobDefinition jde : jobDefinitions) {
      managementService.setJobRetriesByJobDefinitionId(jde.getId(), 1);
    }

    return subResource(new ProcessInstanceResource(engineName), engineName);
  }
}
