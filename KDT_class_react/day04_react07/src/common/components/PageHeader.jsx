import styles from './PageHeader.module.css'
import {Link} from "react-router-dom";

export default function PageHeader({header}) {
    return (
        <div className={styles.container}>
            {header.map(({name, link}) =>
                <Link to={link}><button>{name}</button></Link>
            )}
        </div>
    );
}