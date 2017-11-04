import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Actions from './Actions.js';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Rock, Paper, Scissors</h1>
        </header>
        <Actions />
        <p className="App-intro">
          Rock, Paper, Scissors, Shoot?  Or just Rock, Paper Scissors? No need to decide.  Select your move above and wait for your opponent to do the same!
        </p>
      </div>
    );
  }
}

export default App;
