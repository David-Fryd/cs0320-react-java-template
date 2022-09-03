import React from "react";
import logo from "./logo.svg";
import "./App.css";

function App() {
  // The set of strings displayed as a list
  const [stringList, setStringList] = React.useState([""]);

  // Get the list of strings from the backend
  // React.useEffect(() => {

  function handleClick() {
    console.log("clicked!");

    const requestOptions: RequestInit = {
      method: "POST",
      body: JSON.stringify({ key: "Stringtoadd!" }),
    };

    // Send a POST request to the backend to add a string to the set
    fetch("http://localhost:3017/addtoset", requestOptions)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        return data;
      })
      .then((data) => {});
  }

  return (
    <div className="App">
      <div className="">
        <div className="inputSection">
          <label>Add a string to the stringset:</label>
          <input type="text"></input>
          <button onClick={handleClick}>Add the string to the set</button>
        </div>
        <div className="displaySection">
          <br />
          Current string set: <br />
          {stringList.map((value) => (
            <span key={"val:" + value}>{value + " "}</span>
          ))}
        </div>
      </div>
    </div>
  );
}

export default App;
