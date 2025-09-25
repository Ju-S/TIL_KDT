import styles from './DataInputForm.module.css'

export default function DataInputForm({dataHead, data, setData}) {
    const handleChange = (e) => {
        setData(prev => ({...prev, [dataHead.key]:e.target.value}))
    }

    return (
        <input
            type="text"
            placeholder={`${dataHead.name} 입력...`}
            onChange={handleChange}
            value={data[dataHead.key]}
        />
    );
}