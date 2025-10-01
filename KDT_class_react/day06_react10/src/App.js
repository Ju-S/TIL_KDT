import './App.css';
import {useEffect, useState} from "react";
import axios from "axios";

function App() {

    const [data, setData] = useState([]);
    const [count, setCount] = useState(0);

    useEffect(() => {
        axios.get("http://10.5.5.7/music").then(resp => {
            setData(resp.data);
        });

        setInterval();

        return () => { // clean up 함수 : 컴포넌트가 언마운팅 될 때, useEffect 내에서 사용하던 자원을 정리하는 역할
            clearInterval();
        };
    }, []);

    return (
        <div className="App">
            {data.map(({title, singer}) => <div>{title}, {singer}</div>)}
            <button onClick={() => setCount(count + 1)}>+</button>
        </div>
    );
}

export default App;
