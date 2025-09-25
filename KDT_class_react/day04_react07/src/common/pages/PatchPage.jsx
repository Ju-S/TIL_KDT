import styles from './PatchPage.module.css'
import {useState} from "react";
import DataInputForm from "../components/DataInputForm";

export default function PatchPage({setData, dataHead}) {
    const [modifyData, setModifyData] = useState(
        dataHead.reduce((prev, key) => ({
            ...prev, [key]: ""
        }), {})
    );

    const handlePatch = () => {
        setData(prev => prev.map(e => {
            if (e.id == modifyData.id) return modifyData;
            return e;
        }));
        setModifyData(
            dataHead.reduce((prev, key) => ({
                ...prev, [key]: ""
            }), {})
        );
    }

    return (
        <div className={styles.container}>
            <h1>수정</h1>
            <br/>
            <div className={styles.input}>
                {dataHead.map(e =>
                    <DataInputForm name={e} data={modifyData} setData={setModifyData}/>
                )}
            </div>
            <div className={styles.button}>
                <button
                    className={styles.submitBtn}
                    onClick={handlePatch}>
                    수정
                </button>
            </div>
        </div>
    );
}