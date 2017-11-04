import React, { Component } from 'react';
import classNames from 'classnames';

import './Action.css';

class Loading extends Component {
  render() {

    return (
      <div className="result">
        <i className={classNames('action-item','loading')}></i>
      </div>
    );
  }
}

export default Loading;
