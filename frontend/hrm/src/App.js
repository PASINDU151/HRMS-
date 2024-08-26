import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './components/Login'; // Update the path to your Login component
import Profile from './components/Profile'; // Create and import your Profile component
import './App.css';

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/profile" element={<Profile />} />
          {/* Add more routes for other parts of your app */}
        </Routes>
      </Router>
    </div>
  );
}

export default App;
