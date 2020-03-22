import React from 'react'
import {Line} from "react-chartjs-2";

export default class AreaChart extends React.Component {

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
    fetch("http://localhost:8080/analysis/daysSinceEngagement?channelID=0")
      .then(res => res.json())
      .then(
        (result) => {

          if (result.interactions.length == 0) return;

          let data = {};

          // labels (for the x axis)
          data.labels = Array.from(Array(result.interactions[0].length).keys());

          // build up the datasets
          let datasets = [];
          for (let i = 0; i < result.interactions.length; i++) datasets.push({});

          for (let [index, interaction] of result.interactions.entries()) {

            console.log(index)

            datasets[index].label = `${index}`;
            datasets[index].backgroundColor = `rgba(${120+index*4}, 100, ${240-index*4}, 0.5)`
            datasets[index].data = interaction;

            if (index === 0) {
              datasets[index].fill = 'origin'
            } else {
              datasets[index].fill = '-1'
              for (let i = 0; i < interaction.length; i++) { 
                datasets[index].data[i] += datasets[index-1].data[i]
              }
            }
          }

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