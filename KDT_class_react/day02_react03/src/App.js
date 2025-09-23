import './App.css';
import {useState} from "react";

function App() {
    const [cafeMenu, setCafeMenu] = useState([
        {id: 1, name: '아메리카노', price: 3000},
        {id: 2, name: '카페라떼', price: 4000},
        {id: 3, name: '오렌지쥬스', price: 3500},
        {id: 4, name: '카페모카', price: 4500},
        {id: 5, name: '망고쥬스', price: 3800}
    ]);
    const [newMenu, setNewMenu] = useState({id: '', name: '', price: ''});
    const [deleteTarget, setDeleteTarget] = useState('');
    const [modifyMenu, setModifyMenu] = useState({id: '', name: '', price: ''});

    const handleNewMenuChange = (e) => {
        const {name, value} = e.target;

        if (name === "id" && !/^\d*$/.test(value)) {
            return false;
        }
        setNewMenu(prev => ({...prev, [name]: value}));
    };

    const handleModifyMenuChange = (e) => {
        const {name, value} = e.target;

        if (name === "id" && !/^\d*$/.test(value)) {
            return false;
        }
        setModifyMenu(prev => ({...prev, [name]: value}));
    };

    const handleAdd = () => {
        if (newMenu.id.trim() !== '' && newMenu.name.trim() !== '' && newMenu.price.trim() !== '') {
            setCafeMenu(prev => [...prev, newMenu]);
            setNewMenu({id: '', name: '', price: ''});
        }
    }

    const handleDelete = () => {
        if (deleteTarget.trim() !== '') {
            setCafeMenu(prev => prev.filter(e => e.id != deleteTarget));
            setDeleteTarget('');
        }
    }

    const handleModify = () => {
        if (modifyMenu.id.trim() !== '' && modifyMenu.name.trim() !== '' && modifyMenu.price.trim() !== '') {
            setCafeMenu(prev => prev.map(e => {
                if (e.id == modifyMenu.id) return modifyMenu;
                return e;
            }));
            setModifyMenu({id: '', name: '', price: ''});
        }
    }

    return (
        <div className="container">
            <table border={1} align={"center"}>
                <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>price</th>
                </tr>
                </thead>
                <tbody>
                {cafeMenu.map(({id, name, price}) => {
                    return (
                        <tr>
                            <td>{id}</td>
                            <td>{name}</td>
                            <td>{price}</td>
                        </tr>
                    );
                })}
                <tr>
                    <td colSpan={3}>
                        {["id", "name", "price"].map(e =>
                            <input
                                type="text"
                                placeholder={`${e} 입력...`}
                                name={e}
                                onChange={handleNewMenuChange}
                                value={newMenu[e]}
                            />
                        )}
                        <button onClick={handleAdd}>추가</button>
                    </td>
                </tr>
                <tr>
                    <td colSpan={3} align={"center"}>
                        <input
                            type="text"
                            placeholder="삭제할 ID를 입력해주세요..."
                            onChange={e => setDeleteTarget(e.target.value)}
                            value={deleteTarget}
                        />
                        <button onClick={handleDelete}>삭제</button>
                    </td>
                </tr>
                <tr>
                    <td colSpan={3}>
                        {["id", "name", "price"].map(e =>
                            <input
                                type="text"
                                placeholder={`${e} 입력...`}
                                name={e}
                                onChange={handleModifyMenuChange}
                                value={modifyMenu[e]}
                            />
                        )}
                        <button onClick={handleModify}>수정</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}

export default App;
