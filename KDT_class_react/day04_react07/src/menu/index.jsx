import styles from './index.module.css'
import PageHeader from "../common/components/PageHeader";
import CommonRoutes from "../common/components/CommonRoutes";

export default function MenuPage({menus, setMenus}) {
    const dataHead = [
        {key: "id", name: "ID"},
        {key: "name", name: "메뉴"},
        {key: "price", name: "가격"},
    ];
    const header = [
        {name: "목록", link: "/menu/list"},
        {name: "입력", link: "/menu/input"},
        {name: "수정", link: "/menu/patch"},
        {name: "삭제", link: "/menu/delete"},
    ];

    return (
        <div className={styles.container}>
            <div className={styles.header}>
                <PageHeader header={header}/>
            </div>
            <div className={styles.content}>
                <CommonRoutes data={menus} setData={setMenus} dataHead={dataHead}/>
            </div>
        </div>
    );
}