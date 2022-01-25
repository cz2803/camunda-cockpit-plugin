package org.example.camunda.bpm.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import okhttp3.*;
import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource;

import org.example.camunda.bpm.CockpitPlugin;

import java.io.IOException;

@Path("plugin/" + CockpitPlugin.ID)
public class CockpitPluginRootResource extends AbstractCockpitPluginRootResource {

  public CockpitPluginRootResource() {
    super(CockpitPlugin.ID);
  }

  @Path("{engineName}/process-instance")
  public ProcessInstanceResource getProcessInstanceResource(@PathParam("engineName") String engineName) throws IOException {
    //Camunda Rest API Call is done here
    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, "{\r\n    \"processInstanceQuery\": {\r\n        \"processDefinitionIds\": [\r\n            \"test-process\"\r\n        ]\r\n    },\r\n    \"retries\": 1\r\n}");
    Request request = new Request.Builder()
            .url("http://localhost:8080/engine-rest/process-instance/job-retries")
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .build();
    Response response = client.newCall(request).execute();
    return subResource(new ProcessInstanceResource(engineName), engineName);
  }
}
