import './App.css';
import Index from "./pages/Index/Index";
import {useState} from "react";
import Input from "./pages/Input/Input";
import List from "./pages/List/List";
import {BrowserRouter, Routes, Route} from "react-router-dom";

function App() {
    return (
        <BrowserRouter>
            <div className="container">
                <Routes>
                    <Route path="/" element={<Index/>}/>
                    <Route path="/input" element={<Input/>}/>
                    <Route path="/list" element={<List/>}/>
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;
