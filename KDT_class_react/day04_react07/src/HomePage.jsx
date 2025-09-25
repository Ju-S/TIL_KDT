import styles from './HomePage.module.css'
import PageHeader from "./common/components/PageHeader";
import {Route, Routes} from "react-router-dom";
import EmployeePage from "./employee";
import MoviePage from "./movie";
import MenuPage from "./menu";
import {useState} from "react";

export default function HomePage() {
    const [movies, setMovies] = useState([
        {id: 1, name: '영화1', genre: '액션'},
        {id: 2, name: '영화2', genre: '로맨스'},
        {id: 3, name: '영화3', genre: '판타지'},
        {id: 4, name: '영화4', genre: '드라마'},
        {id: 5, name: '영화5', genre: '공포'},
    ]);

    const [menus, setMenus] = useState([
        {id: 1, name: '생과일 주스', price: 4000},
        {id: 2, name: '카페모카', price: 3000},
        {id: 3, name: '카페라떼', price: 2500},
        {id: 4, name: '프라푸치노', price: 4500},
        {id: 5, name: '아이스 아메리카노', price: 2000},
    ]);

    const [employees, setEmployees] = useState([
        {id: 1, name: '김철수', rank: '대리'},
        {id: 2, name: '김영희', rank: '사장'},
        {id: 3, name: '이미지', rank: '주임'},
        {id: 4, name: '백상상', rank: '사원'},
        {id: 5, name: '아이스', rank: '사원'},
    ]);

    const header = [
        {name: "Home", link: "/"},
        {name: "직원정보", link: "/employee"},
        {name: "영화정보", link: "/movie"},
        {name: "메뉴정보", link: "/menu"},
    ];

    return (<div className={styles.container}>
        <div className={styles.sideBar}>
            <PageHeader header={header}/>
        </div>
        <div className={styles.content}>
            <Routes>
                <Route path="/" element={
                    <div className={styles.home}>
                        <div className={styles.title}>
                            <h1>영화 관리 시스템</h1>
                        </div>
                        <div className={styles.statistics}>
                            <h3>직원: {employees.length}명</h3>
                            <h3>영화: {movies.length}편</h3>
                            <h3>메뉴: {menus.length}개</h3>
                        </div>
                    </div>
                }/>
                <Route path="/employee/*" element={<EmployeePage employees={employees} setEmployees={setEmployees}/>}/>
                <Route path="/movie/*" element={<MoviePage movies={movies} setMovies={setMovies}/>}/>
                <Route path="/menu/*" element={<MenuPage menus={menus} setMenus={setMenus}/>}/>
                <Route path="*" element={<h1>404 Not Found</h1>}/>
            </Routes>
        </div>
    </div>);
}