import React, { Component } from 'react';
import classNames from 'classnames';

import './Action.css';

class GameResult extends Component {

  constructor (props) {
    super(props)
    var resultText = "";
    switch(props.result){
      case "winner":
        resultText = "WINNER!!!"
        break;
      case "loser":
        resultText = "LOSER!!!"
        break;
      case "tie":
        resultText = "TIE GAME!!!"
        break;
      case "noopponent":
        resultText = "No opponent found"
        break;
    }
    this.state={resultText:resultText}
  }

  render() {

    return (
      <div className="result">
        <h2>{this.state.resultText}</h2>
        <i className={classNames('action-item',this.props.result)}></i>
        <div><input type="button" value="Play Again?" onClick={this.props.resetHandler} /></div>
      </div>
    );
  }
}

export default GameResult;
