import {useNavigate} from "react-router-dom";

const List = ({menus}) => {
    const navigate = useNavigate();

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
            <button onClick={() => navigate("/")}>홈으로</button>
        </div>
    );
}

export default List;