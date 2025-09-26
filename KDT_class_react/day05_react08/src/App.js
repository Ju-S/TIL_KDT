import './App.css';
import axios from "axios";
import {useEffect, useState} from "react";

function App() {
    const baseURL = "http://10.5.5.7:80";
    const [musics, setMusics] = useState([]);
    const [newMusics, setNewMusics] = useState({title: "", singer: ""});
    const [modifyMusics, setModifyMusics] = useState({id: "", title: "", singer: ""});
    const [deleteTarget, setDeleteTarget] = useState("");

    const handleDefaultGet = () => {
        axios.get(baseURL + "/music").then((res) => setMusics(res.data));
    }

    const handleAdd = () => {
        axios.post(baseURL + "/music", newMusics).then(handleDefaultGet);
        setNewMusics({title: "", singer: ""});
    }

    const handleUpdate = () => {
        axios.put(baseURL + `/music/${modifyMusics.id}`, modifyMusics).then(handleDefaultGet);
        setModifyMusics({id: "", title: "", singer: ""});
    }

    const handleDelete = () => {
        axios.delete(baseURL + `/music/${deleteTarget}`).then(handleDefaultGet);
        setDeleteTarget("");
    }

    useEffect(() =>
        handleDefaultGet
    , [setMusics]);

    return (
        <div className="container">
            <div className="list">
                <fieldset>
                    <legend>음악 목록</legend>
                    <table border={1}>
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>title</th>
                            <th>singer</th>
                        </tr>
                        </thead>
                        <tbody>
                        {musics && musics.map(e =>
                            <tr>
                                <td>{e.id}</td>
                                <td>{e.title}</td>
                                <td>{e.singer}</td>
                            </tr>
                        )}
                        </tbody>
                    </table>
                </fieldset>
            </div>
            <div className="input">
                <input
                    type="text"
                    placeholder="제목을 입력하세요.."
                    onChange={e => setNewMusics(prev => ({...prev, title: e.target.value}))}
                    value={newMusics.title}
                />
                <input
                    type="text"
                    placeholder="가수명을 입력하세요.."
                    onChange={e => setNewMusics(prev => ({...prev, singer: e.target.value}))}
                    value={newMusics.singer}
                />
                <button onClick={handleAdd}>추가</button>
            </div>
            <div className="input">
                <input
                    type="text"
                    placeholder="수정할 ID를 입력하세요.."
                    onChange={e => setModifyMusics(prev => ({...prev, id: e.target.value}))}
                    value={modifyMusics.id}
                />
                <input
                    type="text"
                    placeholder="제목을 입력하세요.."
                    onChange={e => setModifyMusics(prev => ({...prev, title: e.target.value}))}
                    value={modifyMusics.title}
                />
                <input
                    type="text"
                    placeholder="가수명을 입력하세요.."
                    onChange={e => setModifyMusics(prev => ({...prev, singer: e.target.value}))}
                    value={modifyMusics.singer}
                />
                <button onClick={handleUpdate}>수정</button>
            </div>
            <div className="input">
                <input
                    type="text"
                    placeholder="삭제할 ID를 입력하세요.."
                    onChange={e => setDeleteTarget(e.target.value)}
                    value={deleteTarget}
                />
                <button onClick={handleDelete}>삭제</button>
            </div>
        </div>
    );
}

export default App;
