#!/anaconda3/bin/python

import os
import requests
import discord
import json
import datetime
from helpers import serializeAsJson
from dotenv import load_dotenv


load_dotenv()
token = os.getenv('DISCORD_TOKEN')



client = discord.Client()



@client.event
async def on_ready():
    
    data = []
    
    try:
        for chanel in client.get_all_channels():
            
            print("------------------")
            print(chanel.id)
            print(chanel.type)
            
            try:
                print(chanel.topic)
            except: 
                print("No topic")
                pass
            try:
                print(chanel.name)
            except: 
                print("No name")
                pass
            print("------------------")


    except Exception as e:
        print(e)
        pass 
        
    
    await client.close()

client.run(token)