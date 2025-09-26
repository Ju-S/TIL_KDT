import {create} from "zustand";


const useMovieStore = create(set => ({
    movies: [
        {id: 1, name: '영화1', genre: '액션'},
        {id: 2, name: '영화2', genre: '로맨스'},
        {id: 3, name: '영화3', genre: '판타지'},
        {id: 4, name: '영화4', genre: '드라마'},
        {id: 5, name: '영화5', genre: '공포'},
    ],
    insert: (newData) => set((state) => ({
        movies: [...state.movies, {...newData, id: Number(newData.id)}]
    })),
    patch: (modifyData) => set((state) => ({
        movies: state.movies.map(e =>
            e.id === Number(modifyData.id) ? {...modifyData, id: Number(modifyData.id)} : e
        )
    })),
    delete: (target) => set((state) => ({
        movies: state.movies.filter(e => e.id !== Number(target))
    }))
}))

export default useMovieStore;