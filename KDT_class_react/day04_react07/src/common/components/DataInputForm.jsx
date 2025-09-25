import styles from './DataInputForm.module.css'

export default function DataInputForm({name, data, setData}) {
    const handleChange = (e) => {
        setData(prev => ({...prev, [name]:e.target.value}))
    }

    return (
        <input
            type="text"
            placeholder={`${name} 입력...`}
            onChange={handleChange}
            value={data[name]}
        />
    );
}