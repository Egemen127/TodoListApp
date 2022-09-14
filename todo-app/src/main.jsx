import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './index.css'
import {BrowserRouter, Routes, Route} from "react-router-dom"
import Login from "./Login"


ReactDOM.createRoot(document.getElementById('root')).render(
    <BrowserRouter>
        <Routes>
            <Route path="/main" element={<App />}/>
            <Route path="/" element={<Login/>}/>
        </Routes>
    </BrowserRouter>
)
