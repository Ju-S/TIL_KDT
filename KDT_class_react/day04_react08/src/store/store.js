import {create} from "zustand";

// 1. zustand가 생성하는 store안에 저장되는 모든 필드는 전부 state 값.
// 2. create에 전달되는 (set) 파라미터는 저장소 내의 객체 값을 수정하기 위해 필요한 함수

const useMenuStore = create((set) => ({
    menus: [
        {id: 1, name: '아메리카노', price: 3000},
        {id: 2, name: '카페라떼', price: 4000},
        {id: 3, name: '오렌지쥬스', price: 3500},
        {id: 4, name: '카페모카', price: 4500},
        {id: 5, name: '망고쥬스', price: 3800},
    ],
    // set함수는 증분형 함수.
    // 있는건 바꾸고 없는건 현상유지.
    addMenu: (e) => set((state) => ({
            menus: [...state.menus, e]
    })),
    removeMenu: (target) => set((state) => ({
        menus: state.menus.filter(e => e.id !== Number(target))
    })),
    modifyMenu: (modifyData) => set((state) => ({
        menus: state.menus.map(e =>
            e.id === Number(modifyData.id) ? {...modifyData, id: Number(modifyData.id)} : e
        )
    }))
}));

export default useMenuStore;