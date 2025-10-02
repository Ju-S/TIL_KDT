import './App.css';
import {useEffect, useRef, useState} from "react";
import axios from "axios";

function App() {
    // useRef() - document.getElementById와 같이 state가 아닌 dom으로 직접 접근
    // 하지만 react에 알리고 life cycle에 맞춰 움직이기 때문에 useRef()를 사용
    // state로 접근할 수 없는 속성에서만 사용 권장
    const fileRef = useRef();

    const [files, setFiles] = useState([]);
    const [fileList, setFileList] = useState([]);

    const handleFileSelect = (e) => {
        setFiles(e.target.files);
    }

    const handleUpload = () => {
        const form = new FormData();

        form.append("message", "Hello");

        for (const file of files) {
            form.append("files", file);
        }

        if (fileRef.current.value !== "") {
            axios.post("http://10.5.5.7/files", form).then(() => {
                handleList();
                fileRef.current.value = "";
            });
        }
    }

    const handleDownload = (id) => {
        axios.get(`http://10.5.5.7/files/${id}`);
    }

    const handleList = () => {
        axios.get("http://10.5.5.7/files").then(response => setFileList(response.data));
    }

    useEffect(() => {
        handleList();
    }, []);

    return (
        <div className="App">
            <fieldset>
                <legend>파일 업로드</legend>
                <input type="file" multiple onChange={handleFileSelect} ref={fileRef}/>
                <button onClick={handleUpload}>업로드</button>
            </fieldset>
            <br/>
            <fieldset>
                <legend>
                    파일 리스트
                    <button onClick={handleList}>새로고침</button>
                </legend>
                {fileList.map((e, i) =>
                    <div key={i} onClick={() => handleDownload(e)}>
                        <a href={`http://10.5.5.7/files/${e}`} download>{e}</a>
                    </div>
                )}
            </fieldset>
        </div>
    );
}

export default App;
