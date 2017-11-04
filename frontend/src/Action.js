import React, { Component } from 'react';
import classNames from 'classnames';

import './Action.css';

class Action extends Component {
  render() {

    return (
      <div className="action" value={this.props.actionType} onClick = {this.props.handler}>

        <i className={classNames('action-item',this.props.actionType)}></i>
      </div>
    );
  }
}

export default Action;
