import {useState} from "react";

export default function ListBox({menus}) {
    return (
        <div className="listBox">
            <table>
                <thead>
                <th>id</th>
                <th>name</th>
                <th>price</th>
                </thead>
                <tbody>
                {menus.map(({id, name, price}) =>
                    <tr>
                        <td>{id}</td>
                        <td>{name}</td>
                        <td>{price}</td>
                    </tr>
                )}
                </tbody>
            </table>
        </div>
    );
}