import styles from './PatchPage.module.css'
import {useState} from "react";
import DataInputForm from "../components/DataInputForm";

export default function PatchPage({setData, dataHead}) {
    const [modifyData, setModifyData] = useState(
        dataHead.reduce((prev, key) => ({
            ...prev, [key.key]: ""
        }), {})
    );

    const handlePatch = () => {
        setData(modifyData);
        setModifyData(
            dataHead.reduce((prev, key) => ({
                ...prev, [key.key]: ""
            }), {})
        );
    }

    return (
        <div className={styles.container}>
            <h1>수정</h1>
            <br/>
            <div className={styles.input}>
                {dataHead.map(e =>
                    <DataInputForm dataHead={e} data={modifyData} setData={setModifyData}/>
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