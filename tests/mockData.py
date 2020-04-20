#!/anaconda3/bin/python

import os
import random
import requests

data = []

days = 60*60*24

channels = [{'id': 0, 'descr': "1 person twice a day, 1 person every 2 days for 100. 1 person once a day for 10 days"},
            {'id': 10000, 'descr': "20 people, starting staggered by 2 days, posting for 10-20 days, then dormant"},
            {'id': 59123458, 'descr': "30 people, posting 1-4 times between day 1 and 50." +
                                      "Plus, a spike of posts 1-10 days after day 20"},
            {'id': 53951209533, 'descr': ""}]


# --------- First ---------
for i in range(200):
    data.append({"messageID": i, 
                "channelID": channels[0]["id"], 
                "secondsSinceEpoch": i*days + 1,
                "authorID": 0})
for i in range(50):
    data.append({"messageID": i+200, 
                "channelID": channels[0]["id"], 
                "secondsSinceEpoch": i*days*2 + 1,
                "authorID": 1})
for i in range(10):
    data.append({"messageID": i+250, 
                "channelID": channels[0]["id"], 
                "secondsSinceEpoch": i*days + 1,
                "authorID": 2})

# --------- Second ---------
for p in range(30):
    for i in range(random.randint(10, 20)):
        data.append({"messageID": random.randint(0, 10000000), 
                    "channelID": channels[1]["id"], 
                    "secondsSinceEpoch": i*days + p*days*2,
                    "authorID": p})

# --------- Third ---------
for author in range(30):
    for num_messages in range(random.randint(1, 20)):
        data.append({"messageID": random.randint(0, 10000000), 
                    "channelID": channels[2]["id"], 
                    "secondsSinceEpoch": random.randint(1, 50)*days,
                    "authorID": author})
for author in range(15):
    for num_messages in range(random.randint(4, 8)):
        data.append({"messageID": random.randint(0, 10000000), 
                    "channelID": channels[2]["id"], 
                    "secondsSinceEpoch": random.randint(1, 10)*days + 20*days,
                    "authorID": author})

# Send the data
response = requests.post("http://localhost:8080/ingestion/uploadMessages", json=data)
if response.status_code != 200:
    print(response.status_code, response.text)
else:
    print("Mock messages added to database, with channelIDs:")
    for c in channels:
        print(c["id"], " -> ", c["descr"])