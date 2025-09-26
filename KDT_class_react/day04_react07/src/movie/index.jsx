import styles from './index.module.css'
import PageHeader from "../common/components/PageHeader";
import CommonRoutes from "../common/components/CommonRoutes";
import useMovieStore from "./store/movieStore";

export default function MoviePage() {
    const store = useMovieStore();

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
                <CommonRoutes store={store} data={store.movies} dataHead={dataHead}/>
            </div>
        </div>
    );
}