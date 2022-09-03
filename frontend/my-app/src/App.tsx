import React from "react";
import logo from "./logo.svg";
import "./App.css";

function App() {
  // The current user input of the text field
  const [userInput, setUserInput] = React.useState("");
  // The set of strings displayed as a list
  const [stringList, setStringList] = React.useState([""]);

  const getSetEndpoint = "http://localhost:3017/getset";
  const addToSetEndpoint = "http://localhost:3017/addtoset";


  function getSetFromServer() {
    fetch(getSetEndpoint)
      .then((response) => response.json())
      .then((data) => {setStringList(data.stringSetAsList)})
  }

  // Get the list of strings from the backend on page load
  React.useEffect(() => {
    getSetFromServer();
  }, []);

  function handleSubmitString() {
    const requestOptions: RequestInit = {
      method: "POST",
      // Use the input of the text field for the body of the request
      body: JSON.stringify({ key: userInput }),
    };
    // Send a POST request to the backend to add a string to the set
    fetch(addToSetEndpoint, requestOptions)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        return data;
      })
      // After the POST request is complete, get the updated set from the backend
      .then(() => getSetFromServer());
  }

  return (
    <div className="App">
      <div className="">
        <div className="inputSection">
          <label>Add a string to the stringset:</label>
          <input
            type="text"
            value={userInput}
            onChange={(e) => setUserInput(e.target.value)}
          ></input>
          <br />
          <button onClick={handleSubmitString}>Add the string to the set</button>
        </div>
        <div className="displaySection">
          <br />
          Current string set: <br />
          {stringList.map((value, index) => (
            <span key={"val:" + value}>{(index ? ", " : " ") + "\""+ value + "\""}</span>
          ))}
        </div>
      </div>
    </div>
  );
}

export default App;
