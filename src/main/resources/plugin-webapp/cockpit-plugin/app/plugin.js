export default {
  id: "cockpit-plugin",
  pluginPoint: "cockpit.dashboard",
  priority: 12,
  render: (node, { api }) => {
    node.innerHTML = `<button class="btn btn-default action-button" style="width: 40px; margin-top: 5px">Retry</button>`

    node.onclick = function() {
      fetch(
          `${api.cockpitApi}/plugin/cockpit-plugin/${api.engine}/process-instance`
      ).then(async res => {
        alert(res["status"]);
      });
    }
  }
};
