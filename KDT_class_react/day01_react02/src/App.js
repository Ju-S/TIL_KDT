import './App.css';
import {useState} from "react";

export default function App() {
    const [messages, setMessages] = useState([
        {seq: 1, writer: '김철수', message: '오늘 날씨가 참 좋네요!'},
        {seq: 2, writer: '이영희', message: '점심 메뉴는 뭐가 좋을까요?'},
        {seq: 3, writer: '박지민', message: '프로젝트 마감이 다가오고 있어요. 모두 힘내세요!'},
        {seq: 4, writer: '최유리', message: '새로운 책을 추천받고 싶어요. 추천해 주실 수 있나요?'},
        {seq: 5, writer: '정태욱', message: '이번 주말에 캠핑 갈 사람?'}
    ]);

    const [message, setMessage] = useState({seq: '', writer: '', message: ''});

    const handleSeqChange = (e) => {
        if(/^\d*$/.test(e.target.value)) {
            setMessageWithName('seq', e.target.value);
        }
    }

    const handleWriterChange = (e) => {
        setMessageWithName('writer', e.target.value);
    }

    const handleMessageChange = (e) => {
        setMessageWithName('message', e.target.value);
    }

    const setMessageWithName = (name, value) => {
        setMessage({...message, [name]: value});
    }

    const handleAdd = () => {
        if (message.writer.trim() !== '' && message.message.trim() !== '') {
            // let lastSeq = 0;

            // messages.forEach(e => {
            //     if (e.seq > lastSeq) lastSeq = e.seq;
            // });

            // setMessages([...messages, {seq: lastSeq + 1, writer: message.writer, message: message.message}]);
            // setMessages([...messages, {seq: messages.length + 1, writer: message.writer, message: message.message}]);
            setMessages([...messages, message]);
            setMessage({seq: '', writer: '', message: ''});
        }
    }

    return (
        <div className="container">
            <table border="1">
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
                </tbody>
            </table>
            <hr/>
            <input
                type="text"
                placeholder="글번호 입력..."
                onChange={handleSeqChange}
                value={message.seq}
            />
            <br/>
            <input
                type="text"
                placeholder="작성자 입력..."
                onChange={handleWriterChange}
                value={message.writer}
            />
            <br/>
            <input
                type="text"
                placeholder="메세지 입력..."
                onChange={handleMessageChange}
                value={message.message}
            />
            <br/>
            <button onClick={handleAdd}>추가</button>
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
