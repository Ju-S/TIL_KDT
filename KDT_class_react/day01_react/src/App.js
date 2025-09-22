import './App.css';
import {useState} from "react";
import {CustomTable} from "./components/CustomTable";
import {CustomFieldset} from "./components/CustomFieldset";
import {CustomList} from "./components/CustomList";

function App() {
    const [test, setTest] = useState("testValue");
    return (
        <div className="container">
            <CustomTable onClickFunc={setTest} test={test}/>
            <CustomFieldset/>
            <CustomList/>
        </div>
    );
}

export default App;
