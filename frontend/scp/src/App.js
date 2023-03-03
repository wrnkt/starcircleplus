import React, { Component } from "react";

import Entry from './Entry.js';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      entry: {
        type: "STAR",
        checked: false,
        dateCreated: "2023-03-03T14:04:21.374638-05:00",
        content: "new entry"
      },
    };
  }
  render() {
    return (
      <div>
        <Entry entry={this.state.entry} />
      </div>
    );
  }
}

export default App;
