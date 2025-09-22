// functional component
export function CustomTable({onClickFunc, test}) {
    return (
        <table align="center" border={1}>
            <thead>
            <tr>
                <th>아무테이블</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>{test}</td>
                <td>
                    <button onClick={() => onClickFunc("testChange")}>.</button>
                </td>
            </tr>
            <tr>
                <td>데이터2</td>
            </tr>
            </tbody>
        </table>
    )
}