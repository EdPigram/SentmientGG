
import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/MenuOutlined';
import MediaCard from './SourceCard';
import Grid from '@material-ui/core/Grid'
import Divider from '@material-ui/core/Divider'
//images
import Discord from './images/discord.jpg'
import Reddit from './images/reddit.jpg'
import Twitch from './images/twitch.jpg'
import { ResponsiveLine } from '@nivo/line'
// make sure parent container have a defined height when using
// responsive component, otherwise height will be 0 and
// no chart will be rendered.
// website examples showcase many properties,
// you'll often use just a few of them.

const MyResponsiveLine = () => (
  <ResponsiveLine
    data={[
      {
        "id": "japan",
        "color": "hsl(147, 70%, 50%)",
        "data": [
          {
            "x": "plane",
            "y": 202
          },
          {
            "x": "helicopter",
            "y": 208
          },
          {
            "x": "boat",
            "y": 228
          },
          {
            "x": "train",
            "y": 275
          },
          {
            "x": "subway",
            "y": 201
          },
          {
            "x": "bus",
            "y": 22
          },
          {
            "x": "car",
            "y": 253
          },
          {
            "x": "moto",
            "y": 137
          },
          {
            "x": "bicycle",
            "y": 54
          },
          {
            "x": "horse",
            "y": 240
          },
          {
            "x": "skateboard",
            "y": 58
          },
          {
            "x": "others",
            "y": 178
          }
        ]
      },
      {
        "id": "france",
        "color": "hsl(67, 70%, 50%)",
        "data": [
          {
            "x": "plane",
            "y": 288
          },
          {
            "x": "helicopter",
            "y": 195
          },
          {
            "x": "boat",
            "y": 47
          },
          {
            "x": "train",
            "y": 265
          },
          {
            "x": "subway",
            "y": 272
          },
          {
            "x": "bus",
            "y": 143
          },
          {
            "x": "car",
            "y": 180
          },
          {
            "x": "moto",
            "y": 12
          },
          {
            "x": "bicycle",
            "y": 34
          },
          {
            "x": "horse",
            "y": 260
          },
          {
            "x": "skateboard",
            "y": 71
          },
          {
            "x": "others",
            "y": 156
          }
        ]
      },
      {
        "id": "us",
        "color": "hsl(106, 70%, 50%)",
        "data": [
          {
            "x": "plane",
            "y": 43
          },
          {
            "x": "helicopter",
            "y": 258
          },
          {
            "x": "boat",
            "y": 50
          },
          {
            "x": "train",
            "y": 69
          },
          {
            "x": "subway",
            "y": 168
          },
          {
            "x": "bus",
            "y": 252
          },
          {
            "x": "car",
            "y": 130
          },
          {
            "x": "moto",
            "y": 3
          },
          {
            "x": "bicycle",
            "y": 94
          },
          {
            "x": "horse",
            "y": 230
          },
          {
            "x": "skateboard",
            "y": 119
          },
          {
            "x": "others",
            "y": 287
          }
        ]
      },
      {
        "id": "germany",
        "color": "hsl(135, 70%, 50%)",
        "data": [
          {
            "x": "plane",
            "y": 209
          },
          {
            "x": "helicopter",
            "y": 256
          },
          {
            "x": "boat",
            "y": 137
          },
          {
            "x": "train",
            "y": 281
          },
          {
            "x": "subway",
            "y": 189
          },
          {
            "x": "bus",
            "y": 5
          },
          {
            "x": "car",
            "y": 249
          },
          {
            "x": "moto",
            "y": 102
          },
          {
            "x": "bicycle",
            "y": 181
          },
          {
            "x": "horse",
            "y": 211
          },
          {
            "x": "skateboard",
            "y": 132
          },
          {
            "x": "others",
            "y": 12
          }
        ]
      },
      {
        "id": "norway",
        "color": "hsl(266, 70%, 50%)",
        "data": [
          {
            "x": "plane",
            "y": 57
          },
          {
            "x": "helicopter",
            "y": 3
          },
          {
            "x": "boat",
            "y": 195
          },
          {
            "x": "train",
            "y": 242
          },
          {
            "x": "subway",
            "y": 160
          },
          {
            "x": "bus",
            "y": 193
          },
          {
            "x": "car",
            "y": 268
          },
          {
            "x": "moto",
            "y": 260
          },
          {
            "x": "bicycle",
            "y": 238
          },
          {
            "x": "horse",
            "y": 250
          },
          {
            "x": "skateboard",
            "y": 263
          },
          {
            "x": "others",
            "y": 220
          }
        ]
      }
    ]}
    margin={{ top: 50, right: 110, bottom: 50, left: 60 }}
    xScale={{ type: 'point' }}
    yScale={{ type: 'linear', min: 'auto', max: 'auto', stacked: true, reverse: false }}
    axisTop={null}
    axisRight={null}
    axisBottom={{
      orient: 'bottom',
      tickSize: 5,
      tickPadding: 5,
      tickRotation: 0,
      legend: 'transportation',
      legendOffset: 36,
      legendPosition: 'middle'
    }}
    axisLeft={{
      orient: 'left',
      tickSize: 5,
      tickPadding: 5,
      tickRotation: 0,
      legend: 'count',
      legendOffset: -40,
      legendPosition: 'middle'
    }}
    colors={{ scheme: 'nivo' }}
    pointSize={10}
    pointColor={{ theme: 'background' }}
    pointBorderWidth={2}
    pointBorderColor={{ from: 'serieColor' }}
    pointLabel="y"
    pointLabelYOffset={-12}
    useMesh={true}
    legends={[
      {
        anchor: 'bottom-right',
        direction: 'column',
        justify: false,
        translateX: 100,
        translateY: 0,
        itemsSpacing: 0,
        itemDirection: 'left-to-right',
        itemWidth: 80,
        itemHeight: 20,
        itemOpacity: 0.75,
        symbolSize: 12,
        symbolShape: 'circle',
        symbolBorderColor: 'rgba(0, 0, 0, .5)',
        effects: [
          {
            on: 'hover',
            style: {
              itemBackground: 'rgba(0, 0, 0, .03)',
              itemOpacity: 1
            }
          }
        ]
      }
    ]}
  />
)


