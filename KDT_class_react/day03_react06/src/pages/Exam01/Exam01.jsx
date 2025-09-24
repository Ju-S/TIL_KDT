import styles from './Exam01.module.css'
import {Route, Routes, useNavigate} from "react-router-dom";
import Page01 from "./pages/Page01";
import Page02 from "./pages/Page02";
import Page03 from "./pages/Page03";

export default function Exam01() {
    const navigate = useNavigate();
    return (
        <div className={styles.container}>
            <div className={styles.sideBar}>
                <button onClick={() => navigate("/exam01/page01")}>Page01</button>
                <button onClick={() => navigate("/exam01/page02")}>Page02</button>
                <button onClick={() => navigate("/exam01/page03")}>Page03</button>
            </div>
            <div className={styles.content}>
                <Routes>
                    <Route path="page01" element={<Page01/>}/>
                    <Route path="page02" element={<Page02/>}/>
                    <Route path="page03" element={<Page03/>}/>
                    <Route path="*" element={<h1>404 Not Found</h1>}/>
                </Routes>
            </div>
        </div>
    );
}