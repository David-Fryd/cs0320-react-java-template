# Simple Java Backend + React Frontend Project

A simple project demonstrating an implementation of a project using a Java API backend with a React frontend.

The demo application stores a set of strings on the server, and allows the user to view/add to the set of strings from the frontend application.

## How to run:

The backend and frontend applications are two seperate entities, and must each be run seperately.

In order to run the backend: Navigate to the `backend/` directory, and run: `mvn clean package ; ./run`. If you haven't made any changes to the code since the last run, you can simply run `./run`. The server will start up, and anything printed on the server (`System.out.println("...")`) will appear in the same terminal window.

In order to run the frontend: *In another terminal window*, navigate to the `frontend/my-app/` directory  and run `yarn start`.

**NOTE:** Always make sure the backend is actually ***running*** if the frontend doesn't seem to be working. If the console shows an
`ERR_CONNECTION_REFUSED` error, confirming the backend is running at all should probably be your first step. (*And of course, always make sure you are running the latest version of your backend by running `mvn clean package` before `./run`*).
