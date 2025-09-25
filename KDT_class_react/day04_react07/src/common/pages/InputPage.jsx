import styles from './InputPage.module.css'
import {useState} from "react";
import DataInputForm from "../components/DataInputForm";

export default function InputPage({setData, dataHead}) {
    const [newData, setNewData] = useState(
        dataHead.reduce((prev, key) => ({
            ...prev, [key]: ""
        }), {})
    );

    const handleAdd = () => {
        setData(prev => [...prev, newData])
        setNewData(() =>
            dataHead.reduce((prev, key) => ({
                ...prev, [key]: ""
            }), {})
        );
    }

    return (
        <div className={styles.container}>
            <h1>입력</h1>
            <br/>
            <div className={styles.input}>
                {dataHead.map(e =>
                    <DataInputForm name={e} data={newData} setData={setNewData}/>
                )}
            </div>
            <div className={styles.button}>
                <button
                    className={styles.submitBtn}
                    onClick={handleAdd}>
                    추가
                </button>
            </div>
        </div>
    );
}