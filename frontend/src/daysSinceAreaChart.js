import React from 'react'
import {Line} from "react-chartjs-2";

export default class DaysSinceAreaChart extends React.Component {

  chartRef = React.createRef();

  constructor(props) {
    super(props);
    this.state = {
      isLoaded: false,
      options: {
        responsive: true,
        // maintainAspectRatio: false,
        legend: {
          display:false
        }
        
      }

    }
  }

  componentDidMount() {
    fetch("http://localhost:8080/analysis/interactionHistory?channelID=59123458")
      .then(res => res.json())
      .then(
        (result) => {

          // Assumes {dormant, occasional, consistent}
          if (result.interactions.length != 4) {
            console.error("Engagement history dataset not composed of 3 collumns: " + result.interactions.length)
            return;
          }

          // reverse so that the dormant collum is on the bottom
          result.interactions.reverse();

          let data = {};

          // labels for the x axis. TODO: turn into dates
          data.labels = Array.from(Array(result.interactions[0].length).keys());

          // build up the datasets
          let datasets = [];
          for (let i = 0; i < result.interactions.length; i++) datasets.push({});

          for (let [index, interaction] of result.interactions.entries()) {

            datasets[index].data = interaction;
            datasets[index].borderWidth = 0.5
            datasets[index].lineTension = 0;
            datasets[index].pointRadius = 0;

            if (index === 0) {
              datasets[index].fill = 'origin'
            } else {
              datasets[index].fill = '-1'
              for (let i = 0; i < interaction.length; i++) { 
                datasets[index].data[i] += datasets[index-1].data[i]
              }
            }
          }

          // Manual Colouring and naming. TODO: Don't hardcode
          datasets[0].label = "Dormant";
          datasets[0].backgroundColor = `rgba(220, 100, 100, 0.5)`;
          datasets[1].label = "Occasional";
          datasets[1].backgroundColor = `rgba(200, 120, 200, 0.5)`;
          datasets[2].label = "Consistent";
          datasets[2].backgroundColor = `rgba(100, 150, 200, 0.5)`;
          datasets[3].label = "Persistent";
          datasets[3].backgroundColor = `rgba(100, 220, 150, 0.5)`;

          data.datasets = datasets;

          this.setState({data: data, isLoaded: true});
        }, (error) => {
          console.log(error)
        }
      )
  }

  render() {
    return (
      <div>
        {this.state.isLoaded ? <Line data={this.state.data} options={this.state.options}/> : <h1> Loading... </h1> }
      </div>
    )
  }
}