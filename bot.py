#!/anaconda3/bin/python

# bot.py

import os
import requests
import discord

from dotenv import load_dotenv

load_dotenv()
token = os.getenv('DISCORD_TOKEN')

response = requests.get("http://api.open-notify.org/this-api-doesnt-exist")
print(response)

client = discord.Client()

@client.event
async def on_ready():
    print(f'{client.user.name} has connected to Discord!')

    # just for playing around with formatting :P
    # its updside down since its an async for loop so Im not sure
    # how to collect and reverse such.
    print('Printing the current history for any text channels...\n')
    for channel in client.get_all_channels():
        if str(channel.type) == "text":
            print(f'Channel: {channel.name}:')

            prevUser = ''
            prevDate = ''
            for message in reversed(await channel.history(limit=200).flatten()):

                # print a change in day
                d = message.created_at
                date = f'{d.day}/{d.month}/{d.year}'
                if prevDate != date:
                    prevDate = date
                    print(f'-- {date} --')

                # and a change in name
                if prevUser != message.author.name:
                    prevUser = message.author.name
                    print(f' * {message.author.name} * ')
                
                print(f'   {message.content}')


@client.event
async def on_message(message): 

    count = 0
    for otherM in reversed(await message.channel.history(limit=200).flatten()):
        if otherM.author.name == message.author.name:
            count += 1

    endings = ['th', 'st', 'nd', 'rd']
    rem = count
    if rem > 10 and rem < 20: rem = 0 #fricken teens
    rem = rem % 10
    if rem > 3: rem = 0
        
    print(f'{message.author.name} posted "{message.content}", their {count}{endings[rem]} in this channel.')
    print(message.created_at)
    
client.run(token)

