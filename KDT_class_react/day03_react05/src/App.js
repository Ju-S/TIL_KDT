import styles from './App.module.css';
import {BrowserRouter, Routes, Route, Link} from 'react-router-dom';

const Exam01 = () => {
    return (
        <div style={{width: "100%", height: "100%", display: "flex", flexDirection: "column"}}>
            <div style={{flex: 2, display: "flex", justifyContent: "center", alignItems: "center"}}>
                <Link to="/exam01/page01"><button>Page01</button></Link>
                <Link to="/exam01/page02"><button>Page02</button></Link>
                    <Link to="/exam01/page03"><button>Page03</button></Link>
            </div>
            <div style={{flex: 8, }}>
                <Routes>
                    <Route path="page01" element={<h1>김주성최수종</h1>}/>
                    <Route path="page02" element={<h1>김수정최종수정</h1>}/>
                    <Route path="page03" element={<h1>김주정술주정</h1>}/>
                </Routes>
            </div>
        </div>
    );
}

function App() {
    return (
        <BrowserRouter>
            <div className={styles.container}>
                <div className={styles.sidebar}>
                    <Link to="/exam01"><button>Exam01</button></Link>
                    <br/><br/>
                    <Link to="/exam02"><button>Exam02</button></Link>
                    <br/><br/>
                        <Link to="/exam03"><button>Exam03</button></Link>
                </div>
                <div className={styles.content}>
                    <Routes>
                        <Route index element={<h1>Index</h1>}/>
                        <Route path="/exam01/*" element={<Exam01/>}/>
                        <Route path="/exam02/*" element={<h1>Exam02</h1>}/>
                        <Route path="/exam03/*" element={<h1>Exam03</h1>}/>
                        <Route path="*" element={<h1>404 Not Found</h1>}/>
                    </Routes>
                </div>
            </div>
        </BrowserRouter>
    );
}

export default App;
