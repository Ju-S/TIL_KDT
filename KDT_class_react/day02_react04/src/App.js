import './App.css';
import InputBox from "./components/InputBox";
import ListBox from "./components/ListBox";
import {useState} from "react";

function App() {
    const [menus, setMenus] = useState([
        {id: 1, name: '아메리카노', price: 3000},
        {id: 2, name: '카페라떼', price: 4000},
        {id: 3, name: '오렌지쥬스', price: 3500},
        {id: 4, name: '카페모카', price: 4500},
        {id: 5, name: '망고쥬스', price: 3800}
    ]);

    return (
        <div className="container">
            <InputBox setMenus={setMenus}/>
            <ListBox menus={menus}/>
        </div>
    );
}

export default App;
