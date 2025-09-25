import styles from "./Index.module.css"
import {Link, useNavigate} from "react-router-dom";

const Index = () => {
    const navigate = useNavigate();

    return (
        <div className={styles.container}>
            <div className={styles.title}>
                <h1>Index</h1>
            </div>
            <div className={styles.buttons}>
                <div>
                    <Link to="/input"><button>Input</button></Link>
                </div>
                <div>
                    <button onClick={() => navigate("/list")}>Output</button>
                </div>
            </div>
        </div>
    );
}

export default Index;