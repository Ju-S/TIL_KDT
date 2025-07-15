import logo from './logo.svg';
import './App.css';

import React from 'react';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import SignInPage from "./pages/SignInPage";

function App() {
  // router를 통해 각 url 마다의 페이지를 연결해두어야 함.
  return (
    <Router>
      <Routes>
        <Route path = "/" element={<SignInPage />}/>
      </Routes>
    </Router>
  );
}

export default App;
