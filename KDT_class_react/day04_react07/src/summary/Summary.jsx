import styles from "../HomePage.module.css";
import useEmployeeStore from "../employee/store/employeeStore";
import useMovieStore from "../movie/store/movieStore";
import useMenuStore from "../menu/store/menuStore";

export default function SummaryPage() {
    const employeesLength = useEmployeeStore((state) => state.employees.length);
    const moviesLength = useMovieStore((state) => state.movies.length);
    const menusLength = useMenuStore((state) => state.menus.length);

    return (
        <div className={styles.home}>
            <div className={styles.title}>
                <h1>영화 관리 시스템</h1>
            </div>
            <div className={styles.statistics}>
                <h3>직원: {employeesLength}명</h3>
                <h3>영화: {moviesLength}편</h3>
                <h3>메뉴: {menusLength}개</h3>
            </div>
        </div>
    );
}
