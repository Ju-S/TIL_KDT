import './App.css';
import axios from "axios";
import useAuthStore from "./store/authStore";

function App() {
    const login = useAuthStore(state => state.login);
    const logout = useAuthStore(state => state.logout);
    // const token = useAuthStore(state => state.token);

    const handleLogin = () => {
        axios.post(`http://10.5.5.7/auth`, {id: "test", pw: "testPW"})
            .then(res => login(res.data));
    }

    const handleTest =() => {
        const token = sessionStorage.getItem("token");

        axios.get(`http://10.5.5.7/auth/test`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then(resp => console.log(resp))
            .catch(resp => {
                console.log(resp);
                alert(resp.response.data);
            });
    }

    return (
        <div className="container">
            <button onClick={handleLogin}>Login</button>
            <button onClick={logout}>Logout</button>
            <br/>
            <button onClick={handleTest}>Test</button>
        </div>
    );
}

export default App;
