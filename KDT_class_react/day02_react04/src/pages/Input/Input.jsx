import {useState} from "react";
import {useNavigate} from "react-router-dom";

const Input = ({setMenus}) => {
    const navigate = useNavigate();
    const [newMenu, setNewMenu] = useState({id: "", name: "", price: ""});

    const handleChange = (e) => {
        const {name, value} = e.target;

        if (name === "id" && !/^\d*$/.test(value)) {
            return false;
        }
        setNewMenu(prev => ({...prev, [name]: value}));
    }

    const handleAdd = () => {
        if (newMenu.id.trim() !== "" && newMenu.name.trim() !== "" && newMenu.price.trim() !== "") {
            setMenus(prev => [...prev, newMenu]);
            setNewMenu({id: "", name: "", price: ""});
            navigate("/list");
        }
    }

    return (
        <div className="inputBox">
            {["id", "name", "price"].map(e =>
                <>
                    <input
                        type="text"
                        placeholder={`${e} 입력...`}
                        name={e}
                        onChange={handleChange}
                        value={newMenu[e]}
                    />
                    <br/>
                </>
            )}
            <button onClick={handleAdd}>등록</button>
            <button onClick={() => navigate("/")}>취소</button>
        </div>
    );
}

export default Input;