const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    paddingLeft: "20%",
    paddingRight: "20%",
    paddingTop: "5%",


  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
  buttonPadding: {
    paddingLeft: "2%",
  },
  buttonGroup: {
    flex: "1",
  },
  navContainer: {
    paddingLeft: "5%",
    paddingRight: "5%",
    paddingBottom: "5%",
    paddingTop: "5%", 
    minWidth: "90%"
    }

}));

function App() {
  const classes = useStyles();


  return (
    <div className="App">
      <AppBar color="transparent" style={{ boxShadow: 'none' }} position="static">
        <Toolbar>
          <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
            <MenuIcon />
          </IconButton>
          <Typography  className={classes.title} variant="h6">
            Sentiment.gg
          </Typography>
          <Button variant="outlined" color="inherit">Request Demo</Button>
        </Toolbar>
      </AppBar>
      <div className={classes.root}  >
        <Typography align="center" variant="h3" component="h2">
          Sentiment is the modern way to do Analytics
</Typography>
        <Typography  align="center" variant="h6" component="h2" align="center">
          Sentiment enables modern companies to get a quick handle on their communities. Quickly get insights from your reddit, discord and twitch communities</Typography>

        <Divider />

        <div style={{ "height": "500px", "paddingTop": "50px", "paddingBottom": "50px" }}>
          <AppBar color="transparent" style={{ boxShadow: 'none' }} position="static" >
            <Toolbar>
              <Typography className={classes.title} variant="h6">
                Interactions
          </Typography>
              <span className={classes.buttonPadding} >
                <Button  color="inherit">Day</Button>
              </span>
              <span className={classes.buttonPadding}>
                <Button  color="inherit">Month</Button>
              </span>
              <span className={classes.buttonPadding}>
                <Button  color="inherit">Year</Button>
              </span>


            </Toolbar>
          </AppBar>
          <div style={{ "height": "500px", "paddingBottom": "100px" }}>
            {MyResponsiveLine()}

          </div>
        </div>

      </div>
      <div className={classes.navContainer}>
        <Grid container spacing={1} >
          <Grid item xs={4} >
            <MediaCard image={Discord} imageTitle="Discord" headerText="Discord" bodyText="View all metrics for Discord" />

          </Grid>
          <Grid item xs={4}  >
            <MediaCard image={Reddit} imageTitle="Reddit" headerText="Reddit" bodyText="View all metrics for Reddit" />

          </Grid>
          <Grid item xs={4}>
            <MediaCard image={Twitch} imageTitle="Twitch" headerText="Twitch" bodyText="View all metrics for Twitch" />

          </Grid>

        </Grid>
      </div>

    </div>
  );
}

export default App;
