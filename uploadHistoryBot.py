#!/anaconda3/bin/python

import os
import requests
import discord
import datetime

from dotenv import load_dotenv

load_dotenv()
token = os.getenv('DISCORD_TOKEN')

response = requests.get("http://api.open-notify.org/this-api-doesnt-exist")

client = discord.Client()
@client.event
async def on_ready():

    # go through 300 messages and post them to the api when connected
    print(f'{client.user.name} bot connected to guild. Beginning extraction...')

    for channel in client.get_all_channels():
        if str(channel.type) == "text":
            async for message in channel.history(limit=200):
                data = {"messageID": message.id, 
                        "channelID": channel.id, 
                        "secondsSinceEpoch": int((message.created_at - datetime.datetime.utcfromtimestamp(0)).total_seconds()),
                        "authorID": message.author.id}
                print(data)
                response = requests.post("http://localhost:8080/messages/add", data)
                if response.status_code != 200:
                    print(response.status_code, response.text)
    
    print("messages Extracted")
    await client.close()

client.run(token)

