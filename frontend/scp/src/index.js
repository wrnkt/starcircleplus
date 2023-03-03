import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';

import App from './App.js';
import Entry from './Entry.js';
import Table from './Table.js';

const testEntry = {
        "type": "STAR",
        "checked": false,
        "dateCreated": "2023-03-03T14:04:21.374638-05:00",
        "content": "new entry"
};

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// #34495E

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
