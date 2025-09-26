import styles from './ListPage.module.css'

export default function ListPage({dataList, dataHead}) {
    return (
        <div className={styles.container}>
            <h1>목록</h1>
            <table className={styles.dataList}>
                <thead>
                <tr>
                    {dataHead.map(e =>
                        <th>{e.name}</th>
                    )}
                </tr>
                </thead>
                <tbody>
                {dataList.map(data =>
                    <tr>
                        {dataHead.map(e =>
                            <td>{data[e.key]}</td>
                        )}
                    </tr>
                )}
                </tbody>
            </table>
        </div>
    );
}
