import {create} from "zustand";


const useEmployeeStore = create(set => ({
    employees: [
        {id: 1, name: '김철수', rank: '대리'},
        {id: 2, name: '김영희', rank: '사장'},
        {id: 3, name: '이미지', rank: '주임'},
        {id: 4, name: '백상상', rank: '사원'},
        {id: 5, name: '아이스', rank: '사원'},
    ],
    insert: (newData) => set((state) => ({
        employees: [...state.employees, {...newData, id: Number(newData.id)}]
    })),
    patch: (modifyData) => set((state) => ({
        employees: state.employees.map(e =>
            e.id === Number(modifyData.id) ? {...modifyData, id: Number(modifyData.id)} : e
        )
    })),
    delete: (target) => set((state) => ({
        employees: state.employees.filter(e => e.id !== Number(target))
    }))
}))
export default useEmployeeStore;