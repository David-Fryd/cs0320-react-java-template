const cmd_prompt: HTMLElement | null = document.querySelector("#command-prompt");
const cmd_input: HTMLInputElement | null = <HTMLInputElement>document.getElementById("command-input");
const cmd_history: HTMLElement | null = document.querySelector("#command-history");

cmd_prompt?.addEventListener("submit", submitCallback);

function submitCallback(event : Event): void {
    event.preventDefault();
    if (cmd_input != null && cmd_history != null) {
        let command : string = cmd_input.value;
        console.log(command);
        cmd_input.value = "";
        cmd_history.innerHTML += `Command: ${command}` + "<br></br>";
    }
}

interface Handler {
    run(args : string[]): string;
}

class GetHandler implements Handler {
    run(args : string[]): string {
        if (args.length != 1) {
            return `ERROR: expected 1 argument, but got {args} instead`;
        }
        // load mocked data
        return "";
    }

}
class StatsHandler implements Handler {
    run(args: string[]): string {
        throw new Error("Method not implemented.");
    }

}
const KEYWORDS :Map<string, Handler> = new Map<string, Handler>([ 
    ["get", new GetHandler()],
    ["stats", new StatsHandler()]
]);

class REPL {
    if ()
}