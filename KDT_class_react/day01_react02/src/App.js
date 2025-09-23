import './App.css';
import {useState} from "react";

// React의 특징
// 코드 재사용성 (functional Component)
// 상태변수 기반 UI 갱신
// SPA(Single Page Application) 특징으로 인한 UX 개선

export default function App() {
    const [messages, setMessages] = useState([
        {seq: 1, writer: '김철수', message: '오늘 날씨가 참 좋네요!'},
        {seq: 2, writer: '이영희', message: '점심 메뉴는 뭐가 좋을까요?'},
        {seq: 3, writer: '박지민', message: '프로젝트 마감이 다가오고 있어요. 모두 힘내세요!'},
        {seq: 4, writer: '최유리', message: '새로운 책을 추천받고 싶어요. 추천해 주실 수 있나요?'},
        {seq: 5, writer: '정태욱', message: '이번 주말에 캠핑 갈 사람?'}
    ]);

    const [message, setMessage] = useState({seq: '', writer: '', message: ''});
    const [modifyMessage, setModifyMessage] = useState({seq: '', writer: '', message: ''});
    const [deleteTarget, setDeleteTarget] = useState('');

    const handleChange = (e) => {
        const {name, value} = e.target;
        if (name === "seq" && !/^\d*$/.test(value)) {
            return false;
        }
        setMessage(prev => ({...prev, [name]: value}));
    }

    const handleModifyChange = (e) => {
        const {name, value} = e.target;
        if (name === "seq" && !/^\d*$/.test(value)) {
            return false;
        }
        setModifyMessage(prev => ({...prev, [name]: value}));
    }

    const handleAdd = () => {
        if (message.seq.trim() !== '' && message.writer.trim() !== '' && message.message.trim() !== '') {
            setMessages(prev => [...prev, {...message}]);
            setMessage({seq: '', writer: '', message: ''});
        }
    }

    const handleModify = () => {
        if (modifyMessage.seq.trim() !== '' && modifyMessage.writer.trim() !== '' && modifyMessage.message.trim() !== '') {
            setMessages(prev => prev.map(e => {
                if(e.seq == modifyMessage.seq) {
                    return modifyMessage;
                }
                return e;
            }));
            setModifyMessage({seq: '', writer: '', message: ''});
        }
    }

    const handleDelete = () => {
        if (deleteTarget !== '') {
            setMessages(prev => prev.filter(e => e.seq != deleteTarget));
            setDeleteTarget('');
        }
    }

    return (
        <div className="container">
            <table border="1" align="center">
                <thead>
                <tr>
                    <th>seq</th>
                    <th>writer</th>
                    <th>message</th>
                </tr>
                </thead>
                <tbody>
                {messages.map(({seq, writer, message}, idx) => {
                    return (
                        <tr key={idx}>
                            <td>{seq}</td>
                            <td>{writer}</td>
                            <td>{message}</td>
                        </tr>
                    );
                })}
                <tr>
                    <td colSpan={3}>
                        {["seq", "writer", "message"].map(e => {
                            return (
                                <input
                                    type="text"
                                    placeholder={`${e} 입력...`}
                                    value={message[e]}
                                    name={e}
                                    onChange={handleChange}
                                />
                            );
                        })}
                        <button onClick={handleAdd}>추가</button>
                    </td>
                </tr>
                <tr align="center">
                    <td colSpan={3}>
                        <input
                            type="text"
                            placeholder="글번호 입력..."
                            value={deleteTarget}
                            onChange={(e) => setDeleteTarget(e.target.value)}
                        />
                        <button onClick={handleDelete}>삭제</button>
                    </td>
                </tr>
                <tr align="center">
                    <td colSpan={3}>
                        {["seq", "writer", "message"].map(e => {
                            return (
                                <input
                                    type="text"
                                    placeholder={`${e} 입력...`}
                                    value={modifyMessage[e]}
                                    name={e}
                                    onChange={handleModifyChange}
                                />
                            );
                        })}
                        <button onClick={handleModify}>수정</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}

// export default function App() {
//     const [messages, setMessages] = useState([]);
//     const [message, setMessage] = useState('');
//
//     const handleChange = (e) => {
//         setMessage(e.target.value);
//     }
//
//     const handleKeyDown = (e) => {
//         if (e.key === 'Enter' && message.trim() !== '') {
//             setMessages([...messages, message]);
//             setMessage('');
//         }
//     }
//
//     return (
//         <div className="container">
//             <h1>메세지 목록</h1>
//             <ul>
//                 {messages.map((e, idx) => <li key={idx}>{e}</li>)}
//             </ul>
//             <hr/>
//             <input
//                 type="text"
//                 placeholder="추가할 메세지 입력..."
//                 value={message}
//                 onChange={handleChange}
//                 onKeyDown={handleKeyDown}
//             />
//         </div>
//     );
// }

// export default function App() {
//     const [messages, setMessages] = useState(["Javascript", "React", "Practice"]);
//     const [message, setMessage] = useState('');
//
//     const handleChange = (e) => {
//         setMessage(e.target.value);
//     }
//
//     const handleKeyDown = (e) => {
//         if(e.key === 'Enter' && message.trim() !== '') {
//             setMessages([...messages, message]);
//             setMessage('');
//         }
//     }
//
//     return (
//         <div className="container">
//             <h1>메세지 목록</h1>
//             <ul>
//                 {messages.map((e, idx) => <li key={idx}>{e}</li>)}
//             </ul>
//             <hr/>
//             <input
//                 type="text"
//                 placeholder="추가 할 메세지 입력"
//                 onChange={handleChange}
//                 onKeyDown={handleKeyDown}
//                 value={message}
//             />
//         </div>
//     );
// }

// export default function App() {
//     const [text, setText] = useState('');
//
//
//     return (
//         <div className="container">
//             <h1>메세지: {text}</h1>
//             <hr/>
//             <input type="text" placeholder="메세지를 입력하세요." onChange={e=>setText(e.target.value)}/>
//         </div>
//     );
// }

// function App() {
//     // 상태 변수
//     // React는 직접적으로 UI를 다루지 않고, state라는 상태값을 통해 UI를 제어한다.
//     // UI는 상태로부터 파생된다.
//
//     const [cnt, setCnt] = useState(10);
//
//     return (
//         <div className="container">
//             <h1>카운트: {cnt}</h1>
//             <hr/>
//             <button onClick={()=>setCnt(cnt+1)}>+</button>
//             <button onClick={()=>setCnt(cnt-1)}>-</button>
//         </div>
//     );
// }
//
// export default App;
