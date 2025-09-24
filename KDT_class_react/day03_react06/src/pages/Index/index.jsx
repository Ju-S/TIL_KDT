import styles from './index.module.css'
import {Route, Routes, useNavigate} from "react-router-dom";
import Exam01 from "../Exam01/Exam01";
import Exam02 from "../Exam02/Exam02";
import Exam03 from "../Exam03/Exam03";

export default function Index() {
    const navigate = useNavigate();

    return (
        <div className={styles.container}>
            <div className={styles.nav}>
                <button onClick={() => navigate("/exam01")}>
                    Exam01
                </button>
                <button onClick={() => navigate("/exam02")}>
                    Exam02
                </button>
                <button onClick={() => navigate("/exam03")}>
                    Exam03
                </button>
            </div>
            <div className={styles.content}>
                <Routes>
                    <Route path="/exam01/*" element={<Exam01/>}/>
                    <Route path="/exam02/*" element={<Exam02/>}/>
                    <Route path="/exam03/*" element={<Exam03/>}/>
                    <Route path="*" element={<h1>404 Not Found</h1>}/>
                </Routes>
            </div>
        </div>
    );
}