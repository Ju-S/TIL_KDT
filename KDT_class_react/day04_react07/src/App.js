import styles from './App.module.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import HomePage from "./HomePage";

function App() {
    return (
        <BrowserRouter>
            <div className={styles.container}>
                <Routes>
                    <Route path="*" element={<HomePage/>}/>
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;
