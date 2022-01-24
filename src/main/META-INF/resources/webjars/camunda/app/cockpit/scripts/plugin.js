export default {
    id: "myDemoPlugin",
    pluginPoint: "cockpit.dashboard",
    render: (node, { api }) => {
        node.innerHTML = `<button class="btn btn-default action-button" style="width: 40px; margin-top: 5px"> test </button>`;
        node.onclick = function() {
          //here a call to the Camunda REST API should be made
        };
    }
}