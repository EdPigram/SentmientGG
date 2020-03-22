#!/anaconda3/bin/python

import os
import requests
import discord
import datetime

from dotenv import load_dotenv

load_dotenv()

data = []

channels = [{'id': 0, 'descr': "1 person twice a day, 1 person every 2 days for 100. 1 person once a day for 10 days"},
            {'id': 10000, 'descr': ""},
            {'id': 59123458, 'descr': ""},
            {'id': 53951209533, 'descr': ""}]


# First
for i in range(200):
    data.append({"messageID": i, 
                "channelID": channels[0]["id"], 
                "secondsSinceEpoch": i*60*60*12 + 1,
                "authorID": 0})
for i in range(50):
    data.append({"messageID": i+200, 
                "channelID": channels[0]["id"], 
                "secondsSinceEpoch": i*60*60*24*2 + 1,
                "authorID": 1})
for i in range(10):
    data.append({"messageID": i+250, 
                "channelID": channels[0]["id"], 
                "secondsSinceEpoch": i*60*60*24 + 1,
                "authorID": 2})


# Send the data
response = requests.post("http://localhost:8080/ingestion/uploadMessages", json=data)
if response.status_code != 200:
    print(response.status_code, response.text)
else:
    print("Mock messages added to database, with channelIDs:")
    for c in channels:
        print(c["id"], " -> ", c["descr"])