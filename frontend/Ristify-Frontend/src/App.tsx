import './styles/variables.scss'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import React, {lazy, Suspense} from "react";

const HomePage = lazy(() => import("./pages/Home/HomePage.tsx"))
const NotFoundPage = lazy(() => import("./pages/NotFound/NotFoundPage.tsx"))

function App() {
    return (
        <>
            <BrowserRouter>
                <Suspense fallback={<SuspenseFallback/>}>
                    <Routes>
                        <Route path="/" element={<HomePage/>}/>
                        <Route path="*" element={<NotFoundPage/>}/>
                    </Routes>
                </Suspense>
            </BrowserRouter>
        </>
    )
}

function SuspenseFallback() {
    return (
        <>
            <div>Loading...</div>
        </>
    )
}

export default App
