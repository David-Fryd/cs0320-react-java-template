"use strict";
const cmd_prompt = document.querySelector("#command-prompt");
const cmd_input = document.getElementById("command-input");
const cmd_history = document.querySelector("#command-history");
cmd_prompt === null || cmd_prompt === void 0 ? void 0 : cmd_prompt.addEventListener("submit", submitCallback);
function submitCallback(event) {
    event.preventDefault();
    if (cmd_input != null && cmd_history != null) {
        let command = cmd_input.value;
        console.log(command);
        cmd_input.value = "";
        cmd_history.innerHTML += `Command: ${command}` + "<br></br>";
    }
}
const KEYWORDS = ["get", "stats"];
class REPL {
}
class GetHandler {
    run(args) {
        if (args.length != 1) {
            return `ERROR: expected 1 argument, but got {args} instead`;
        }
        // load mocked data
        return "";
    }
}
class StatsHandler {
    run(args) {
        throw new Error("Method not implemented.");
    }
}
function getHandler(command) {
}
function statsHandler(command) {
}
