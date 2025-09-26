import styles from './index.module.css'
import PageHeader from "../common/components/PageHeader";
import CommonRoutes from "../common/components/CommonRoutes";
import useEmployeeStore from "./store/employeeStore";

export default function EmployeePage() {
    const store = useEmployeeStore();

    const dataHead = [
        {key: "id", name: "ID"},
        {key: "name", name: "이름"},
        {key: "rank", name: "직급"},
    ];
    const header = [
        {name: "목록", link: "/employee/list"},
        {name: "입력", link: "/employee/input"},
        {name: "수정", link: "/employee/patch"},
        {name: "삭제", link: "/employee/delete"},
    ];

    return (
        <div className={styles.container}>
            <div className={styles.header}>
                <PageHeader header={header}/>
            </div>
            <div className={styles.content}>
                <CommonRoutes store={store} data={store.employees} dataHead={dataHead}/>
            </div>
        </div>
    );
}