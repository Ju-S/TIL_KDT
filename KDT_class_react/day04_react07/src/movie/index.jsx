import styles from './index.module.css'
import PageHeader from "../common/components/PageHeader";
import CommonRoutes from "../common/components/CommonRoutes";

export default function MoviePage({movies, setMovies}) {
    const dataHead = [
        {key: "id", name: "ID"},
        {key: "name", name: "제목"},
        {key: "genre", name: "장르"},
    ];
    const header = [
        {name: "목록", link: "/movie/list"},
        {name: "입력", link: "/movie/input"},
        {name: "수정", link: "/movie/patch"},
        {name: "삭제", link: "/movie/delete"},
    ];

    return (
        <div className={styles.container}>
            <div className={styles.header}>
                <PageHeader header={header}/>
            </div>
            <div className={styles.content}>
                <CommonRoutes data={movies} setData={setMovies} dataHead={dataHead}/>
            </div>
        </div>
    );
}