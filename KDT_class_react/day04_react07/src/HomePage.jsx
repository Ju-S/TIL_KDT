import styles from './HomePage.module.css'
import PageHeader from "./common/components/PageHeader";
import {Route, Routes} from "react-router-dom";
import EmployeePage from "./employee";
import MoviePage from "./movie";
import MenuPage from "./menu";
import SummaryPage from "./summary/Summary";

export default function HomePage() {
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
                <Route path="/" element={<SummaryPage/>}/>
                <Route path="/employee/*" element={<EmployeePage/>}/>
                <Route path="/movie/*" element={<MoviePage/>}/>
                <Route path="/menu/*" element={<MenuPage/>}/>
                <Route path="*" element={<h1>404 Not Found</h1>}/>
            </Routes>
        </div>
    </div>);
}