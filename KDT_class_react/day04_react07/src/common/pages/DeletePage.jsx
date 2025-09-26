import styles from './DeletePage.module.css'
import {useState} from "react";
import DataInputForm from "../components/DataInputForm";

export default function DeletePage({setData}) {
    const [target, setTarget] = useState({id: ""});

    const handleDelete = () => {
        setData(target.id);
        setTarget({id: ""});
    }

    return (
        <div className={styles.container}>
            <h1>삭제</h1>
            <br/>
            <div className={styles.input}>
                <DataInputForm dataHead={{key: "id", name: "ID"}} data={target} setData={setTarget}/>
            </div>
            <div className={styles.button}>
                <button
                    className={styles.submitBtn}
                    onClick={handleDelete}>
                    삭제
                </button>
            </div>
        </div>
    );
}