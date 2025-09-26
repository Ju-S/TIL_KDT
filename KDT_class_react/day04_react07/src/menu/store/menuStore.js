import {create} from "zustand";


const useMenuStore = create(set => ({
    menus: [
        {id: 1, name: '생과일 주스', price: 4000},
        {id: 2, name: '카페모카', price: 3000},
        {id: 3, name: '카페라떼', price: 2500},
        {id: 4, name: '프라푸치노', price: 4500},
        {id: 5, name: '아이스 아메리카노', price: 2000},
    ],
    insert: (newData) => set((state) => ({
        menus: [...state.menus, {...newData, id: newData.id}]
    })),
    patch: (modifyData) => set((state) => ({
        menus: state.menus.map(e =>
            e.id === Number(modifyData.id) ? {...modifyData, id: Number(modifyData.id)} : e
        )
    })),
    delete: (target) => set((state) => ({
        menus: state.menus.filter(e => e.id !== Number(target))
    }))
}))

export default useMenuStore;