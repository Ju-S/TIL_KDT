import './App.css';
import Index from "./pages/Index/Index";
import {useState} from "react";
import Input from "./pages/Input/Input";
import List from "./pages/List/List";
import {BrowserRouter, Routes, Route} from "react-router-dom";

function App() {
    const [menus, setMenus] = useState([
        {id: 1, name: '아메리카노', price: 3000},
        {id: 2, name: '카페라떼', price: 4000},
        {id: 3, name: '오렌지쥬스', price: 3500},
        {id: 4, name: '카페모카', price: 4500},
        {id: 5, name: '망고쥬스', price: 3800}
    ]);

    return (
        <BrowserRouter>
            <div className="container">
                <Routes>
                    <Route path="/" element={<Index/>}/>
                    <Route path="/input" element={<Input setMenus={setMenus}/>}/>
                    <Route path="/list" element={<List menus={menus}/>}/>
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;
