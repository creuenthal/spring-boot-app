import React from 'react';
import logo from './logo.svg';
import './App.css';
import Inventory from './Inventory.js'
import Scanner from './Scanner.js'
import Catalog from "./Catalog";

function App() {
    return (
        <div className="App">
            <span>Upload new inventory:</span>
            <Scanner/>
            <span>Current inventory:</span>
            <Inventory/>
            <span>Product Catalog:</span>
            <Catalog/>
        </div>
    );
}

export default App;
