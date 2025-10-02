import {create} from "zustand";

const useAuthStore = create((set) => ({
    token: "",
    login: (token) => {
        sessionStorage.setItem("token", token);

        set(() => ({
            token: token
        }))
    },
    logout: () => {
        sessionStorage.clear();
        set(() => ({
            token: "",
        }))
    },
}))

export default useAuthStore;