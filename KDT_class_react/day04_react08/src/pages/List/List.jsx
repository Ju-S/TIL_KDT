import {useNavigate} from "react-router-dom";
import useMenuStore from "../../store/store";
import {useState} from "react";

const List = () => {
    const navigate = useNavigate();
    const menus = useMenuStore((state) => state.menus);
    const removeMenu = useMenuStore((state) => state.removeMenu);
    const modifyMenu = useMenuStore((state) => state.modifyMenu);

    const [deleteTarget, setDeleteTarget] = useState("");
    const [modifyData, setModifyData] = useState({id: "", name: "", price: ""});

    return (
        <div className="listBox">
            <table>
                <thead>
                <th>id</th>
                <th>name</th>
                <th>price</th>
                </thead>
                <tbody>
                {menus.map(({id, name, price}) =>
                    <tr>
                        <td>{id}</td>
                        <td>{name}</td>
                        <td>{price}</td>
                    </tr>
                )}
                </tbody>
            </table>
            <div>
                {["id", "name", "price"].map(e =>
                    <input
                        type="text"
                        placeholder={`수정할 ${e} 입력...`}
                        value={modifyData[e]}
                        onChange={input => {setModifyData(prev => ({...prev, [e]: input.target.value}))}}
                    />
                )}
                <button onClick={() => {
                    modifyMenu(modifyData);
                    setModifyData({id: "", name: "", price: ""})
                }}>수정
                </button>
            </div>
            <div>
                <input type="text" placeholder="삭제할 ID 입력..." value={deleteTarget}
                       onChange={e => setDeleteTarget(e.target.value)}/>
                <button onClick={() => {
                    removeMenu(deleteTarget);
                    setDeleteTarget("")
                }}>삭제
                </button>
            </div>
            <button onClick={() => navigate("/")}>홈으로</button>
        </div>
    );
}

export default List;