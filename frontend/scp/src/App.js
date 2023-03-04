import React, { Component } from "react";

//import Entry from './Entry.js';

export default function App() {
  return (
    <div>
      <EntryListView />
    </div>
  );
}

function EntryListView() { return (
    <div>
        <span>I'm a div</span>
        <Entry />
    </div>
    );
}

function Entry({entry}) {
  return (
    <div className="Entry">
      <h1>Entry rendered.</h1>
    </div>
  );
};

function TypeIdentifier({ type }) {
  return (
    <div className="type-identifier">
      Type:{type}
    </div>
  );
};
