import {Route, Routes} from "react-router-dom";
import ListPage from "../pages/ListPage";
import InputPage from "../pages/InputPage";
import PatchPage from "../pages/PatchPage";
import DeletePage from "../pages/DeletePage";

export default function CommonRoutes({store, data, dataHead}) {
    return (
        <Routes>
            <Route path="" element={<ListPage dataList={data} dataHead={dataHead}/>}/>
            <Route path="list" element={<ListPage dataList={data} dataHead={dataHead}/>}/>
            <Route path="input" element={<InputPage dataHead={dataHead} setData={store.insert}/>}/>
            <Route path="patch" element={<PatchPage dataHead={dataHead} setData={store.patch}/>}/>
            <Route path="delete" element={<DeletePage setData={store.delete}/>}/>
        </Routes>
    );
}