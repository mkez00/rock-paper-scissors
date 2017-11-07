import React, { Component } from 'react';
import Action from './Action.js';
import './Actions.css';
import GameResult from './GameResult.js';
import Loading from './Loading.js';

class Actions extends Component {
  constructor (props) {
    super(props)

    this.handler = this.handler.bind(this)
    this.resetHandler = this.resetHandler.bind(this)
    this.state = { locationLoading: true, actionReady: false, gameFinished: false, loading: false, longitude:"0.00", latitude:"0.00" }
  }

  componentWillMount() {
    var $this = this
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position){
          $this.setState({locationLoading: false, actionReady: true, longitude: position.coords.longitude, latitude: position.coords.latitude})
        });
    } else {
      alert("No location supported")
    }
  }

  resetHandler(e) {
    e.preventDefault()
    this.setState({actionReady: true, gameFinished: false, loading: false})
  }
  handler(e) {
    e.preventDefault()

    var action = e.currentTarget.getAttribute('value')
    this.setState({
      actionReady: false,
      loading: true,
      actionChosen: action
    })

    var $this = this
    var latitude = this.state.latitude
    var longitude = this.state.longitude

    fetch('/rest/v1/action', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        latitude: latitude,
        longitude: longitude,
        action: action
      })
    })
    .then((resp) => resp.json()) // Transform the data into json
    .then(function (data) {
      $this.setState({gameFinished:true, loading:false, result:data.response})
    })
    .catch(function (error) {
      console.log('Request failed', error);
    });
  }

  render() {
    return (
      <div>
        {this.state.actionReady &&
          <div className="actions">
            <Action actionType="rock" handler = {this.handler} />
            <Action actionType="paper" handler = {this.handler} />
            <Action actionType="scissors" handler = {this.handler} />
          </div>
        }
        {this.state.gameFinished &&
          <GameResult result={this.state.result} resetHandler={this.resetHandler} />
        }
        {this.state.loading &&
          <Loading />
        }
        {this.state.locationLoading &&
          <Loading />
        }
      </div>

    );
  }
}

export default Actions;
