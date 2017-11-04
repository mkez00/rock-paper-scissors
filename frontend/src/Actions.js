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
    this.locationHandler = this.locationHandler.bind(this)
    this.state = { actionCompleted: false, gameFinished: false, loading: false, longitude:"0.00", latitude:"0.00" }
  }
  locationHandler(e) {
    // e.preventDefault()
    // var $this = this
    // if (navigator.geolocation) {
    //     navigator.geolocation.getCurrentPosition(function(position){
    //       alert("Latitude: " + position.coords.latitude + "Longitude: " + position.coords.longitude)
    //       $this.setState({latitude:position.coords.latitude, longitude:position.coords.longitude})
    //     });
    // } else {
    //   alert("No location supported")
    // }
  }
  resetHandler(e) {
    e.preventDefault()
    this.setState({actionCompleted: false, gameFinished: false, loading: false})
  }
  handler(e) {
    e.preventDefault()

    var action = e.currentTarget.getAttribute('value')
    this.setState({
      actionCompleted: true,
      loading: true,
      actionChosen: action
    })

    var $this = this
    // alert($this.state.latitude, $this.state.longitude)
    fetch('/rest/v1/action', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        latitude: "0.000",
        longitude: "0.000",
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
        {!this.state.actionCompleted &&
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
      </div>

    );
  }
}

export default Actions;
